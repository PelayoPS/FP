package src;

import java.util.Scanner;

public class Actividad2 {
    public static void main(String[] args) {
        /*
         * Escribir un programa que pida al usuario un número entero positivo y
         * muestre por pantalla todos los números impares desde 1 hasta ese
         * número separados por comas.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero positivo: ");
        int number = keyboard.nextInt();
        keyboard.close();
        //bucle que muestra los números impares
        for (int i = 1; i <= number; i++) {
            if (i % 2 != 0) {
                if (i == number) {
                    System.out.print(i);
                } else {
                    System.out.print(i + ", ");
                }
            }   
        }
        
    }

}
