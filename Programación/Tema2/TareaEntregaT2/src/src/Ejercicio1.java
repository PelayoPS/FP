package src;

import java.util.Scanner;

public class Ejercicio1 {

    /**
     * Función main que comprueba si el dni es correcto
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el dni: ");
        int dni = keyboard.nextInt();
        if (comprobarDNI(dni)) {
            System.out.println("Introduce la letra del dni: ");
            char letter = keyboard.next().charAt(0);
            System.out.println(dniValido(dni, letter));
        } else {
            System.out.println("El dni no es correcto");
        }
        keyboard.close();
    }

    /**
     * Comprueba si el dni es positivo y tiene 8 digitos
     * 
     * @param dni
     * @return
     */
    public static boolean comprobarDNI(int dni) {
        return (dni > 0 && dni <= 99999999 && dni > 9999999) ? true : false;
    }

    /**
     * Devuelve la letra del dni según el módulo del dni entre 23
     * sacándola de un String con todas las letras ordenadas
     * 
     * @param dni
     * @return
     */
    public static char letraDni(int dni) {
        String charString = "TRWAGMYFPDXBNJZSQVHLCKE";
        int module = dni % 23;
        char letter = charString.charAt(module);
        return letter;
    }

    /**
     * Comprueba si el dni es correcto
     * Devuelve un String con el resultado de la comprobación
     * @param dni
     * @param letter
     * @return
     */
    public static String dniValido(int dni, char letter) {
        return (comprobarDNI(dni) && letraDni(dni) == letter) ? "El dni es correcto" : "El dni no es correcto";
    }
    

}
