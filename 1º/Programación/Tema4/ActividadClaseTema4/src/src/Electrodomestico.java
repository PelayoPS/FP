package src;

/**
 * Clase que representa un electrodomestico
 * 
 * @version 1.0, 01/03/2024
 * @autor Pelayo Palacio Suárez
 */
public class Electrodomestico {

    // Constantes
    private final Color DEFAULT_COLOR = Color.BLANCO;
    private final char[] CONSUMOS_ENERGETICOS = { 'A', 'B', 'C', 'D', 'E', 'F' };
    private final char DEFAULT_CONSUMO = CONSUMOS_ENERGETICOS[5];
    private final double DEFAULT_PRECIO = 100;
    private final double DEFAULT_PESO = 5;

    // Atributos
    private double precioBase = DEFAULT_PRECIO;
    private Color color = DEFAULT_COLOR;
    private char consumoEnergetico = DEFAULT_CONSUMO;
    private double peso = DEFAULT_PESO;

    // Enums
    /**
     * Permite que sólo pueda tener esos colores
     */
    public enum Color {
        BLANCO, NEGRO, VERDE, GRIS;
    }

    // Constructores
    /**
     * Constructor por defecto
     */
    public Electrodomestico() {
    }

    /**
     * Asigna precio y peso el resto de valores por defecto
     * 
     * @param double : precio
     * @param double : peso
     */
    public Electrodomestico(double precio, double peso) {
        this.peso = peso;
        this.precioBase = precio;
    }

    // Métodos

    /**
     * Aumenta el precio en base al consumo energético y al peso
     */
    public void precioFinal() {
        switch (consumoEnergetico) {
            case 'A':
                precioBase += 100;
                aumentarPrecioPorPeso();
                break;
            case 'B':
                precioBase += 80;
                aumentarPrecioPorPeso();
                break;
            case 'C':
                precioBase += 60;
                aumentarPrecioPorPeso();
                break;
            case 'D':
                precioBase += 40;
                aumentarPrecioPorPeso();
                break;
            case 'E':
                precioBase += 40;
                aumentarPrecioPorPeso();
                break;

            case 'F':
                precioBase += 40;
                aumentarPrecioPorPeso();
                break;

            default:
                // No va a pasar porque las letras se verifican en constructor
                break;
        }
    }

    /**
     * Aumenta el precio base en función del peso
     */
    private void aumentarPrecioPorPeso() {
        if (peso >= 0 && peso <= 19) {
            precioBase += 10;
        } else if (peso >= 20 && peso <= 49) {
            precioBase += 50;
        } else if (peso >= 50 && peso <= 79) {
            precioBase += 80;
        } else if (peso >= 80) {
            precioBase += 100;
        }
    }

    // Getters

    /**
     * Devuelve el precio base
     * 
     * @return double : precio base
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Devuelve el color
     * 
     * @return String : color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Devuelve el consumo energético
     * 
     * @return char : consumo energético
     */
    public char getConsumoEnergetico() {
        return consumoEnergetico;
    }

    /**
     * Devuelve el peso
     * 
     * @return double : peso
     */
    public double getPeso() {
        return peso;
    }

    // Setters

    /**
     * Asigna el precio
     * 
     */
    public void setPrecioBase(double precio) {
        this.precioBase = precio;
    }

}
