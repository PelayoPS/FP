package src;

import java.util.Scanner;

public class Ejercicio3 {
    /**
     * Programa que calcule la raíz entera positivo de un número mediante el método
     * de aproximaciones sucesivas. Es decir, se comienza con la raíz=1 y se 
     * va incrementando en 1 hasta que raíz * raíz = num hasta que se cumpla
     * raíz * raíz > num
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int num, root = 1;
        System.out.println("Introduce un número: ");
        num = keyboard.nextInt();
        keyboard.close();
        if (num < 0) {
            System.out.println("El número introducido es negativo y por lo tanto no se puede calcular la raíz");
            System.exit(0);
            
        }
        while (root * root <= num) {
            root++;
        }
        System.out.println("La raíz entera positiva de " + num + " es " + (root - 1));
    }
}
