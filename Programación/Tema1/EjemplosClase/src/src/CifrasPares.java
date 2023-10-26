package src;

import java.util.Scanner;

public class CifrasPares {
    public static void main(String[] args) {
        // Contar las cifras pares de un número
        Scanner keyboard = new Scanner(System.in);
        int number, cifrasPares = 0;
        System.out.println("Introduce un número entero: ");
        number = keyboard.nextInt();
        while (number > 0) {
            if ((number % 10) % 2 == 0) {
                cifrasPares++;
            }
            number /= 10;
        }
        System.out.println("El número tiene " + cifrasPares + " cifras pares");
        keyboard.close();
    }
    
}
