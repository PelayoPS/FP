package src;

import java.util.Scanner;

public class Actividad18 {
    public static void main(String[] args) {
        /*
         * Implementar una función se simule la devolución de monedas de una
         * máquina expendedora. El programa principal lee la cantidad de dinero
         * y la función calcula la cantidad de monedas necesarias para cubrir la
         * cantidad
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce la cantidad de dinero en euros");
        double money = keyboard.nextDouble();
        keyboard.close();
        int[] result = vendingMachine(money);
        System.out.println("Para devolver " + money + "€ se necesitan:");
        System.out.println(result[0] + " monedas de 2€");
        System.out.println(result[1] + " monedas de 1€");
        System.out.println(result[2] + " monedas de 50cent");
        System.out.println(result[3] + " monedas de 20cent");
        System.out.println(result[4] + " monedas de 10cent");
        System.out.println(result[5] + " monedas de 5cent");
        System.out.println(result[6] + " monedas de 2cent");
        System.out.println(result[7] + " monedas de 1cent");


    }

    private static int[] vendingMachine(double money) {
        int[] result = new int[8];
        int[] coins = { 200, 100, 50, 20, 10, 5, 2, 1 };
        //200=2€, 100=1€, 50=50cent, 20=20cent, 10=10cent, 5=5cent, 2=2cent, 1=1cent
        money = money * 100;
        for (int i = 0; i < coins.length; i++) {
            result[i] =  (int) (money / coins[i]);
            System.out.println(result[i]);
            money = money % coins[i];
        }
        return result;
    }
}
