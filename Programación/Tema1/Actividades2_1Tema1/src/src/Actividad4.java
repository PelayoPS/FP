package src;

import java.util.Scanner;

public class Actividad4 {
    public static void main(String[] args) {
        /*
         * Escribir un programa que pregunte al usuario una cantidad a invertir, el
         * interés anual y el número de años, y muestre por pantalla el capital
         * obtenido en la inversión cada año que dura la inversión.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce la cantidad a invertir: ");
        double amount = keyboard.nextDouble();
        System.out.println("Introduce el interés anual: ");
        double interest = keyboard.nextDouble();
        System.out.println("Introduce el número de años: ");
        int years = keyboard.nextInt();
        keyboard.close();
        //bucle que muestra el capital obtenido cada año
        for (int i = 1; i <= years; i++) {
            amount = amount * (1 + interest / 100);
            System.out.println("Año " + i + ": " + amount);
        }
    }
}
