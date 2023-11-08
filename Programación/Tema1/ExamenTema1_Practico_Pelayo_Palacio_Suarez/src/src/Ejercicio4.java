package src;

import java.util.Scanner;

public class Ejercicio4 {
    /**
     * Programa que calcule el número mágico de una persona. Para calcularlo se suman todas las
     * cifras de la fecha de nacimiento y se sigue sumando hasta que el resultado sea un número
     * de un sólo digíto.
     * Solicitando día mes y año como números enteros
     */
    public static void main(String[] args) {
        int day = 0, month = 0, year = 0, magicNumber = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el día de nacimiento: ");
        day = keyboard.nextInt();
        System.out.println("Introduce el mes de nacimiento: ");
        month = keyboard.nextInt();
        System.out.println("Introduce el año de nacimiento: ");
        year = keyboard.nextInt();
        keyboard.close();
        magicNumber = day + month + year;
        while (magicNumber > 9) {
            int sum = 0;
            while (magicNumber > 0) {
                sum += magicNumber % 10;
                magicNumber /= 10; 
            }
            magicNumber = sum;
        }
        System.out.println("El número mágico de " + day + "/" + month + "/" + year + " es " + magicNumber);
    }

    
}
