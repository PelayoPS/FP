package Actividades;

import java.util.Scanner;

public class Actividad25 {
    public static void main(String[] args) {
        /*
         * Calcula la media para una cantidad indefinida de números. El 
         * programa parará de pedir números cuando se le introduzca un número 
         * negativo, en ese momento nos dará la media de los números 
         * introducidos hasta ese momento y la cantidad de los mismos ( si 
         * incluir al negativo que usamos para acabar)
         */
        Scanner keyboard = new Scanner(System.in);
        int num = 0, sum = 0, count = 0;
        double avg = 0;
        System.out.println("Introduce un número:");
        num = keyboard.nextInt();
        while (num >= 0) {
            sum += num;
            count++;
            System.out.println("Introduce un número:");
            num = keyboard.nextInt();
        }
        avg = (double) sum / count;
        System.out.println("La media es: " + avg);
        keyboard.close();
    }
}
