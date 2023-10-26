package src;

import java.util.Scanner;

public class Actividad3 {
    public static void main(String[] args) {
        /*
         * Implementar una función que reciba como parámetro un número y
         * retorne un valor lógico indicando si es positivo o negativo. El programa
         * principal leer el numero por teclado y llama a la función. Muestra un
         * mensaje en función del valor de retorno.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un numero: ");
        int numero = keyboard.nextInt();
        keyboard.close();
        if (esPositivo(numero)) {
            System.out.println("El numero es positivo");
        } else {
            System.out.println("El numero es negativo");
        }
    }

    /**
     * función que comprueba si un numero es positivo o negativo
     * @param numero
     * @return
     */
    public static boolean esPositivo(int numero) {
        return numero >= 0;
    }

}
