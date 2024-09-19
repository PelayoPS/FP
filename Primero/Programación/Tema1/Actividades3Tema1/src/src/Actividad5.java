package src;

import java.util.Scanner;

public class Actividad5 {
    public static void main(String[] args) {
        /*
         * Implementar un función que determine si un año es bisiesto o no
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un año: ");
        int year = keyboard.nextInt();
        keyboard.close();
        if (esBisiesto(year)) {
            System.out.println("El año " + year + " es bisiesto");
        } else {
            System.out.println("El año " + year + " no es bisiesto");
        }
    }

    /**
     * función que comprueba si un año es bisiesto o no
     * @param year
     * @return
     */
    public static boolean esBisiesto(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
