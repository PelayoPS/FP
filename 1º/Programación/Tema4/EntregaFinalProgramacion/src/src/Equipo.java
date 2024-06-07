package src;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    // Atributos
    private String nombre;
    private List<Persona> miembros;

    // Constructor

    /**
     * Constructor de la clase Equipo
     * @param String : nombre del equipo
     */
    public Equipo(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
    }

    // Getters y setters

    /**
     * Getter del nombre
     * @return String : nombre del equipo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre
     * @param String : nombre del equipo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de los miembros
     * @return List<Persona> : miembros del equipo
     */
    public List<Persona> getMiembros() {
        return miembros;
    }

    // Métodos

    /**
     * Método agregarMiembro que agrega una persona a la lista de miembros
     * @param Persona : persona a agrega
     */
    public void agregarMiembro(Persona persona) {
        miembros.add(persona);
    }

    /**
     * Método eliminarMiembro que elimina una persona de la lista de miembros
     * @param Persona : persona a eliminar
     */
    public void eliminarMiembro(Persona persona) {
        miembros.remove(persona);
    }

    /**
     * Método mostrarMiembros que muestra los miembros del equipo
     */
    public void mostrarMiembros() {
        for (Persona persona : miembros) {
            System.out.println(persona.toString());
        }
    }

    /**
     * Método numeroJugadores que devuelve el número de jugadores del equipo
     * @return int : número de jugadores del equipo
     */
    public int numeroJugadores() {
        int numJugadores = 0;
        for (Persona persona : miembros) {
            if (persona instanceof Jugador) {
                numJugadores++;
            }
        }
        return numJugadores;
    }

    /**
     * Método numeroEntrenadores que devuelve el número de entrenadores del equipo
     * @return int : número de entrenadores del equipo
     */
    public int numeroEntrenadores() {
        int numEntrenadores = 0;
        for (Persona persona : miembros) {
            if (persona instanceof Entrenador) {
                numEntrenadores++;
            }
        }
        return numEntrenadores;
    }

}
