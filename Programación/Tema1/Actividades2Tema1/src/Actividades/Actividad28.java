package Actividades;

import java.util.Scanner;

public class Actividad28 {
    public static void main(String[] args) {
        /*
         * Implementar un algoritmo que pida números hasta que el número
         * leído este entre 1 y 5, en ese momento se debe salir.
         */
        Scanner keyboard = new Scanner(System.in);
        int number = 0;
        while (number <= 1 || number >= 5) {
            System.out.print("Introduzca un número entre 1 y 5: ");
            number = keyboard.nextInt();
        }
        keyboard.close();
    }
}
