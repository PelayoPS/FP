package src;

import java.util.Scanner;

public class Actividad20 {
    public static void main(String[] args) {
        /*
         * Realiza una función que admita 3 números enteros y los devuelva
         * ordenados de menor a mayor.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el primer número");
        int num1 = keyboard.nextInt();
        System.out.println("Introduce el segundo número");
        int num2 = keyboard.nextInt();
        System.out.println("Introduce el tercer número");
        int num3 = keyboard.nextInt();
        keyboard.close();

        int[] result = sort(num1, num2, num3);
        System.out.println("Los números ordenados de menor a mayor son:");
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int[] sort(int num1, int num2, int num3) {
        //ordena los 3 números de menor a mayor
        int[] result = new int[3];
        if (num1 < num2) {
            if (num1 < num3) {
                result[0] = num1;
                if (num2 < num3) {
                    result[1] = num2;
                    result[2] = num3;
                } else {
                    result[1] = num3;
                    result[2] = num2;
                }
            } else {
                result[0] = num3;
                result[1] = num1;
                result[2] = num2;
            }
        } else {
            if (num2 < num3) {
                result[0] = num2;
                if (num1 < num3) {
                    result[1] = num1;
                    result[2] = num3;
                } else {
                    result[1] = num3;
                    result[2] = num1;
                }
            } else {
                result[0] = num3;
                result[1] = num2;
                result[2] = num1;
            }
        }
        return result;
    }

}
