package src;

import java.util.Scanner;

public class Actividad22 {
    public static void main(String[] args) {
        /*
         * Realiza una función que devuelva cuantas cifras pares contiene un
         * número. Implementar la función gemela que retorne cuantas cifras
         * impares contiene el número parado como parámetro.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el número");
        int number = keyboard.nextInt();
        keyboard.close();
        System.out.println("El número " + number + " tiene " + evenDigits(number) + " cifras pares");
        System.out.println("El número " + number + " tiene " + oddDigits(number) + " cifras impares");

    }

    private static int evenDigits(int number) {
        //devuelve el número de cifras pares de un número
        int evenDigits = 0;
        while (number > 0) {
            if (number % 2 == 0) {
                evenDigits++;
            }
            number /= 10;
        }
        return evenDigits;
    }

    private static int oddDigits(int number) {
        //devuelve el número de cifras impares de un número
        int oddDigits = 0;
        number = Math.abs(number);
        oddDigits = String.valueOf(number).length() - evenDigits(number);
        return oddDigits;
    }

}
