package src;

public class Ejercicio1 {

    /**
     * Muestra los puntos de luz de un vector
     * un punto de luz es un valor múltiplo de 3 e impar en una posición par
     * 
     * @param int[] v: vector de enteros
     */
    public static void mostrarPuntosLuz(int[] v) {
        for (int i = 0; i < v.length; i++) {
            if (v[i] % 3 == 0 && v[i] % 2 != 0 && i % 2 == 0) {
                System.out.println("Punto de luz en la posición " + i + " con valor " + v[i]);
            }
        }
    }
}
