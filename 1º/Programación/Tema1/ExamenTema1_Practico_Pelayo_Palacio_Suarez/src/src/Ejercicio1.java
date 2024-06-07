package src;

import java.util.Scanner;

public class Ejercicio1 {
    /**
     * Programa que pida 3 números y escriba si 3 son iguales, si 2 son iguales
     * o si los 3 son distintos.
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double num1, num2, num3;
        System.out.println("COMPARADOR DE TRES NÚMEROS");
        System.out.println("Introduce el primer número: ");
        num1 = keyboard.nextDouble();
        System.out.println("Introduce el segundo número: ");
        num2 = keyboard.nextDouble();
        System.out.println("Introduce el tercer número: ");
        num3 = keyboard.nextDouble();
        if (num1 == num2 && num2 == num3) {
            System.out.println("Los tres números son iguales");
        } else if (num1 == num2 || num2 == num3 || num1 == num3) {
            System.out.println("Dos de los números son iguales");
        } else {
            System.out.println("Los tres números son distintos");
        }
        keyboard.close();
    }
}
