package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Actividad8 {
    public static void main(String[] args) {
		/*
		 * Determinar si tres números pedidos por teclado están o no ordenados
		 * de mayor a menor
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce el primer número");
		float num = keyboard.nextFloat();
		System.out.println("Introduce el primer número");
		float num1 = keyboard.nextFloat();
		System.out.println("Introduce el primer número");
		float num2 = keyboard.nextFloat();
		//Mete los valores en una lista
        List<Float> floats = new ArrayList<Float>();
        floats.add(num);
        floats.add(num1);
        floats.add(num2);		
        if(isOrdered(floats)) {
        	System.out.println("Están ya ordenados: " + floats);
        } else {
        	System.out.println("No están ordenados: " + floats);
        }
		keyboard.close();
	}

    private static boolean isOrdered(List<Float> floats) {
        List<Float> ordered = new ArrayList<>(floats);
        Collections.sort(ordered);
        return floats.equals(ordered);
    }

    @SuppressWarnings("unused")
    private static boolean firstIsBigger(float num, float num1, float num2) {
        return num > num1 && num > num2;
    }
    
}
