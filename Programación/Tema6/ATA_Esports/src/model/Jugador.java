package model;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Jugador representa a un jugador en el sistema de ATA Esports.
 * Hereda de la clase Persona y contiene información específica de un jugador,
 * como su rol, estado de agente libre y los equipos a los que ha pertenecido.
 */
public class Jugador extends Persona {
    private String role;
    private boolean freeAgent;
    private List<Equipo> equipos;

    /**
     * Crea un nuevo objeto Jugador con el nombre, ID, rol y estado de agente libre especificados.
     * 
     * @param name El nombre del jugador.
     * @param id El ID del jugador.
     * @param role El rol del jugador.
     * @param freeAgent El estado de agente libre del jugador.
     */
    public Jugador(String name, int id, String role, boolean freeAgent) {
        super(name, id);
        this.role = role;
        this.freeAgent = freeAgent;
        this.equipos = new ArrayList<>();
    }

    /**
     * Obtiene el rol del jugador.
     * 
     * @return El rol del jugador.
     */
    public String getRole() {
        return role;
    }

    /**
     * Verifica si el jugador es un agente libre.
     * 
     * @return true si el jugador es un agente libre, false de lo contrario.
     */
    public boolean isFreeAgent() {
        return freeAgent;
    }

    /**
     * Establece el estado de agente libre del jugador.
     * 
     * @param freeAgent El nuevo estado de agente libre del jugador.
     */
    public void setFreeAgent(boolean freeAgent) {
        this.freeAgent = freeAgent;
    }

    /**
     * Agrega un equipo a la lista de equipos del jugador.
     * 
     * @param equipo El equipo a agregar.
     */
    public void addEquipo(Equipo equipo) {
        equipos.add(equipo);
    }
}
