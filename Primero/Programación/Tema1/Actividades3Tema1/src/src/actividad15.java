package src;

import java.util.Scanner;

public class actividad15 {
    public static void main(String[] args) {
        /*
         * Dados dos números enteros, realizar una función que calcule el
         * cociente y el resto mediante restas sucesivas.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el dividendo");
        int dividend = keyboard.nextInt();
        System.out.println("Introduce el divisor");
        int divisor = keyboard.nextInt();
        keyboard.close();
        System.out.println("El cociente es " + division(dividend, divisor)[0]);
        System.out.println("El resto es " + division(dividend, divisor)[1]);

    }

    private static int[] division(int dividend, int divisor) {
        int quotient = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            quotient++;
        }
        return new int[]{quotient, dividend};
    }


}
