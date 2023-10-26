package Actividades;

import java.util.ArrayList;
import java.util.List;

public class Actividad32 {
    public static void main(String[] args) {
        /*
         * Reutiliza el ejercicio anterior para mostrar los números primos que hay
         * del 1 al 100
         */
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 100; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                numbers.add(i);
            }
        }
        System.out.println("Los números primos del 1 al 100 son: " + numbers);
    }
}
