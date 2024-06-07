package src;

import java.util.Scanner;

public class Actividad13 {
    public static void main(String[] args) {
        /*
         * Determinar mediante una función si un número introducido por
         * teclado es primo o no. Un número primo solo es divisible por él mismo
         * y por la unidad
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número para saber si es primo o no");
        int number = keyboard.nextInt();
        keyboard.close();
        if (isPrime(number)) {
            System.out.println("El número " + number + " es primo");
        } else {
            System.out.println("El número " + number + " no es primo");
        }
    }

    private static boolean isPrime(int number) {
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }

}
