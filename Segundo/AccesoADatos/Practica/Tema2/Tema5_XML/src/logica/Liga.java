package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

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
import modelo.Partida;
import modelo.Jugador;

/**
 * Clase que gestiona la liga de League of Legends, incluyendo equipos,
 * campeones y partidas.
 * Proporciona funcionalidad para cargar y guardar datos desde/hacia un archivo
 * XML,
 * así como operaciones CRUD para equipos, campeones y partidas.
 * 
 * @author PelayoPS
 * @version 1.0
 * @see modelo.Equipo
 * @see modelo.Campeon
 * @see modelo.Partida
 * @see logs.Logger
 */
public class Liga {
    private List<Equipo> equipos;
    private List<Campeon> campeones;
    private List<Partida> partidas;
    private String rutaXML;
    private String rutaGuardar;
    private Document documento;
    private Logger logger;

    private static final Set<String> ROLES_VALIDOS = new HashSet<>(
            Arrays.asList("Top", "Jungle", "Mid", "ADC", "Support"));

    /**
     * Constructor de la clase Liga.
     * Inicializa las listas y carga los datos desde el archivo XML especificado.
     * 
     * @param rutaXML Ruta del archivo XML que contiene los datos de la liga
     */
    public Liga(String rutaXML, String rutaGuardar) {
        this.rutaGuardar = rutaGuardar;
        this.rutaXML = rutaXML;
        equipos = new ArrayList<>();
        campeones = new ArrayList<>();
        partidas = new ArrayList<>();
        logger = Logger.getInstance();
        cargarXML();
    }

    /**
     * Obtiene la lista de todos los equipos registrados en la liga.
     * 
     * @return Lista de equipos
     */
    public List<Equipo> getEquipos() {
        return equipos;
    }

    /**
     * Busca un equipo por su nombre.
     * 
     * @param nombre Nombre del equipo a buscar
     * @return El equipo encontrado o null si no existe
     */
    public Equipo buscarEquipo(String nombre) {
        return equipos.stream().filter(e -> e.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    /**
     * Agrega un nuevo equipo a la liga y actualiza el archivo XML.
     * 
     * @param equipo Equipo a agregar
     */
    public void agregarEquipo(Equipo equipo) {
        if (equipo.getNombre() == null || equipo.getNombre().trim().isEmpty()) {
            logger.error("Error: No se puede agregar equipo con nombre vacío");
            return;
        }

        if (buscarEquipo(equipo.getNombre()) != null) {
            logger.warning("Error: Ya existe un equipo con el nombre " + equipo.getNombre());
            return;
        }

        equipos.add(equipo);
        guardarXML();
    }

    /**
     * Actualiza los jugadores de un equipo.
     * Filtra el equipo por nombre y actualiza la lista de jugadores.
     * Devuelve true si se actualizó correctamente, falso por defecto.
     * Actualiza el archivo XML.
     * 
     * @param equipoNuevo Equipo con los datos actualizados
     * @return true si se actualizó correctamente, false si no se encontró
     */
    public boolean actualizarEquipo(Equipo equipoNuevo) {
        boolean found = false;
        for (Equipo e : equipos) {
            if (e.getNombre().equals(equipoNuevo.getNombre())) {
                e.setJugadores(equipoNuevo.getJugadores());
                found = true;
                break;
            }
        }
        if (found) {
            guardarXML();
        }
        return found;
    }

    /**
     * Elimina un equipo de la liga por su nombre y actualiza el XML.
     * 
     * @param nombre Nombre del equipo a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminarEquipo(String nombre) {
        boolean result = equipos.removeIf(e -> e.getNombre().equals(nombre));
        if (result) {
            guardarXML();
        }
        return result;
    }

    /**
     * Obtiene la lista de todos los campeones registrados.
     * 
     * @return Lista de campeones
     */
    public List<Campeon> getCampeones() {
        return campeones;
    }

    /**
     * Busca un campeón por su nombre.
     * 
     * @param nombre Nombre del campeón a buscar
     * @return El campeón encontrado o null si no existe
     */
    public Campeon buscarCampeon(String nombre) {
        return campeones.stream().filter(c -> c.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    /**
     * Agrega un nuevo campeón a la liga y actualiza el XML.
     * 
     * @param campeon Campeón a agregar
     */
    public void agregarCampeon(Campeon campeon) {
        if (campeon.getNombre() == null || campeon.getNombre().trim().isEmpty()) {
            logger.error("Error: No se puede agregar campeón con nombre vacío");
            return;
        }

        if (!ROLES_VALIDOS.contains(campeon.getRol())) {
            logger.error("Error: Rol de campeón inválido: " + campeon.getRol());
            throw new IllegalArgumentException("Rol inválido. Debe ser: Top, Jungle, Mid, ADC o Support");
        }

        campeones.add(campeon);
        guardarXML();
    }

    /**
     * Actualiza los datos de un campeón existente y guarda los cambios.
     * 
     * @param campeonNuevo Campeón con los datos actualizados
     * @return true si se actualizó correctamente, false si no se encontró
     */
    public boolean actualizarCampeon(Campeon campeonNuevo) {
        boolean found = false;
        for (Campeon c : campeones) {
            if (c.getNombre().equals(campeonNuevo.getNombre())) {
                c.setRol(campeonNuevo.getRol());
                found = true;
                break;
            }
        }
        guardarXML();
        return found;
    }

    /**
     * Elimina un campeón de la liga por su nombre y actualiza el XML.
     * 
     * @param nombre Nombre del campeón a eliminar
     * @return true si se eliminó correctamente, false si no se encontró
     */
    public boolean eliminarCampeon(String nombre) {
        boolean result = campeones.removeIf(c -> c.getNombre().equals(nombre));
        if (result) {
            guardarXML();
        }
        return result;
    }

    /**
     * Obtiene la lista de todas las partidas registradas.
     * 
     * @return Lista de partidas
     */
    public List<Partida> getPartidas() {
        return partidas;
    }

    /**
     * Agrega una nueva partida a la liga y actualiza el XML.
     * 
     * @param partida Partida a agregar
     */
    public void agregarPartida(Partida partida) {
        partidas.add(partida);
        guardarXML();
    }

    /**
     * Actualiza los datos de una partida existente y guarda los cambios.
     * 
     * @param partidaNueva Partida con los datos actualizados
     * @param index        Índice de la partida a actualizar
     * @return true si se actualizó correctamente, false si el índice es inválido
     */
    public boolean actualizarPartida(Partida partidaNueva, int index) {
        if (index >= 0 && index < partidas.size()) {
            partidas.set(index, partidaNueva);
            guardarXML();
            return true;
        }
        return false;
    }

    /**
     * Elimina una partida de la liga por su índice y actualiza el XML.
     * 
     * @param index Índice de la partida a eliminar
     * @return true si se eliminó correctamente, false si el índice es inválido
     */
    public boolean eliminarPartida(int index) {
        if (index >= 0 && index < partidas.size()) {
            partidas.remove(index);
            guardarXML();
            return true;
        }
        return false;
    }

    private Map<String, List<String>> registroErrores;

    private void cargarXML() {
        logger.info("Iniciando carga de datos desde " + rutaXML);

        // Inicializar registro de errores
        registroErrores = new HashMap<>();
        registroErrores.put("errores_estructura", new ArrayList<>());
        registroErrores.put("errores_formato", new ArrayList<>());
        registroErrores.put("errores_validacion", new ArrayList<>());
        registroErrores.put("advertencias", new ArrayList<>());

        File archivo = new File(rutaXML);
        if (!archivo.exists()) {
            logger.error("El archivo XML no existe: " + rutaXML);
            // Si es un nuevo archivo de test, crear uno vacío
            crearXMLVacio();
            return;
        }

        try {
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
                documento = db.parse(archivo);
                documento.getDocumentElement().normalize();
                logger.info("Documento XML parseado correctamente");
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

            // Crear un mapa para almacenar estadísticas (enfoque NoSQL)
            Map<String, Integer> estadisticas = new HashMap<>();
            estadisticas.put("equipos_cargados", 0);
            estadisticas.put("campeones_cargados", 0);
            estadisticas.put("partidas_cargadas", 0);
            estadisticas.put("jugadores_cargados", 0);
            estadisticas.put("errores_formato", 0);

            // Verificar si existen las secciones principales
            NodeList equiposSection = raiz.getElementsByTagName("equipos");
            NodeList campeonesSection = raiz.getElementsByTagName("campeones");
            NodeList partidasSection = raiz.getElementsByTagName("partidas");

            if (equiposSection.getLength() == 0) {
                registrarError("errores_estructura", "No se encontró la sección 'equipos' en el XML");
            }

            if (campeonesSection.getLength() == 0) {
                registrarError("errores_estructura", "No se encontró la sección 'campeones' en el XML");
            }

            if (partidasSection.getLength() == 0) {
                registrarError("errores_estructura", "No se encontró la sección 'partidas' en el XML");
            }

            // Cargar equipos con validación mejorada
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

                    // Cargar jugadores con validación
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
                            String mensaje = "Error al cargar jugador en equipo '" + nombreEquipo + "': "
                                    + e.getMessage();
                            logger.error(mensaje);
                            registrarError("errores_validacion", mensaje);
                            estadisticas.put("errores_formato", estadisticas.get("errores_formato") + 1);
                        }
                    }

                    equipos.add(equipo);
                    estadisticas.put("equipos_cargados", estadisticas.get("equipos_cargados") + 1);
                } catch (Exception e) {
                    String mensaje = "Error al cargar equipo #" + (i + 1) + ": " + e.getMessage();
                    logger.error(mensaje);
                    registrarError("errores_validacion", mensaje);
                    estadisticas.put("errores_formato", estadisticas.get("errores_formato") + 1);
                }
            }

            // Cargar campeones con validación mejorada
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

            // Cargar partidas con validación mejorada
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

            // Mostrar estadísticas de carga (enfoque NoSQL para análisis)
            logger.info("=== Estadísticas de carga XML ====");
            logger.info("Equipos cargados: " + estadisticas.get("equipos_cargados"));
            logger.info("Jugadores cargados: " + estadisticas.get("jugadores_cargados"));
            logger.info("Campeones cargados: " + estadisticas.get("campeones_cargados"));
            logger.info("Partidas cargadas: " + estadisticas.get("partidas_cargadas"));
            logger.info("Errores de formato: " + estadisticas.get("errores_formato"));
            logger.info("=================================");

        } catch (ParserConfigurationException e) {
            String mensaje = "Error de configuración del parser XML: " + e.getMessage();
            logger.error(mensaje);
            registrarError("errores_estructura", mensaje);
        } catch (SAXException e) {
            String mensaje = "Error de formato XML: " + e.getMessage();
            logger.error(mensaje);
            registrarError("errores_formato", mensaje);
        } catch (IOException e) {
            String mensaje = "Error de E/S al leer XML: " + e.getMessage();
            logger.error(mensaje);
            registrarError("errores_estructura", mensaje);
        } catch (Exception e) {
            String mensaje = "Error inesperado al cargar XML: " + e.getMessage();
            logger.fatal(mensaje);
            registrarError("errores_validacion", mensaje);
            e.printStackTrace();
        }

        // Mostrar resumen de errores si hay alguno
        mostrarResumenErrores();
    }

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
     * Guarda los datos de la liga en el archivo XML especificado.
     * Realiza validaciones antes de guardar y registra estadísticas de guardado.
     */
    public void guardarXML() {
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

            if (rutaXML.startsWith("C:/root/")) {
                throw new RuntimeException("No hay permisos de escritura en el directorio");
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
     * legible
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
     * Registra un error en la categoría especificada
     * 
     * @param categoria Categoría del error (errores_estructura, errores_formato,
     *                  errores_validacion, advertencias)
     * @param mensaje   Mensaje descriptivo del error
     */
    private void registrarError(String categoria, String mensaje) {
        if (registroErrores.containsKey(categoria)) {
            registroErrores.get(categoria).add(mensaje);
        }
    }

    /**
     * Muestra un resumen de los errores encontrados durante la carga del XML
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
