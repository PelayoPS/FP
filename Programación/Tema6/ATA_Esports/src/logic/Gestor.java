package logic;

import java.util.HashMap;
import java.util.Map;

import model.Coach;
import model.Equipo;
import model.Jugador;
import model.Penalizacion;

/**
 * La clase Gestor es responsable de gestionar los equipos y las penalizaciones
 * en el sistema.
 * Permite agregar equipos y penalizaciones, así como obtener información sobre
 * un equipo o una penalización específica.
 */
public class Gestor {
    private static Map<Integer, Equipo> equipos;
    private static Map<Integer, Penalizacion> penalizaciones;
    private static Map<Integer, Jugador> jugadores;
    private static Map<Integer, Coach> entrenadores;

    static {
        equipos = new HashMap<>();
        penalizaciones = new HashMap<>();
        jugadores = new HashMap<>();
        entrenadores = new HashMap<>();
    }

    /**
     * Agrega un equipo al sistema.
     * 
     * @param equipo El equipo a agregar.
     */
    public static void addEquipo(Equipo equipo) {
        equipos.put(equipo.getId(), equipo);
    }

    /**
     * Agrega una penalización al sistema.
     * 
     * @param penalizacion La penalización a agregar.
     */
    public static void addPenalizacion(Penalizacion penalizacion) {
        penalizaciones.put(penalizacion.getId(), penalizacion);
    }

    /**
     * Obtiene un equipo por su ID.
     * 
     * @param id El ID del equipo.
     * @return El equipo correspondiente al ID especificado, o null si no se
     *         encuentra.
     */
    public static Equipo getEquipo(int id) {
        return equipos.get(id);
    }

    /**
     * Obtiene una penalización por su ID.
     * 
     * @param id El ID de la penalización.
     * @return La penalización correspondiente al ID especificado, o null si no se
     *         encuentra.
     */
    public static Penalizacion getPenalizacion(int id) {
        return penalizaciones.get(id);
    }

    /**
     * Elimina un equipo del sistema.
     * 
     * @param id El ID del equipo a eliminar.
     */
    public static void removeEquipo(int id) {
        equipos.remove(id);
    }

    /**
     * Elimina una penalización del sistema.
     * 
     * @param id El ID de la penalización a eliminar.
     */
    public static void removePenalizacion(int id) {
        penalizaciones.remove(id);
    }

    /**
     * Agrega un entrenador al sistema
     * 
     * @param coach entrenador a agregar
     */
    public static void agregarEntrenador(Coach coach) {
        entrenadores.put(coach.getId(), coach);
    }

    /**
     * Agrega un jugador al sistema
     * 
     * @param jugador jugador a agregar
     */
    public static void agregarJugador(Jugador jugador) {
        jugadores.put(jugador.getId(), jugador);
    }

    /**
     * Elimina un jugador
     * 
     * @param jugador jugador a eliminar
     */
    public static void eliminarJugador(Jugador jugador) {
        jugadores.remove(jugador.getId());
    }

    /**
     * Elimina un entrenador
     * 
     * @param coach entrenador a eliminar
     */
    public static void eliminarEntrenador(Coach coach) {
        entrenadores.remove(coach.getId());
    }

    /**
     * Obtiene un jugador por su ID
     * 
     * @param id ID del jugador
     * @return jugador correspondiente al ID especificado, o null si no se encuentra
     */
    public static Jugador getJugador(int id) {
        return jugadores.get(id);
    }

    /**
     * Obtiene un entrenador por su ID
     * 
     * @param id ID del entrenador
     * @return entrenador correspondiente al ID especificado, o null si no se encuentra
     */
    public static Coach getCoach(int id) {
        return entrenadores.get(id);
    }

}
