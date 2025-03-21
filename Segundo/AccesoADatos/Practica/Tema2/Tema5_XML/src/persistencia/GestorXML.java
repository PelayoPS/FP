package persistencia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import logs.Logger;
import modelo.Campeon;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partida;

/**
 * Clase que gestiona la persistencia de datos en XML.
 * Proporciona métodos para cargar y guardar datos desde/hacia archivos XML,
 * así como utilidades para la validación y manipulación de documentos XML.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class GestorXML {
    private String rutaXML;
    private String rutaGuardar;
    private Document documento;
    private Logger logger;
    private Map<String, List<String>> registroErrores;

    /**
     * Constructor que inicializa el gestor XML con las rutas especificadas.
     * 
     * @param rutaXML     Ruta del archivo XML de origen para cargar datos
     * @param rutaGuardar Ruta donde se guardarán los datos XML
     */
    public GestorXML(String rutaXML, String rutaGuardar) {
        this.rutaXML = rutaXML;
        this.rutaGuardar = rutaGuardar;
        this.logger = Logger.getInstance();
        inicializarRegistroErrores();
    }

    /**
     * Inicializa el registro de errores con las categorías predefinidas.
     */
    private void inicializarRegistroErrores() {
        registroErrores = new HashMap<>();
        registroErrores.put("errores_estructura", new ArrayList<>());
        registroErrores.put("errores_formato", new ArrayList<>());
        registroErrores.put("errores_validacion", new ArrayList<>());
        registroErrores.put("advertencias", new ArrayList<>());
    }

    /**
     * Carga los datos desde el archivo XML especificado.
     * 
     * @param equipos   Lista donde se cargarán los equipos desde el XML
     * @param campeones Lista donde se cargarán los campeones desde el XML
     * @param partidas  Lista donde se cargarán las partidas desde el XML
     * @return true si la carga fue exitosa, false en caso contrario
     */
    public boolean cargarXML(List<Equipo> equipos, List<Campeon> campeones, List<Partida> partidas) {
        logger.info("Iniciando carga de datos desde " + rutaXML);
        inicializarRegistroErrores();

        File archivo = new File(rutaXML);
        if (!archivo.exists()) {
            logger.error("El archivo XML no existe: " + rutaXML);
            // Si es un nuevo archivo de test, crear uno vacío
            crearXMLVacio();
            return false;
        }

        try {
            // Parsear el documento XML
            documento = parseDocumento(archivo);

            // Verificar estructura básica del documento
            verificarEstructuraXML();

            // Crear un mapa para almacenar estadísticas (enfoque NoSQL)
            Map<String, Integer> estadisticas = inicializarEstadisticas();

            // Cargar datos
            cargarEquipos(equipos, estadisticas);
            cargarCampeones(campeones, estadisticas);
            cargarPartidas(partidas, equipos, estadisticas);

            // Mostrar estadísticas de carga
            mostrarEstadisticas(estadisticas);

            return true;
        } catch (Exception e) {
            String mensaje = "Error inesperado al cargar XML: " + e.getMessage();
            logger.fatal(mensaje);
            registrarError("errores_validacion", mensaje);
            return false;
        } finally {
            // Mostrar resumen de errores si hay alguno
            mostrarResumenErrores();
        }
    }

    /**
     * Parsea el documento XML aplicando configuraciones de seguridad y validación.
     * 
     * @param archivo El archivo XML a parsear
     * @return El documento XML parseado
     * @throws ParserConfigurationException Si hay errores en la configuración del
     *                                      parser
     * @throws SAXException                 Si hay errores de formato en el XML
     * @throws IOException                  Si hay errores de lectura del archivo
     */
    private Document parseDocumento(File archivo) throws ParserConfigurationException, SAXException, IOException {
        // Configurar el parser con seguridad y validación mejorada
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // Prevenir ataques XXE
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        dbf.setNamespaceAware(true);
        dbf.setValidating(true);
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setIgnoringComments(true);
        dbf.setIgnoringElementContentWhitespace(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        // Añadir manejador de errores personalizado
        db.setErrorHandler(new org.xml.sax.ErrorHandler() {
            @Override
            public void warning(org.xml.sax.SAXParseException e) {
                String mensaje = "Advertencia XML: " + e.getMessage();
                registrarError("advertencias", mensaje);
                logger.warning(mensaje);
            }

            @Override
            public void error(org.xml.sax.SAXParseException e) {
                String mensaje = "Error XML: " + e.getMessage();
                registrarError("errores_formato", mensaje);
                logger.error(mensaje);
            }

            @Override
            public void fatalError(org.xml.sax.SAXParseException e) throws SAXException {
                String mensaje = "Error fatal XML: " + e.getMessage();
                registrarError("errores_estructura", mensaje);
                logger.fatal(mensaje);
                throw e;
            }
        });

        try {
            Document doc = db.parse(archivo);
            doc.getDocumentElement().normalize();
            logger.info("Documento XML parseado correctamente");
            return doc;
        } catch (SAXException e) {
            String mensaje = "Error de parseo XML: " + e.getMessage();
            registrarError("errores_estructura", mensaje);
            logger.error(mensaje);
            throw e;
        } catch (IOException e) {
            String mensaje = "Error de lectura XML: " + e.getMessage();
            registrarError("errores_estructura", mensaje);
            logger.error(mensaje);
            throw e;
        }
    }

    /**
     * Verifica que el documento XML tenga la estructura básica requerida.
     * Comprueba el elemento raíz y la existencia de las secciones principales.
     * 
     * @throws IOException Si la estructura del XML no es válida
     */
    private void verificarEstructuraXML() throws IOException {
        // Verificar elemento raíz con validación mejorada
        Element raiz = documento.getDocumentElement();
        if (!"league_of_legends".equals(raiz.getNodeName())) {
            String mensaje = "Formato XML inválido: elemento raíz debe ser 'league_of_legends', encontrado '"
                    + raiz.getNodeName() + "'";
            registrarError("errores_estructura", mensaje);
            logger.error(mensaje);
            throw new IOException(mensaje);
        }

        // Verificar estructura básica del documento
        boolean tieneEquipos = raiz.getElementsByTagName("equipos").getLength() > 0;
        boolean tieneCampeones = raiz.getElementsByTagName("campeones").getLength() > 0;
        boolean tienePartidas = raiz.getElementsByTagName("partidas").getLength() > 0;

        if (!tieneEquipos) {
            String mensaje = "Falta la sección 'equipos' en el documento XML";
            registrarError("errores_estructura", mensaje);
            logger.warning(mensaje);
        }
        if (!tieneCampeones) {
            String mensaje = "Falta la sección 'campeones' en el documento XML";
            registrarError("errores_estructura", mensaje);
            logger.warning(mensaje);
        }
        if (!tienePartidas) {
            String mensaje = "Falta la sección 'partidas' en el documento XML";
            registrarError("errores_estructura", mensaje);
            logger.warning(mensaje);
        }
    }

    /**
     * Inicializa un mapa con contadores para las estadísticas de carga.
     * 
     * @return Un mapa con las estadísticas inicializadas a cero
     */
    private Map<String, Integer> inicializarEstadisticas() {
        Map<String, Integer> estadisticas = new HashMap<>();
        estadisticas.put("equipos_cargados", 0);
        estadisticas.put("campeones_cargados", 0);
        estadisticas.put("partidas_cargadas", 0);
        estadisticas.put("jugadores_cargados", 0);
        estadisticas.put("errores_formato", 0);
        return estadisticas;
    }

    /**
     * Carga los equipos desde el documento XML a la lista de equipos.
     * Valida los datos y registra errores encontrados.
     * 
     * @param equipos      Lista donde se cargarán los equipos
     * @param estadisticas Mapa para actualizar las estadísticas de carga
     */
    private void cargarEquipos(List<Equipo> equipos, Map<String, Integer> estadisticas) {
        NodeList equiposNodeList = documento.getElementsByTagName("equipo");
        if (equiposNodeList.getLength() == 0) {
            registrarError("errores_estructura", "No se encontraron elementos 'equipo' en el XML");
        }

        for (int i = 0; i < equiposNodeList.getLength(); i++) {
            try {
                Element equipoElement = (Element) equiposNodeList.item(i);

                // Validar atributo nombre
                String nombreEquipo = equipoElement.getAttribute("nombre");
                if (nombreEquipo == null || nombreEquipo.trim().isEmpty()) {
                    String mensaje = "Equipo #" + (i + 1) + " sin nombre válido";
                    registrarError("errores_validacion", mensaje);
                    throw new Exception(mensaje);
                }

                // Verificar si ya existe un equipo con el mismo nombre (duplicado)
                if (equipos.stream().anyMatch(e -> e.getNombre().equals(nombreEquipo))) {
                    registrarError("advertencias", "Equipo duplicado encontrado: '" + nombreEquipo + "'");
                }

                Equipo equipo = new Equipo(nombreEquipo);
                cargarJugadoresEquipo(equipo, equipoElement, estadisticas);

                equipos.add(equipo);
                estadisticas.put("equipos_cargados", estadisticas.get("equipos_cargados") + 1);
            } catch (Exception e) {
                String mensaje = "Error al cargar equipo #" + (i + 1) + ": " + e.getMessage();
                logger.error(mensaje);
                registrarError("errores_validacion", mensaje);
                estadisticas.put("errores_formato", estadisticas.get("errores_formato") + 1);
            }
        }
    }

    /**
     * Carga los jugadores de un equipo específico desde un elemento XML.
     * 
     * @param equipo        El equipo al que se añadirán los jugadores
     * @param equipoElement El elemento XML que contiene los datos de los jugadores
     * @param estadisticas  Mapa para actualizar las estadísticas de carga
     */
    private void cargarJugadoresEquipo(Equipo equipo, Element equipoElement, Map<String, Integer> estadisticas) {
        NodeList jugadoresNodeList = equipoElement.getElementsByTagName("jugador");
        for (int j = 0; j < jugadoresNodeList.getLength(); j++) {
            try {
                Element jugadorElement = (Element) jugadoresNodeList.item(j);

                // Validar que existan los elementos requeridos
                Node nombreNode = jugadorElement.getElementsByTagName("nombre").item(0);
                Node rolNode = jugadorElement.getElementsByTagName("rol").item(0);

                if (nombreNode == null || rolNode == null) {
                    throw new Exception("Jugador con datos incompletos");
                }

                String nombreJugador = nombreNode.getTextContent().trim();
                String rolJugador = rolNode.getTextContent().trim();

                if (nombreJugador.isEmpty() || rolJugador.isEmpty()) {
                    throw new Exception("Jugador con nombre o rol vacío");
                }

                equipo.agregarJugador(new Jugador(nombreJugador, rolJugador));
                estadisticas.put("jugadores_cargados", estadisticas.get("jugadores_cargados") + 1);
            } catch (Exception e) {
                String mensaje = "Error al cargar jugador en equipo '" + equipo.getNombre() + "': "
                        + e.getMessage();
                logger.error(mensaje);
                registrarError("errores_validacion", mensaje);
                estadisticas.put("errores_formato", estadisticas.get("errores_formato") + 1);
            }
        }
    }

    /**
     * Carga los campeones desde el documento XML a la lista de campeones.
     * Valida los datos y registra errores encontrados.
     * 
     * @param campeones    Lista donde se cargarán los campeones
     * @param estadisticas Mapa para actualizar las estadísticas de carga
     */
    private void cargarCampeones(List<Campeon> campeones, Map<String, Integer> estadisticas) {
        NodeList campeonesNodeList = documento.getElementsByTagName("campeon");
        if (campeonesNodeList.getLength() == 0) {
            registrarError("errores_estructura", "No se encontraron elementos 'campeon' en el XML");
        }

        for (int i = 0; i < campeonesNodeList.getLength(); i++) {
            try {
                Element campeonElement = (Element) campeonesNodeList.item(i);

                // Validar atributo nombre
                String nombreCampeon = campeonElement.getAttribute("nombre");
                if (nombreCampeon == null || nombreCampeon.trim().isEmpty()) {
                    String mensaje = "Campeón #" + (i + 1) + " sin nombre válido";
                    registrarError("errores_validacion", mensaje);
                    throw new Exception(mensaje);
                }

                // Verificar si ya existe un campeón con el mismo nombre (duplicado)
                if (campeones.stream().anyMatch(c -> c.getNombre().equals(nombreCampeon))) {
                    registrarError("advertencias", "Campeón duplicado encontrado: '" + nombreCampeon + "'");
                }

                // Validar elemento rol
                Node rolNode = campeonElement.getElementsByTagName("rol").item(0);
                if (rolNode == null) {
                    throw new Exception("Campeón sin rol definido");
                }

                String rolCampeon = rolNode.getTextContent().trim();
                if (rolCampeon.isEmpty()) {
                    throw new Exception("Campeón con rol vacío");
                }

                campeones.add(new Campeon(nombreCampeon, rolCampeon));
                estadisticas.put("campeones_cargados", estadisticas.get("campeones_cargados") + 1);
            } catch (Exception e) {
                logger.error("Error al cargar campeón: " + e.getMessage());
                estadisticas.put("errores_formato", estadisticas.get("errores_formato") + 1);
            }
        }
    }

    /**
     * Carga las partidas desde el documento XML a la lista de partidas.
     * Valida los datos y registra errores encontrados.
     * 
     * @param partidas     Lista donde se cargarán las partidas
     * @param equipos      Lista de equipos para validación de referencias
     * @param estadisticas Mapa para actualizar las estadísticas de carga
     */
    private void cargarPartidas(List<Partida> partidas, List<Equipo> equipos, Map<String, Integer> estadisticas) {
        NodeList partidasNodeList = documento.getElementsByTagName("partida");
        if (partidasNodeList.getLength() == 0) {
            registrarError("errores_estructura", "No se encontraron elementos 'partida' en el XML");
        }

        for (int i = 0; i < partidasNodeList.getLength(); i++) {
            try {
                Element partidaElement = (Element) partidasNodeList.item(i);

                // Validar elementos requeridos con mensajes específicos
                Node equipoNode = partidaElement.getElementsByTagName("equipo").item(0);
                Node oponenteNode = partidaElement.getElementsByTagName("oponente").item(0);
                Node resultadoNode = partidaElement.getElementsByTagName("resultado").item(0);

                if (equipoNode == null) {
                    registrarError("errores_validacion", "Partida #" + (i + 1) + ": falta el elemento 'equipo'");
                }

                if (oponenteNode == null) {
                    registrarError("errores_validacion", "Partida #" + (i + 1) + ": falta el elemento 'oponente'");
                }

                if (resultadoNode == null) {
                    registrarError("errores_validacion", "Partida #" + (i + 1) + ": falta el elemento 'resultado'");
                }

                if (equipoNode == null || oponenteNode == null || resultadoNode == null) {
                    throw new Exception("Partida con datos incompletos");
                }

                String equipoNombre = equipoNode.getTextContent().trim();
                String oponenteNombre = oponenteNode.getTextContent().trim();
                String resultado = resultadoNode.getTextContent().trim();

                if (equipoNombre.isEmpty() || oponenteNombre.isEmpty() || resultado.isEmpty()) {
                    throw new Exception("Partida con campos vacíos");
                }

                // Validar que el resultado sea válido
                if (!resultado.equals("Ganado") && !resultado.equals("Perdido") && !resultado.equals("Empate")) {
                    throw new Exception("Resultado de partida inválido: debe ser 'Ganado', 'Perdido' o 'Empate'");
                }

                // Validar que los equipos existan
                boolean equipoExiste = equipos.stream().anyMatch(e -> e.getNombre().equals(equipoNombre));
                boolean oponenteExiste = equipos.stream().anyMatch(e -> e.getNombre().equals(oponenteNombre));

                if (!equipoExiste || !oponenteExiste) {
                    logger.warning("Advertencia: Partida referencia a equipo(s) que no existe(n)");
                }

                partidas.add(new Partida(equipoNombre, oponenteNombre, resultado));
                estadisticas.put("partidas_cargadas", estadisticas.get("partidas_cargadas") + 1);
            } catch (Exception e) {
                logger.error("Error al cargar partida: " + e.getMessage());
                estadisticas.put("errores_formato", estadisticas.get("errores_formato") + 1);
            }
        }
    }

    /**
     * Muestra las estadísticas de carga del documento XML en el log.
     * 
     * @param estadisticas Mapa con las estadísticas de carga
     */
    private void mostrarEstadisticas(Map<String, Integer> estadisticas) {
        logger.info("=== Estadísticas de carga XML ====");
        logger.info("Equipos cargados: " + estadisticas.get("equipos_cargados"));
        logger.info("Jugadores cargados: " + estadisticas.get("jugadores_cargados"));
        logger.info("Campeones cargados: " + estadisticas.get("campeones_cargados"));
        logger.info("Partidas cargadas: " + estadisticas.get("partidas_cargadas"));
        logger.info("Errores de formato: " + estadisticas.get("errores_formato"));
        logger.info("=================================");
    }

    /**
     * Crea un documento XML vacío con la estructura básica requerida.
     * Se utiliza cuando el archivo XML especificado no existe.
     */
    private void crearXMLVacio() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element raiz = doc.createElement("league_of_legends");
            doc.appendChild(raiz);

            Element equiposElement = doc.createElement("equipos");
            raiz.appendChild(equiposElement);

            Element campeonesElement = doc.createElement("campeones");
            raiz.appendChild(campeonesElement);

            Element partidasElement = doc.createElement("partidas");
            raiz.appendChild(partidasElement);

            guardarDocumento(doc);
        } catch (Exception e) {
            logger.error("Error al crear XML vacío: " + e.getMessage());
            throw new RuntimeException("No se pudo crear el archivo XML");
        }
    }

    /**
     * Guarda los datos en el archivo XML especificado.
     * 
     * @param equipos   Lista de equipos a guardar
     * @param campeones Lista de campeones a guardar
     * @param partidas  Lista de partidas a guardar
     * @throws RuntimeException Si ocurre algún error durante el guardado
     */
    public void guardarXML(List<Equipo> equipos, List<Campeon> campeones, List<Partida> partidas) {
        try {
            File file = new File(rutaGuardar);
            File parentDir = file.getParentFile();

            // Verificar permisos de escritura
            if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                throw new RuntimeException("No se puede crear el directorio para el archivo XML");
            }

            if (file.exists() && !file.canWrite()) {
                throw new RuntimeException("No hay permisos de escritura en el archivo XML");
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // crea toda la estructura del XML y rellena los datos
            Element raiz = doc.createElement("league_of_legends");
            doc.appendChild(raiz);
            Element equiposElement = doc.createElement("equipos");
            raiz.appendChild(equiposElement);
            Element campeonesElement = doc.createElement("campeones");
            raiz.appendChild(campeonesElement);
            Element partidasElement = doc.createElement("partidas");
            raiz.appendChild(partidasElement);

            // guarda cada child con sus datos
            for (Equipo equipo : equipos) {
                Element equipoElement = doc.createElement("equipo");
                equipoElement.setAttribute("nombre", equipo.getNombre());
                equiposElement.appendChild(equipoElement);

                for (Jugador jugador : equipo.getJugadores()) {
                    Element jugadorElement = doc.createElement("jugador");
                    Element nombreElement = doc.createElement("nombre");
                    nombreElement.setTextContent(jugador.getNombre());
                    Element rolElement = doc.createElement("rol");
                    rolElement.setTextContent(jugador.getRol());
                    jugadorElement.appendChild(nombreElement);
                    jugadorElement.appendChild(rolElement);
                    equipoElement.appendChild(jugadorElement);
                }
            }

            for (Campeon campeon : campeones) {
                Element campeonElement = doc.createElement("campeon");
                campeonElement.setAttribute("nombre", campeon.getNombre());
                Element rolElement = doc.createElement("rol");
                rolElement.setTextContent(campeon.getRol());
                campeonElement.appendChild(rolElement);
                campeonesElement.appendChild(campeonElement);
            }

            for (Partida partida : partidas) {
                Element partidaElement = doc.createElement("partida");
                Element equipoElement = doc.createElement("equipo");
                equipoElement.setTextContent(partida.getEquipo());
                Element oponenteElement = doc.createElement("oponente");
                oponenteElement.setTextContent(partida.getOponente());
                Element resultadoElement = doc.createElement("resultado");
                resultadoElement.setTextContent(partida.getResultado());
                partidaElement.appendChild(equipoElement);
                partidaElement.appendChild(oponenteElement);
                partidaElement.appendChild(resultadoElement);
                partidasElement.appendChild(partidaElement);
            }

            guardarDocumento(doc);

        } catch (Exception e) {
            logger.error("Error al guardar XML: " + e.getMessage());
            throw new RuntimeException("Error al guardar XML: " + e.getMessage());
        }
    }

    /**
     * Guarda un documento XML en el archivo especificado.
     * Aplica formato de indentación para mejor legibilidad.
     * 
     * @param doc El documento XML a guardar
     * @throws TransformerException Si hay errores durante la transformación del
     *                              documento
     */
    private void guardarDocumento(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(rutaGuardar));
        transformer.transform(source, result);
    }

    /**
     * Formatea el nombre de la categoría de error para mostrarla de forma más
     * legible.
     * 
     * @param categoria Nombre de la categoría (errores_estructura, errores_formato,
     *                  etc.)
     * @return Nombre formateado de la categoría
     */
    private String formatearCategoria(String categoria) {
        switch (categoria) {
            case "errores_estructura":
                return "Errores de estructura XML";
            case "errores_formato":
                return "Errores de formato XML";
            case "errores_validacion":
                return "Errores de validación de datos";
            case "advertencias":
                return "Advertencias";
            default:
                return categoria;
        }
    }

    /**
     * Registra un error en la categoría especificada.
     * 
     * @param categoria Categoría del error (errores_estructura, errores_formato,
     *                  errores_validacion, advertencias)
     * @param mensaje   Mensaje descriptivo del error
     */
    private void registrarError(String categoria, String mensaje) {
        if (registroErrores.containsKey(categoria)) {
            registroErrores.get(categoria).add(mensaje);
        } else {
            logger.warning(
                    "Categoría desconocida '" + categoria + "' encontrada en registrarError. Añadiendo dinámicamente.");
            registroErrores.put(categoria, new ArrayList<>());
            registroErrores.get(categoria).add(mensaje);
        }
    }

    /**
     * Muestra un resumen de los errores encontrados durante la carga del XML.
     * Agrupa los errores por categoría y los muestra en el log.
     */
    private void mostrarResumenErrores() {
        boolean hayErrores = false;

        for (Map.Entry<String, List<String>> entrada : registroErrores.entrySet()) {
            if (!entrada.getValue().isEmpty()) {
                hayErrores = true;
                break;
            }
        }

        if (hayErrores) {
            logger.warning("Se encontraron errores durante la carga del XML");

            for (Map.Entry<String, List<String>> entrada : registroErrores.entrySet()) {
                String categoria = entrada.getKey();
                List<String> errores = entrada.getValue();

                if (!errores.isEmpty()) {
                    logger.info("\n" + formatearCategoria(categoria) + " (" + errores.size() + "):");
                    for (int i = 0; i < errores.size(); i++) {
                        if (categoria.equals("errores_estructura") || categoria.equals("errores_formato")) {
                            logger.error((i + 1) + ". " + errores.get(i));
                        } else if (categoria.equals("errores_validacion")) {
                            logger.error((i + 1) + ". " + errores.get(i));
                        } else {
                            logger.warning((i + 1) + ". " + errores.get(i));
                        }
                    }
                }
            }
        }
    }
}
