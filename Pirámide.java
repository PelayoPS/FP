
import java.util.Scanner;

public class Pirámide {
    public static void main(String[] args) {
        /**
         * Escribe la siguiente figura según el número de filas como parámetro
         * dos espacios en blanco entre cada número
         * EJEMPLO CON 8 FILAS:
         *                         1
         *                      3  3  3
         *                   5  5  5  5  5
         *                7  7  7  7  7  7  7
         *             9  9  9  9  9  9  9  9  9
         *          11 11 11 11 11 11 11 11 11 11 11
         *       13 13 13 13 13 13 13 13 13 13 13 13 13
         *    15 15 15 15 15 15 15 15 15 15 15 15 15 15 15
         *
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el número de filas");
        int filas = keyboard.nextInt();
        int espacios = filas * 3;
        int numero = 1;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < espacios; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < numero; j++) {
                System.out.print(numero + " ");
                if(numero <= 9)
                    System.out.print(" ");
            }
            System.out.println();
            numero += 2;
            espacios -= 3;
        }
        keyboard.close();
    }
}
        
        
      