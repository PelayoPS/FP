package src;

import java.util.Scanner;

public class Actividad6 {
    public static void main(String[] args) {
        /*
         * Escribir un programa que pida al usuario un número entero y muestre
         * por pantalla un triángulo rectángulo como el de más abajo.
         * ejemplo:
         * input = 5
         * output:
         * 1
         * 3 1
         * 5 3 1
         * 7 5 3 1
         * 9 7 5 3 1
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero: ");
        int number = keyboard.nextInt();
        keyboard.close();
        //bucle que muestra el triángulo
        for (int i = 1; i <= number; i++) {
            for (int j = i; j >= 1; j--) {
                System.out.print(j * 2 - 1 + " ");
            }
            //salto de línea
            System.out.println();
        }
    }
}
