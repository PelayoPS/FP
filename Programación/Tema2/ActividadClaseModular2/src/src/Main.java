package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int num1 = 0;
        int num2 = 0;
        System.out.println("Introduce el primer número");
        num1 = keyboard.nextInt();
        System.out.println("Introduce el segundo número");
        num2 = keyboard.nextInt();
        int option = Libreria.menu();
        keyboard.close();
        switch (option) {
            case 1:
                if (Libreria.numeroDeDigitos(1) == Libreria.numeroDeDigitos(2)
                        && Libreria.numeroInverso(num2) == num1) {
                    System.out.println("Los números son cuñados");
                } else {
                    System.out.println("Los números no son cuñados");
                }
                break;
            case 2:
                if (Libreria.compartenDigito(num1, num2)) {
                    System.out.println("Los números son familia");
                } else {
                    System.out.println("Los números no son familia");
                }                
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }


    }
}
