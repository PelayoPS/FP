package Actividades;

import java.util.Scanner;

public class Actividad27 {
    public static void main(String[] args) {
        /*
         * Diseña un algoritmo que pida números hasta que se le introduzca un
         * número mayor que 100.
         */
        Scanner keyboard = new Scanner(System.in);
        int number = 0;
        while (number <= 100) {
            System.out.print("Introduzca un número menor que 100 para continuar: ");
            number = keyboard.nextInt();
        }
        keyboard.close();
    }
}
