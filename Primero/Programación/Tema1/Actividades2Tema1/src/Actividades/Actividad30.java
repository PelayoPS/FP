package Actividades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actividad30 {
    public static void main(String[] args) {
        /*
         * Diseñar el algoritmo que indique el mayor de una serie de números que
         * introducimos por teclado. Crea dos versiones: en la primera la
         * cantidad está definida en la segunda será indefinida.
         */
        Scanner keyboard = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<Integer>();
        String answer = "s";
        while (answer.equals("s")) {
            System.out.print("Introduzca un número: ");
            numbers.add(keyboard.nextInt());
            System.out.print("¿Desea introducir otro número? (s/n): ");
            answer = keyboard.next();
        }
        keyboard.close();
        int max = numbers.stream().max(Integer::compare).get();
        System.out.println("El número mayor es: " + max);

    }

}
