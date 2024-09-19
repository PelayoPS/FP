package src;

import java.util.Scanner;

public class Actividad12 {
    public static void main(String[] args) {
        /*
         * Utilizando la función anterior, calcula el número combinatorio
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el primer número para calcular el número combinatorio");
        int n = keyboard.nextInt();
        System.out.println("Introduce el segundo número para calcular el número combinatorio");
        int k = keyboard.nextInt();
        System.out.println("El número combinatorio de "
                + n + " sobre " + k + " es " + combinatorio(n, k));

        keyboard.close();
    }

    private static int factorial(int number) {
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result = result * i;
        }
        return result;
    }

    private static int combinatorio(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

}
