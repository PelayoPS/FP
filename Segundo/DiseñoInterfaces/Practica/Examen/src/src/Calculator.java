package src;

/**
 * Clase que proporciona métodos para realizar operaciones aritméticas básicas.
 */
public class Calculator {
    /**
     * Suma dos números.
     * @param a Primer número.
     * @param b Segundo número.
     * @return La suma de a y b.
     */
    public static double add(double a, double b) {
        return a + b;
    }
    
    /**
     * Resta dos números.
     * @param a Primer número.
     * @param b Segundo número.
     * @return La resta de a y b.
     */
    public static double subtract(double a, double b) {
        return a - b;
    }
    
    /**
     * Multiplica dos números.
     * @param a Primer número.
     * @param b Segundo número.
     * @return El producto de a y b.
     */
    public static double multiply(double a, double b) {
        return a * b;
    }
    
    /**
     * Divide dos números.
     * @param a Primer número.
     * @param b Segundo número.
     * @return El cociente de a y b.
     */
    public static double divide(double a, double b) {
        return a / b;
    }
}
