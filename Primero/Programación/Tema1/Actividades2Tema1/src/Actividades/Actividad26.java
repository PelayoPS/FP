package Actividades;

import java.util.Scanner;

public class Actividad26 {
    public static void main(String[] args) {
        /*
         * Mostrar el mensaje “¿Desea terminar (s/n)?” el programa terminará
         * cuando el usuario pulse ‘S’ o ‘s’, en otro caso el programa continuará
         * indefinidamente.
         */
        Scanner keyboard = new Scanner(System.in);
        char stop = 'n';
        while (stop != 's' && stop != 'S') {
            System.out.print("¿Desea terminar (s/n)? ");
            stop = keyboard.next().charAt(0);
        }
        keyboard.close();
    }
}
