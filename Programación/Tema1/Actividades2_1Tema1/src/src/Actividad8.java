package src;

import java.util.Scanner;

public class Actividad8 {
    public static void main(String[] args) {
        /*
         * Leer por teclado un número entero N no negativo y mostrar el factorial
         * de todos los números desde 0 hasta N.
         * Mostrando como output:
         * 0! = 1
         * 1! = 1
         * 2! = 2
         * ...
         * n! = n * (n – 1) * (n – 2) * .... * 3 * 2 * 1
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero no negativo: ");
        int number = keyboard.nextInt();
        int factorial = 1;
        if (number < 0) {
            System.out.println("El número introducido no es válido");
        } else {
            for (int i = 0; i <= number; i++) {
                if (i == 0) {
                    System.out.println(i + "! = 1");
                } else {
                    factorial = factorial * i;
                    System.out.println(i + "! = " + factorial);
                }
            }
        }
        keyboard.close();
    }

}
