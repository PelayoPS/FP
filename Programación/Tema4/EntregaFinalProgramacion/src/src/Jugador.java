package src;

public class Jugador extends Persona {

    // Atributos

    private String equipo;
    private int numero;

    // Constructor

    /**
     * Constructor de la clase Jugador
     * @param String : nombre del jugador
     * @param String : apellidos del jugador
     * @param String : dni del jugador
     * @param Direccion : dirección del jugador
     * @param String : equipo del jugador
     * @param int : número del jugador
     */
    public Jugador(String nombre, String apellidos, String dni, Direccion direccion, String equipo, int numero) {
        super(nombre, apellidos, dni, direccion);
        this.equipo = equipo;
        this.numero = numero;
    }

    // Getters y setters

    /**
     * Getter del equipo
     * @return String : equipo del jugador
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * Setter del equipo
     * @param String : equipo del jugador
     */
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    /**
     * Getter del número
     * @return int : número del jugador
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Setter del número
     * @param int : número del jugador
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Métodos

    /**
     * Método toString
     * @return String : información del jugador
     */
    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + getNombre() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", equipo='" + equipo + '\'' +
                ", numero=" + numero +
                '}';
    }
}
