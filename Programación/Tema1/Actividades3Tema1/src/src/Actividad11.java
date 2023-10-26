package src;

import java.util.Scanner;

public class Actividad11 {
    public static void main(String[] args) {
        /*
         * Calcula mediante una función el factorial de un número entero. El
         * factorial de un número es el resultado de multiplicar ese número por
         * todos los números menores que él.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número para calcular su factorial");
        int number = keyboard.nextInt();
        System.out.println("El factorial de " + number + " es " + factorial(number));
        keyboard.close();
    }

    private static int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result = result * i;
        }
        return result;
    }

}
