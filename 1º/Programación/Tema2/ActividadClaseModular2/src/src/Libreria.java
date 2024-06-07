package src;

import java.util.Scanner;

public class Libreria {
    /**
     * Método que devuelve el número de dígitos de un número entero
     * @param num
     * @return
     */
    public static int numeroDeDigitos(int num) {
        int digits = 0;
        while (num != 0) {
            num /= 10;
            digits++;
        }
        return digits;
    }

    /**
     * Método que devuelve el número inverso de un número entero
     * @param num
     * @return
     */
    public static int numeroInverso(int num) {
        int inverted = 0;
        while (num != 0) {
            inverted = (inverted * 10) + (num % 10);
            num /= 10;
        }
        return inverted;
    }

    /**
     * Método que devuelve true si el número continene el dígito
     * @param num
     * @param digito
     * @return
     */
    public static boolean contieneDigito(int num, int digito) {
        boolean contains = false;
        while (num != 0) {
            if (num % 10 == digito) {
                contains = true;
            }
            num /= 10;
        }
        return contains;
    }

    /**
     * Devuelve true si los números son familia
     * @param num1
     * @param num2
     * @return
     */
    public static boolean compartenDigito(int num1, int num2) {
        boolean contains = false;
        while (num1 != 0) {
            if (contieneDigito(num2, num1 % 10)) {
                contains = true;
            }
            num1 /= 10;
        }
        return contains;
    }

    /**
     * Método que devuelve la opción elegida en el menú
     * @return
     */
    public static int menu() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1.- números cuñados");
        System.out.println("2.- números familia");
        int option = keyboard.nextInt();
        keyboard.close();
        return option;
    }
}
