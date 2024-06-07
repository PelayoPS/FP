package Actividades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Actividad12 {

	public static void main(String[] args) {
		/**
		 * Determinar si 5 números pedidos por teclado son consecutivos o no
		 */
		Scanner keyboard = new Scanner(System.in);
		//Los añade a la lista pidiendo cada uno en una iteracción del bucle
		List<Integer> ints = new ArrayList<Integer>();
		int num = 0;
		for(int i = 0; i < 5; i++) {
			System.out.println("Introduce el primer número");
			num = keyboard.nextInt();
			ints.add(num);
		}
		keyboard.close();
		//Los ordena para que si vienen desordenados pueda verse un salto de 1
		ints.sort(null);
		//mira el salto de uno y si no lo hay no son consecutivos
		for(int i = 0; i<ints.size()-1;i++) {
			if(ints.get(i+1) - ints.get(i) != 1) {
				System.out.println("No son números consecutivos");
				System.exit(0);
			}
		}
		System.out.println("Son números consecutivos");
		
	}
}
