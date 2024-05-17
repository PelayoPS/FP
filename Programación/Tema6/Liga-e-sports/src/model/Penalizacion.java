package model;

/**
 * Clase que representa una penalización.
 */
public class Penalizacion {
    private int id;
    private String description;

    /**
     * Constructor de la clase Penalizacion.
     * 
     * @param id El identificador de la penalización.
     * @param description La descripción de la penalización.
     */
    public Penalizacion(int id, String description) {
        this.id = id;
        this.description = description;
    }

    
    /**
     * Devuelve el identificador de la penalización.
     * 
     * @return el identificador de la penalización
     */
    public int getId() {
        return id;
    }

    /**
     * Devuelve la descripción de la penalización.
     * 
     * @return la descripción de la penalización
     */
    public String getDescription() {
        return description;
    }
}
