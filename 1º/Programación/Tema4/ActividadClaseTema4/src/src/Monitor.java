package src;

/**
 * Clase que representa un monitor
 * 
 * @version 1.0, 01/03/2024
 * @autor Pelayo Palacio Suárez
 */
public class Monitor extends Electrodomestico {

    // Constantes
    private final int DEFAULT_PULGADAS = 30;

    // Atributos
    private int pulgadas = DEFAULT_PULGADAS;

    // Constructores
    /**
     * Constructor por defecto
     */
    public Monitor() {
        super();
    }

    /**
     * Asigna precio, peso y pulgadas el resto de valores por defecto
     * 
     * @param double : precio
     * @param double : peso
     */
    public Monitor(double precio, double peso) {
        super(precio, peso);
    }

    // Getters
    /**
     * Devuelve las pulgadas
     * 
     * @return int : pulgadas
     */
    public int getPulgadas() {
        return pulgadas;
    }

    // Métodos
    /**
     * Aumenta el precio en base a las pulgadas
     */
    public void precioFinal() {
        super.precioFinal();//aplica el aumento de precio de electrodomestico
        if (pulgadas > 30) {
            setPrecioBase(getPrecioBase() + 50);
        }
    }
}
