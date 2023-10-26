package src;

import java.util.Scanner;

public class Actividad4 {
    public static void main(String[] args) {
        /*
         * Implementar un código modular con una función que calcule la raíz
         * cuadrada de un numero que recibe como parámetro, teniendo la
         * precaución de no llamar a la función si el numero es negativo, en cuyo
         * caso se muestra un mensaje de error.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un numero: ");
        int numero = keyboard.nextInt();
        keyboard.close();
        if (esPositivo(numero)) {
            System.out.println("La raiz cuadrada de " + numero + " es: " + raizCuadrada(numero));
        } else {
            System.err.println("El numero es negativo");
        }
    }

    /**
     * función que comprueba si un numero es positivo o negativo
     * @param numero
     * @return
     */
    public static boolean esPositivo(int numero) {
        return numero >= 0;
    }

    /**
     * función que calcula la raiz cuadrada de un numero
     * @param numero
     * @return
     */
    public static double raizCuadrada(int numero) {
        return Math.sqrt(numero);
    }

}
