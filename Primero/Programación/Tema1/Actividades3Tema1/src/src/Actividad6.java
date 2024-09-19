package src;

import java.util.Scanner;

public class Actividad6 {
    public static void main(String[] args) {
        /*
         * Determinar, con un programa modular, si un número real pedido por
         * teclado tiene decimales o no.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un numero: ");
        double numero = keyboard.nextDouble();
        keyboard.close();
        if (tieneDecimales(numero)) {
            System.out.println("El numero " + numero + " tiene decimales");
        } else {
            System.out.println("El numero " + numero + " no tiene decimales");
        }
    }

    /**
     * función que comprueba si un numero tiene decimales o no
     * @param numero
     * @return
     */
    public static boolean tieneDecimales(double numero) {
        return (numero + "").replace(".", "|").split("|").length > 1;
    }

}
