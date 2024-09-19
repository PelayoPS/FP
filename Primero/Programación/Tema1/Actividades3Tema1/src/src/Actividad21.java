package src;

import java.util.Scanner;

public class Actividad21 {
    public static void main(String[] args) {
        /*
         * Realiza un programa que permita convertir modularmente números
         * binarios en decimales y viceversa.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el número usando b para binario y d para decimal");
        String number = keyboard.nextLine();
        keyboard.close();
        //comprueba si es binario o decimal
        //para ello mira que el número acabe en d o b
        if (number.charAt(number.length() - 1) == 'd') {
            //es decimal
            System.out.println("El número " + number + " en binario es " + decimalToBinary(number));
        } else if (number.charAt(number.length() - 1) == 'b') {
            //es binario
            System.out.println("El número " + number + " en decimal es " + binaryToDecimal(number));
        } else {
            System.out.println("El número introducido no está en formato correcto");
        }

    }

    private static String decimalToBinary(String number) {
        //convierte un número decimal a binario
        //para ello quita la d final y lo convierte a int
        int decimal = Integer.parseInt(number.substring(0, number.length() - 1));
        //crea un string vacío
        String binary = "";
        //mientras el número sea mayor que 0
        while (decimal > 0) {
            //añade el resto de dividir el número entre 2 al string
            binary = decimal % 2 + binary;
            //divide el número entre 2
            decimal /= 2;
        }
        //devuelve el string
        return binary+"b";
    }

    private static String binaryToDecimal(String number){
        //convierte un número binario a decimal
        //para ello quita la b final y lo convierte a int
        int binary = Integer.parseInt(number.substring(0, number.length() - 1));
        //crea un string vacío
        int decimal = 0;
        //transforma el número a decimal elevando 2 a la posición del número
        //y multiplicándolo por el número
        for (int i = 0; i < number.length() - 1; i++) {
            decimal += binary % 10 * Math.pow(2, i);
            binary /= 10;
        }
        //devuelve el string
        return decimal+"d";
    }

}
