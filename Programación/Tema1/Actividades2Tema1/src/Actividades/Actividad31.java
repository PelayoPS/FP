package Actividades;

import java.util.Scanner;

public class Actividad31 {
    public static void main(String[] args) {
        /*
         * Determinar si un número introducido por teclado es primo o no. Un
         * número primo solo es divisible por él mismo y por la unidad.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Introduzca un número: ");
        int number = keyboard.nextInt();
        keyboard.close();
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
            }
        }
        if (isPrime) {
            System.out.println("El número " + number + " es primo");
        } else {
            System.out.println("El número " + number + " no es primo");
        }
    }

}
