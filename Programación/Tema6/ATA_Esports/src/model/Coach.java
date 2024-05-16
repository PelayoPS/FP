package model;

/**
 * Clase que representa un entrenador.
 */
public class Coach extends Persona {
    private int experience;
    private boolean freeAgent;

    /**
     * Constructor de la clase Coach.
     * 
     * @param name El nombre del entrenador.
     * @param id El ID del entrenador.
     * @param role El rol del entrenador.
     * @param experience La experiencia del entrenador.
     * @param freeAgent Indica si el entrenador es agente libre o no.
     */
    public Coach(String name, int id, String role, int experience, boolean freeAgent) {
        super(name, id);
        this.experience = experience;
        this.freeAgent = freeAgent;
    }

    /**
     * Obtiene la experiencia del entrenador.
     * 
     * @return La experiencia del entrenador.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Verifica si el entrenador es agente libre.
     * 
     * @return true si el entrenador es agente libre, false de lo contrario.
     */
    public boolean isFreeAgent() {
        return freeAgent;
    }
}
