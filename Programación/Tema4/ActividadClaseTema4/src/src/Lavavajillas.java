package src;

/**
 * Clase que representa un lavavajillas
 * 
 * @version 1.0, 01/03/2024
 * @autor Pelayo Palacio Suárez
 */
public class Lavavajillas extends Electrodomestico {

    // Constantes
    private final int DEFAULT_CARGA = 8;

    // Atributos
    private double carga = DEFAULT_CARGA;

    // Constructores
    /**
     * Constructor por defecto
     */
    public Lavavajillas() {
        super();//aplica los aumentos de precio de electrodomestico
        precioFinal();
    }

    /**
     * Asigna precio, peso y carga el resto de valores por defecto
     * 
     * @param double : precio
     * @param double : peso
     */
    public Lavavajillas(double precio, double peso) {
        super(precio, peso);//aplica los aumentos de precio de electrodomestico
        precioFinal();
    }

    // Getters
    /**
     * Devuelve la carga
     * @return double : carga
     */
    public double getCarga() {
        return carga;
    }

    // Métodos
    /**
     * Aumenta el precio en base a la carga
     */
    private void precioFinal() {
        if (carga > 10) {
            setPrecioBase(getPrecioBase() + 50);
        }
    }



}
