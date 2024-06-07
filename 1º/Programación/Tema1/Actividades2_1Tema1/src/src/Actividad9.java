package src;

import java.util.Scanner;

public class Actividad9 {
    public static void main(String[] args) {
        /*
         * Leer por teclado un número entero N no negativo y mostrar la suma de
         * los factoriales de todos los números desde 0 hasta N.
         * Este ejercicio es una variante del anterior. Para resolverlo basta con
         * añadir otra variable suma que actúe como acumulador donde
         * sumaremos el factorial obtenido de cada número.
         * La variable suma también se ha declarado de tipo double igual que la
         * variable factorial.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero no negativo: ");
        int number = keyboard.nextInt();
        int suma = 0;
        int factorial = 1;
        if (number < 0) {
            System.out.println("El número introducido no es válido");
        } else {
            for (int i = 0; i <= number; i++) {
                if (i == 0) {
                    System.out.println(i + "! = 1");
                    suma++;
                } else {
                    factorial = factorial * i;
                    System.out.println(i + "! = " + factorial);
                    suma += factorial;
                }
            }
        }
        System.out.println("La suma de los factoriales es: " + suma);
        keyboard.close();
    }

}
