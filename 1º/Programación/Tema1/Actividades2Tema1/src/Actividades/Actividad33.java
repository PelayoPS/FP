package Actividades;

import java.util.Arrays;

public class Actividad33 {
    public static void main(String[] args) {
        /*
         * Genera una combinación al azar para la lotería primitiva. Los números
         * de la lotería son 6, comprendidos del 1 al 49. En esta versión no
         * importa si se repite algún número.
         */
        int[] numbers = new int[6];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (int) (Math.random() * 49 + 1);
        }
        System.out.println("La combinación ganadora es: " + Arrays.toString(numbers));
    }
}
