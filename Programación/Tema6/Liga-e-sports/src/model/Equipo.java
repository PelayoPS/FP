package model;

import java.util.ArrayList;
import java.util.List;

import logic.exceptions.ExcepcionMaxJugadores;
import logic.exceptions.ExceptionJugadorNoEncontrado;

/**
 * La clase Equipo representa un equipo en el contexto de un juego o deporte.
 * Contiene información sobre el nombre del equipo, los jugadores que lo componen
 * y el entrenador del equipo.
 */
public class Equipo {

    private static final int MAX_JUGADORES = 5;

    private String nombre;
    private List<Jugador> jugadores;
    private Coach entrenador;
    private int id;

    /**
     * Crea un nuevo objeto Equipo con el nombre especificado.
     *
     * @param nombre el nombre del equipo
     */
    public Equipo(int id, String nombre) {
        this.nombre = nombre;
        this.jugadores = new ArrayList<>();
        this.entrenador = null;
        this.id = id;
    }

    /**
     * Obtiene el nombre del equipo.
     *
     * @return el nombre del equipo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del equipo.
     *
     * @param nombre el nombre del equipo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID del equipo.
     *
     * @return el ID del equipo
     */
    public int getId(){
        return id;
    }

    /**
     * Establece el ID del equipo.
     *
     * @param id el ID del equipo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la lista de jugadores del equipo.
     *
     * @return la lista de jugadores del equipo
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Agrega un jugador a la lista de jugadores del equipo.
     *
     * @param jugador el jugador a agregar
     * @throws ExcepcionMaxJugadores si se alcanza el número máximo de jugadores permitidos
     */
    public void addJugador(Jugador jugador) throws ExcepcionMaxJugadores {
        if (jugadores.size() >= MAX_JUGADORES) {
            throw new ExcepcionMaxJugadores();
        }

        jugadores.add(jugador);
    }

    /**
     * Elimina un jugador de la lista de jugadores del equipo.
     *
     * @param jugador el jugador a eliminar
     * @throws ExceptionJugadorNoEncontrado si el jugador no se encuentra en la lista
     */
    public void removeJugador(Jugador jugador) throws ExceptionJugadorNoEncontrado {
        if (!jugadores.contains(jugador)) {
            throw new ExceptionJugadorNoEncontrado();
        }
        jugadores.remove(jugador);
    }

    /**
     * Obtiene el entrenador del equipo.
     *
     * @return el entrenador del equipo
     */
    public Coach getCoach() {
        return entrenador;
    }

    /**
     * Establece el entrenador del equipo.
     *
     * @param entrenador el entrenador del equipo
     */
    public void setCoach(Coach entrenador) {
        this.entrenador = entrenador;
    }

}
