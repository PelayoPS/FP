package src;

public class Persona implements Humano {

    // Atributos
    private String nombre;
    private String apellidos;
    private String dni;
    private Direccion direccion;

    // Constructor

    /**
     * Constructor de la clase Persona
     * @param String : nombre de la persona
     * @param String : apellidos de la persona
     * @param String : dni de la persona
     * @param Direccion : dirección de la persona
     */
    public Persona(String nombre, String apellidos, String dni, Direccion direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
    }

    // Getters y setters

    /**
     * Getter del nombre
     * @return String : nombre de la persona
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del nombre
     * @param String : nombre de la persona
     */
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de los apellidos
     * @return String : apellidos de la persona
     */
    @Override
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Setter de los apellidos
     * @param String : apellidos de la persona
     */
    @Override
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Getter del dni
     * @return String : dni de la persona
     */
    @Override
    public String getDni() {
        return dni;
    }

    /**
     * Setter del dni
     * @param String : dni de la persona
     */
    @Override
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Getter de la dirección
     * @return Direccion : dirección de la persona
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Setter de la dirección
     * @param Direccion : dirección de la persona
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
