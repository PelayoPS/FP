package src;

import java.util.Scanner;

public class Ejercicio1 {
    /**
     * Escribe un programa que pida el límite inferior y superior de un
     * intervalo. Si el límite inferior es mayor que el superior lo
     * tiene que volver a pedir. A continuación, se van introduciendo
     * números hasta que introduzcamos el 0. Cuando termine el programa
     * dará las siguientes informaciones:
     * - Suma de los que estén dentro del intervalo
     * - Cuántos números están fuera del intervalo
     * - Informa si hemos introducido algún número igual a los
     * límites del intervalo
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el límite inferior del intervalo");
        int lowerLimit = keyboard.nextInt();
        System.out.println("Introduce el límite superior del intervalo");
        int upperLimit = keyboard.nextInt();
        while (lowerLimit >= upperLimit) {
            System.out.println("El límite inferior no puede ser mayor o igual que el superior");
            System.out.println("Introduce el límite inferior del intervalo");
            lowerLimit = keyboard.nextInt();
            System.out.println("Introduce el límite superior del intervalo");
            upperLimit = keyboard.nextInt();
        }
        int number = 1;
        int sum = 0;
        int numbersOutside = 0;
        int numbersEqual = 0;
        while(number != 0) {
            System.out.println("Introduce un número");
            number = keyboard.nextInt();
            if (number > lowerLimit && number < upperLimit) {
                sum += number;
            } else {
                numbersOutside++;
            }
            if (number == lowerLimit || number == upperLimit) {
                numbersEqual++;
            }
        }
        System.out.println("La suma de los números dentro del intervalo es " + sum);
        System.out.println("Hay " + numbersOutside + " números fuera del intervalo");
        if (numbersEqual > 0) {
            System.out.println("Has introducido " + numbersEqual + " números iguales a los límites del intervalo");
        } else {
            System.out.println("No has introducido ningún número igual a los límites del intervalo");
        }
        keyboard.close();
    }
}
