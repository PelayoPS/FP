package src;

import java.util.Scanner;

public class Actividad23 {
    public static void main(String[] args) {
        /*
         * Implementar una función que calcule la suma de los dígitos de un
         * número.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el número");
        int number = keyboard.nextInt();
        number = Math.abs(number);
        keyboard.close();
        System.out.println("La suma de los dígitos del número " + number + " es " + sumDigits(number));
    }

    private static int sumDigits(int number) {
        //devuelve la suma de los dígitos de un número
        int sumDigits = 0;
        while (number > 0) {
            sumDigits += number % 10;
            number /= 10;
        }
        return sumDigits;
    }

}
