package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Arrays;

import logs.Logger;
import modelo.Campeon;
import modelo.Equipo;
import modelo.Partida;
import persistencia.GestorXML;

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
    private Logger logger;
    private GestorXML gestorXML;

    private static final Set<String> ROLES_VALIDOS = new HashSet<>(
            Arrays.asList("Top", "Jungle", "Mid", "ADC", "Support"));

    /**
     * Constructor de la clase Liga.
     * Inicializa las listas y carga los datos desde el archivo XML especificado.
     * 
     * @param rutaXML     Ruta del archivo XML que contiene los datos de la liga
     * @param rutaGuardar Ruta donde se guardarán los datos de la liga
     */
    public Liga(String rutaXML, String rutaGuardar) {
        equipos = new ArrayList<>();
        campeones = new ArrayList<>();
        partidas = new ArrayList<>();
        logger = Logger.getInstance();
        gestorXML = new GestorXML(rutaXML, rutaGuardar);

        // Cargar datos desde XML
        gestorXML.cargarXML(equipos, campeones, partidas);
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
     * @throws IllegalArgumentException Si el rol del campeón no es válido
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

    /**
     * Guarda los datos de la liga en el archivo XML.
     * Delega la operación al gestor XML.
     */
    public void guardarXML() {
        gestorXML.guardarXML(equipos, campeones, partidas);
    }
}
