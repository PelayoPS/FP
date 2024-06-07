package src;

import java.util.Scanner;

public class Actividad9 {
    public static void main(String[] args) {
        /*
        * Determinar el número de cifras de un número entero
        */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero");
        int num = keyboard.nextInt();
        String numString = String.valueOf(num);
        System.out.println("El número " + num + " tiene " + numString.length() + " cifras");
        keyboard.close();
    }
    
}
