package src;

import java.util.Scanner;

public class Ejercicio2 {
    /**
     * Programa que pide números reales mientras el usuario meta números mayores
     * que el primero.
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double start = 0, num;
        System.out.println("Introduce el primer número: ");
        start = keyboard.nextDouble();
        do {
            System.out.println("Introduce un número mayor que " + start + ": ");
            num = keyboard.nextDouble();
        } while (num > start);
        System.out.println(num + " no es mayor que " + start);
        keyboard.close();
    }
}
