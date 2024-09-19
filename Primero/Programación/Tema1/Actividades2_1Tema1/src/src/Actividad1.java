package src;

import java.util.Scanner;

public class Actividad1 {
    public static void main(String[] args) {
        /*
         * Escribir un programa que pregunte al usuario su edad y muestre por
         * pantalla todos los años que ha cumplido (desde 1 hasta su edad).
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce tu edad: ");
        int age = keyboard.nextInt();
        //consigue el año actual
        int year = java.time.Year.now().getValue();
        //bucle que muestra los años que ha cumplido
        for (int i = 1; i <= age; i++) {
            System.out.println("Año " + i + ": " + (year - age + i));
        }
        keyboard.close();
    }

}
