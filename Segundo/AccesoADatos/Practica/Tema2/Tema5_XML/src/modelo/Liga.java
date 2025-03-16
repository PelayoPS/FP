package modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Liga {
    private List<Equipo> equipos;
    private List<Campeon> campeones;
    private List<Partida> partidas;
    private String rutaXML;
    private Document documento;

    public Liga(String rutaXML) {
        this.rutaXML = rutaXML;
        equipos = new ArrayList<>();
        campeones = new ArrayList<>();
        partidas = new ArrayList<>();
        cargarXML();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public Equipo buscarEquipo(String nombre) {
        return equipos.stream().filter(e -> e.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public void agregarEquipo(Equipo equipo) {
        equipos.add(equipo);
        guardarXML();
    }

    /**
     * Actualiza los jugadores de un equipo.
     * Filtra el equipo por nombre y actualiza la lista de jugadores.
     * Devuelve true si se actualizó correctamente, falso por defecto.
     * Actualiza el archivo XML.
     * 
     * @param equipoNuevo
     * @return
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

    public boolean eliminarEquipo(String nombre) {
        boolean result = equipos.removeIf(e -> e.getNombre().equals(nombre));
        if (result) {
            guardarXML();
        }
        return result;
    }

    public List<Campeon> getCampeones() {
        return campeones;
    }

    public Campeon buscarCampeon(String nombre) {
        return campeones.stream().filter(c -> c.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public void agregarCampeon(Campeon campeon) {
        campeones.add(campeon);
        guardarXML();
    }

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

    public boolean eliminarCampeon(String nombre) {
        boolean result = campeones.removeIf(c -> c.getNombre().equals(nombre));
        if (result) {
            guardarXML();
        }
        return result;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void agregarPartida(Partida partida) {
        partidas.add(partida);
        guardarXML();
    }

    public boolean actualizarPartida(Partida partidaNueva, int index) {
        if (index >= 0 && index < partidas.size()) {
            partidas.set(index, partidaNueva);
            guardarXML();
            return true;
        }
        return false;
    }

    public boolean eliminarPartida(int index) {
        if (index >= 0 && index < partidas.size()) {
            partidas.remove(index);
            guardarXML();
            return true;
        }
        return false;
    }

    private void cargarXML() {
        try {
            File archivo = new File(rutaXML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            documento = db.parse(archivo);
            documento.getDocumentElement().normalize();

            // Cargar equipos
            NodeList equiposNodeList = documento.getElementsByTagName("equipo");
            for (int i = 0; i < equiposNodeList.getLength(); i++) {
                Element equipoElement = (Element) equiposNodeList.item(i);
                Equipo equipo = new Equipo(equipoElement.getAttribute("nombre"));

                NodeList jugadoresNodeList = equipoElement.getElementsByTagName("jugador");
                for (int j = 0; j < jugadoresNodeList.getLength(); j++) {
                    Element jugadorElement = (Element) jugadoresNodeList.item(j);
                    String nombreJugador = jugadorElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String rolJugador = jugadorElement.getElementsByTagName("rol").item(0).getTextContent();
                    equipo.agregarJugador(new Jugador(nombreJugador, rolJugador));
                }
                equipos.add(equipo);
            }

            // Cargar campeones
            NodeList campeonesNodeList = documento.getElementsByTagName("campeon");
            for (int i = 0; i < campeonesNodeList.getLength(); i++) {
                Element campeonElement = (Element) campeonesNodeList.item(i);
                String nombreCampeon = campeonElement.getAttribute("nombre");
                String rolCampeon = campeonElement.getElementsByTagName("rol").item(0).getTextContent();
                campeones.add(new Campeon(nombreCampeon, rolCampeon));
            }

            // Cargar partidas
            NodeList partidasNodeList = documento.getElementsByTagName("partida");
            for (int i = 0; i < partidasNodeList.getLength(); i++) {
                Element partidaElement = (Element) partidasNodeList.item(i);
                String equipoNombre = partidaElement.getElementsByTagName("equipo").item(0).getTextContent();
                String oponenteNombre = partidaElement.getElementsByTagName("oponente").item(0).getTextContent();
                String resultado = partidaElement.getElementsByTagName("resultado").item(0).getTextContent();
                partidas.add(new Partida(equipoNombre, oponenteNombre, resultado));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guardarXML() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            // Elemento raíz
            Element raiz = doc.createElement("league_of_legends");
            doc.appendChild(raiz);

            // Equipos
            Element equiposElement = doc.createElement("equipos");
            raiz.appendChild(equiposElement);

            for (Equipo equipo : equipos) {
                Element equipoElement = doc.createElement("equipo");
                equipoElement.setAttribute("nombre", equipo.getNombre());
                equiposElement.appendChild(equipoElement);

                for (Jugador jugador : equipo.getJugadores()) {
                    Element jugadorElement = doc.createElement("jugador");
                    equipoElement.appendChild(jugadorElement);

                    Element nombreElement = doc.createElement("nombre");
                    nombreElement.setTextContent(jugador.getNombre());
                    jugadorElement.appendChild(nombreElement);

                    Element rolElement = doc.createElement("rol");
                    rolElement.setTextContent(jugador.getRol());
                    jugadorElement.appendChild(rolElement);
                }
            }

            // Campeones
            Element campeonesElement = doc.createElement("campeones");
            raiz.appendChild(campeonesElement);

            for (Campeon campeon : campeones) {
                Element campeonElement = doc.createElement("campeon");
                campeonElement.setAttribute("nombre", campeon.getNombre());
                campeonesElement.appendChild(campeonElement);

                Element rolElement = doc.createElement("rol");
                rolElement.setTextContent(campeon.getRol());
                campeonElement.appendChild(rolElement);
            }

            // Partidas
            Element partidasElement = doc.createElement("partidas");
            raiz.appendChild(partidasElement);

            for (Partida partida : partidas) {
                Element partidaElement = doc.createElement("partida");
                partidasElement.appendChild(partidaElement);

                Element equipoElement = doc.createElement("equipo");
                equipoElement.setTextContent(partida.getEquipo());
                partidaElement.appendChild(equipoElement);

                Element oponenteElement = doc.createElement("oponente");
                oponenteElement.setTextContent(partida.getOponente());
                partidaElement.appendChild(oponenteElement);

                Element resultadoElement = doc.createElement("resultado");
                resultadoElement.setTextContent(partida.getResultado());
                partidaElement.appendChild(resultadoElement);
            }

            // Guardar el documento XML
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaXML));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
