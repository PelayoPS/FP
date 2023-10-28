package src;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        /**
         * Diseñar un algoritmo que calcule el numero de mensualidades
         * necesarias para que una persona devuelva el prestamo que adquirió
         * en la compra de un producto, teniendo en cuenta que cada cuanta
         * será el doble de la anterior y además el importe íntegro a devolver será
         * incrementado en un 5%.
         * El programa debe informar del importe final y el número de cuantías.
         * También deberá mostrar el importe de cada cuantía
         * Ejemplo:
         * Precio del producto: 200€ => importe final a devolver 210€ ; 5 cuantias
         * Cuantías:
         * • Mes 1: 10€ (cantidad pendiente 200)
         * • Mes 2: 20€ (cantidad pendiente 180)
         * • Mes 3: 40€ (cantidad pendiente 140)
         * • Mes 4: 80€ (cantidad pendiente 60)
         * • Mes 5: 60€ (cantidad pendiente 0)
         */
        Scanner keyboard = new Scanner(System.in);
        double price = 0;
        System.out.println("Introduce el precio del producto: ");
        price = keyboard.nextDouble();
        double finalPrice = price * 1.05;
        int month = 10;
        int counter = 1;
        keyboard.close();
        while (finalPrice > 0) {
            if (finalPrice < month) {
                System.out.println("Mes " + counter + ": " + finalPrice + "€ (cantidad pendiente " + 0 + ")");
                System.out.println("Se paga en " + counter + " cuotas.");
                System.exit(0);
            } else {
                System.out.println("Mes " + counter + ": " + month + "€ (cantidad pendiente " + finalPrice + ")");
                finalPrice -= month;
                month *= 2;
                counter++;
            }
        }
        
    }
}
