package Actividades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Actividad11 {

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
        List<Float> ordered = new ArrayList<Float>(floats);
        //Ordena de menor a mayor 
        ordered.sort(null);
        //Mayor a menor
        Collections.reverse(ordered);
        //Si ya están ordenados lo dice
        if(floats.equals(ordered)) {
        	System.out.println("Están ya ordenados: " + floats);
        } else {
        	System.out.println("No están ordenados: " + floats);
        }
		keyboard.close();
	}

	public static void main2(String[] args){
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
		keyboard.close();
		//Compara los valores para ordenarlos
		if(num > num1 && num1 > num2) {
			System.out.println("Están ordenados de mayor a menor");
		} else {
			System.out.println("No están ordenados de mayor a menor");			
		}
	}
}
