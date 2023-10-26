package src;

import java.util.Scanner;

public class Algoritmo {
    public static void main(String[] args){
        /**
         * Diseñar un algoritmo que invierta un número leído por teclado.
         * El número deber ser estrictamente positivo.
         * Si se introduce 0 o número negativo muestra un mensaje de error
         * y vuelve a pedir el número.
         */
        Scanner keyboard = new Scanner(System.in);
        int number = 0;
        System.out.println("Introduce un número positivo: ");
        number = keyboard.nextInt();
        while(number <= 0){
            System.err.println("El número introducido no es positivo.");
            System.out.println("Introduce un número positivo: ");
            number = keyboard.nextInt();
        }
        keyboard.close();
        System.out.println("El número introducido es: " + number);
        System.out.println("El número invertido es: " + invertNumber(number));
    }

    private static String invertNumber(int number){
        String invertedNumber = "";
        while(number > 0){
            invertedNumber += number % 10;
            number /= 10;
        }
        return invertedNumber+"";
    }
}
