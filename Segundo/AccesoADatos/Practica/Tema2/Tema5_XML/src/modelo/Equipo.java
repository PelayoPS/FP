package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Clase que representa un equipo en el juego League of Legends.
 * Contiene información sobre el nombre del equipo, los jugadores que lo
 * componen, la fecha de creación del equipo, y el número de victorias y
 * derrotas.
 * 
 * @author PelayoPS
 * @version 1.0
 * @see Jugador
 */
public class Equipo {
    private String nombre;
    private List<Jugador> jugadores;
    private Date fechaCreacion;
    private int victorias;
    private int derrotas;

    /**
     * Constructor por defecto que inicializa un equipo con valores por defecto.
     * La fecha de creación es la fecha actual, y el número de victorias y derrotas
     * es 0.
     */
    public Equipo() {
        jugadores = new ArrayList<>();
        fechaCreacion = new Date();
        victorias = 0;
        derrotas = 0;
    }

    /**
     * Constructor que inicializa un equipo con el nombre especificado.
     * La fecha de creación es la fecha actual, y el número de victorias y derrotas
     * es 0.
     *
     * @param nombre Nombre del equipo
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
        jugadores = new ArrayList<>();
        fechaCreacion = new Date();
        victorias = 0;
        derrotas = 0;
    }

    /**
     * Getter para el nombre del equipo.
     * 
     * @return Nombre del equipo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter para el nombre del equipo.
     * 
     * @param nombre Nombre del equipo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter para los jugadores del equipo.
     * 
     * @return Lista de jugadores del equipo
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Setter para los jugadores del equipo.
     * 
     * @param jugadores Lista de jugadores del equipo
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    /**
     * Agrega un jugador al equipo.
     * 
     * @param jugador Jugador a agregar
     */
    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    /**
     * Elimina un jugador del equipo por su nombre.
     * 
     * @param nombre Nombre del jugador a eliminar
     * @return true si el jugador fue eliminado, false si no se encontró
     */
    public boolean eliminarJugador(String nombre) {
        return jugadores.removeIf(j -> j.getNombre().equals(nombre));
    }

    /**
     * Busca un jugador en el equipo por su nombre.
     * 
     * @param nombre Nombre del jugador a buscar
     * @return Jugador encontrado o null si no se encuentra
     */
    public Jugador buscarJugador(String nombre) {
        return jugadores.stream().filter(j -> j.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    /**
     * ToString para representar el equipo.
     * 
     * @return Cadena que representa el equipo
     */
    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre + ", jugadores=" + jugadores + "]";
    }

}
