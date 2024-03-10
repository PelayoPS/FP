package src;

/**
 * Clase que representa una habitación de un hotel
 * 
 * @version 1.1, 28/02/2021
 * @autor Pelayo Palacio Suárez
 */
public class Habitacion {

    // =======================ATRIBUTOS========================================
    private int identificador;
    private int planta;
    private String tipo;
    private double precio;
    private boolean ocupada;
    private boolean tieneVistaExterior;

    // =======================CONSTRUCTORES===================================

    /**
     * Constructor por defecto con todos los parámetros
     * 
     * @param identificador      : int valor de la identificación de la habitación
     * @param planta             : int valor de la planta en la que se encuentra la
     *                           habitación
     * @param tipo               : String valor del tipo de habitación
     * @param precio             : boolean valor del precio de la habitación
     * @param ocupada            : boolean valor que indica si la habitación está
     *                           ocupada
     * @param tieneVistaExterior : boolean valor que indica si la habitación tiene
     *                           vista exterior
     */
    public Habitacion(int identificador, int planta, String tipo, double precio,
            boolean ocupada, boolean tieneVistaExterior) {
        this.identificador = identificador;
        this.planta = planta;
        this.tipo = tipo;
        this.precio = precio;
        this.ocupada = ocupada;
        this.tieneVistaExterior = tieneVistaExterior;
    }

    // =======================GETTERS==========================================

    /**
     * Al ser un identificador único, no se puede modificar
     * y por eso no tiene un setter
     * 
     * @return int valor del identificador de la habitación
     */
    public int getIdentificador() {
        return identificador;
    }

    /**
     * Al ser un valor único, no se puede modificar
     * y por eso no tiene un setter
     * 
     * @return int valor de la planta en la que se encuentra la habitación
     */
    public int getPlanta() {
        return planta;
    }

    /**
     * Al ser un valor único, no se puede modificar
     * y por eso no tiene un setter
     * 
     * @return String valor del tipo de habitación
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @return double valor del precio de la habitación
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @return boolean valor que indica si la habitación está ocupada
     */
    public boolean getOcupada() {
        return ocupada;
    }

    /**
     * @return boolean valor que indica si la habitación tiene vista exterior
     */
    public boolean getTieneVistaExterior() {
        return tieneVistaExterior;
    }

    // =======================SETTERS==========================================

    /**
     * Cambia el valor del precio de la habitación
     * 
     * @param precio : double valor del precio de la habitación
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Cambia el valor de si la habitación está ocupada
     * 
     * @param ocupada : boolean valor que indica si la habitación está ocupada
     */
    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    /**
     * Cambia el valor de si la habitación tiene vista exterior
     * 
     * @param tieneVistaExterior : boolean valor que indica si la habitación tiene
     *                           vista exterior
     */
    public void setTieneVistaExterior(boolean tieneVistaExterior) {
        this.tieneVistaExterior = tieneVistaExterior;
    }

    // =======================MÉTODOS==========================================
    /**
     * Método que asigna un localizador a la habitación
     * con los tipos:
     * - Simple: S + planta + identificador
     * - Doble: D + planta + identificador
     * - Matrimoniales: M + planta + identificador
     * - Especiales: E + planta + identificador
     * 
     * @return String valor del localizador de la habitación
     */
    public String asignarLocalizador() {
        String localizador = "";// Inicializamos la variable localizador
        switch (tipo) {// Según el tipo de habitación
            case "Simple":
                localizador = "S" + planta + identificador;
                break;
            case "Doble":
                localizador = "D" + planta + identificador;
                break;
            case "Matrimonial":
                localizador = "M" + planta + identificador;
                break;
            case "Especial":
                localizador = "E" + planta + identificador;
                break;
            default:
                break;
        }
        return localizador;// Devolvemos el localizador
    }

    // =======================TO STRING==========================================
    /**
     * Método que devuelve una cadena con la información de la habitación
     * 
     * @return String valor con la información de la habitación
     */
    @Override
    public String toString() {
        // Devolvemos la información de la habitación
        // Con formato: Habitación [localizador] - Precio: [precio]€ - Ocupada: [ocupada] - Vista exterior: [tieneVistaExterior]
        return "Habitación " + asignarLocalizador() + " - Precio: " + getPrecio()
                + "€ - Ocupada: " + getOcupada()
                + " - Vista exterior: " + getTieneVistaExterior();
    }

}
