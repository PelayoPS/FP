package src;

public class Entrenador extends Persona {

    // Atributos
    private String equipo;
    private String titulacion;

    // Constructor

    /**
     * Constructor de la clase Entrenador
     * @param String : nombre del entrenador
     * @param String : apellidos del entrenador
     * @param String : dni del entrenador
     * @param Direccion : dirección del entrenador
     * @param String : equipo del entrenador
     * @param String : titulación del entrenador
     */
    public Entrenador(String nombre, String apellidos, String dni, Direccion direccion, String equipo,
            String titulacion) {
        super(nombre, apellidos, dni, direccion);
        this.equipo = equipo;
        this.titulacion = titulacion;
    }

    // Getters y setters

    /**
     * Getter del equipo
     * @return String : equipo del entrenador
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * Setter del equipo
     * @param String : equipo del entrenador
     */
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    /**
     * Getter de la titulación
     * @return String : titulación del entrenador
     */
    public String getTitulacion() {
        return titulacion;
    }

    /**
     * Setter de la titulación
     * @param String : titulación del entrenador
     */
    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    // Métodos

    /**
     * Método toString
     * @return String : información del entrenador
     */
    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + getNombre() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", equipo='" + equipo + '\'' +
                ", titulacion='" + titulacion + '\'' +
                '}';
    }
}
