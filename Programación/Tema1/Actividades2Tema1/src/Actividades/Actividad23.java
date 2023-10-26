package Actividades;

import java.util.Scanner;

public class Actividad23 {
    public static void main(String[] args){
        /*
         * Calcula el factorial de un número entero introducido por el usuario
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce un número entero");
        int num = keyboard.nextInt();
        keyboard.close();
        //Si el número es 0 o 1 el factorial es 1
        if(num == 0 || num == 1) {
            System.out.println("El factorial de " + num + " es 1");
        } else {
            //Si no, calcula el factorial
            int factorial = 1;
            for(int i = 1; i <= num; i++) {
                factorial *= i;
            }
            System.out.println("El factorial de " + num + " es " + factorial);
        }
    }
}
