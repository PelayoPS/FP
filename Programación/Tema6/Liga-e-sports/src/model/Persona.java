package model;

/**
 * La clase Persona representa a una persona con un nombre y un identificador.
 */
public class Persona {
    private String name;
    private int id;

    /**
     * Crea una nueva instancia de la clase Persona.
     * 
     * @param name el nombre de la persona
     * @param id el identificador de la persona
     */
    public Persona(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     * 
     * @return el nombre de la persona
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el identificador de la persona.
     * 
     * @return el identificador de la persona
     */
    public int getId() {
        return id;
    }
}
