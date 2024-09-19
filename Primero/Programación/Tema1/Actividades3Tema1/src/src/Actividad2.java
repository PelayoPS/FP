package src;

import java.util.Scanner;

public class Actividad2 {
    public static void main(String[] args) {
        /*
         * Diseña un programa modular que calcule el área y la circunferencia de
         * un circulo cuyo radio se debe preguntar al usuario.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el radio del circulo: ");
        int radio = keyboard.nextInt();
        keyboard.close();
        System.out.println("El perimetro del circulo es: " + perimetro(radio));
        System.out.println("La superficie del circulo es: " + superficie(radio));
    }

    /**
     * función que calcula el perímetro de un circulo dado su radio
     * @param radio
     * @return
     */
    public static double perimetro(int radio) {
        return 2 * Math.PI * radio;
    }

    /**
     * función que calcula la superficie de un circulo dado su radio
     * @param radio
     * @return
     */
    public static double superficie(int radio) {
        return Math.PI * Math.pow(radio, 2);
    }

}
