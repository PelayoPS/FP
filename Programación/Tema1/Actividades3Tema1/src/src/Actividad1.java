package src;

import java.util.Scanner;

public class Actividad1 {
    public static void main(String[] args) {
        /*
         * Realiza un programa modular que calcule la superficie y el perímetro
         * de un cuadrado cuyo lado pediremos por teclado
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el lado del cuadrado: ");
        int lado = keyboard.nextInt();
        keyboard.close();
        System.out.println("El perimetro del cuadrado es: " + perimetro(lado));
        System.out.println("La superficie del cuadrado es: " + superficie(lado));
    }

    /**
     * función que calcula el perímetro de un cuadrado dado su lado
     * @param lado
     * @return
     */
    public static int perimetro(int lado) {
        return lado * 4;
    }

    /**
     * función que calcula la superficie de un cuadrado dado su lado
     * @param lado
     * @return
     */
    public static int superficie(int lado) {
        return lado * lado;
    }


}
