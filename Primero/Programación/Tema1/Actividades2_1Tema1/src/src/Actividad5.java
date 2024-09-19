package src;

import java.util.Scanner;

public class Actividad5 {
    public static void main(String[] args) {
        /*
         * Escribir un programa que pida al usuario un número entero y muestre
         * por pantalla un triángulo rectángulo como el de más abajo, de altura el
         * número introducido.
         * ejemplo: 
         * input = 4
         * output:
         * *
         * **
         * ***
         * ****
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero: ");
        int number = keyboard.nextInt();
        keyboard.close();
        //bucle que muestra el triángulo
        for (int i = 1; i <= number; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            //salto de línea
            System.out.println();
        }
    }

}
