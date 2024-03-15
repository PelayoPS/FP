package src;

public class Direccion {

    // Atributos
    private String calle;

    // Constructor

    /**
     * Constructor de la clase Direccion
     * @param String : calle de la dirección
     */
    public Direccion(String calle) {
        this.calle = calle;
    }

    // Getters y setters

    /**
     * Getter de la calle
     * @return String : calle de la dirección
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Setter de la calle
     * @param String : calle de la dirección
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }


    // Métodos

    /**
     * Método toString
     * @return String : información de la dirección
     */
    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle +
                '}';
    }
}
