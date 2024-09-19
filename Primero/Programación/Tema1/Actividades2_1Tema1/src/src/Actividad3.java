package src;

import java.util.Scanner;

public class Actividad3 {
    public static void main(String[] args) {
        /*
         * Escribir un programa que pida al usuario un número entero positivo y
         * muestre por pantalla la cuenta atrás desde ese número hasta cero
         * separados por comas
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero positivo: ");
        int number = keyboard.nextInt();
        keyboard.close();
        //bucle que muestra la cuenta atrás
        for (int i = number; i >= 0; i--) {
            if (i == 0) {
                System.out.print(i);
            } else {
                System.out.print(i + ", ");
            }
        }
    }

}
