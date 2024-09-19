package Actividades;

import java.util.Scanner;

public class Actividad22 {
    public static void main(String[] args) {
        // Suma de los números impares del 1 al 20
        int sumaImpares = 0;
        for (int i = 1; i <= 20; i++) {
            if (i % 2 != 0) {
                sumaImpares += i;
            }
        }
        System.out.println("La suma de los números impares del 1 al 20 es: " + sumaImpares);

    }

    public static void main2(String[] args) {
        // Suma de los números impares entre dos números ingresados por el usuario
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Ingrese el primer número: ");
        int num1 = keyboard.nextInt();
        System.out.print("Ingrese el segundo número: ");
        int num2 = keyboard.nextInt();
        keyboard.close();

        int sumaImpares = 0;
        int menor = Math.min(num1, num2);
        int mayor = Math.max(num1, num2);
        for (int i = menor; i <= mayor; i++) {
            if (i % 2 != 0) {
                sumaImpares += i;
            }
        }
        System.out.printf("La suma de los números impares entre %s y %s es: ", menor, mayor, sumaImpares);

    }
}
