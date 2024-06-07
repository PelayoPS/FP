package Actividades;

import java.util.Scanner;

public class Actividad24 {
    public static void main(String[] args){
        /*
         * Calcula la media de 10 números pedidos por teclado
         */
        //Crea un array de 10 números
        int[] nums = new int[10];
        //Llena el array con los números preguntando por teclado
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce 10 números");
        for(int i = 0; i < nums.length; i++) {
            nums[i] = keyboard.nextInt();
        }
        keyboard.close();
        //Suma todos los números
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //Calcula la media
        float media = sum / nums.length;
        System.out.println("La media de los números es " + media);
    }

    public static void main2(String[] args){
        /*
         * Calcula la media de x números preguntando por teclado x y los números
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("¿Cuántos números quieres introducir?");
        int total = keyboard.nextInt();
        System.out.println("Introduce " + total + " números");
        //Crea un array de x números
        int[] nums = new int[total];
        //Llena el array con los números preguntando por teclado
        for(int i = 0; i < nums.length; i++) {
            nums[i] = keyboard.nextInt();
        }
        keyboard.close();
        //Suma todos los números
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        //Calcula la media
        float media = sum / nums.length;
        System.out.println("La media de los números es " + media);

    }
}
