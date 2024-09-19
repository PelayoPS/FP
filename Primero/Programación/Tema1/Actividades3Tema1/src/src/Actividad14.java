package src;

public class Actividad14 {
    public static void main(String[] args) {
        /*
         * Reutiliza el ejercicio anterior para mostrar los números primos que hay
         * entre el 1 y el 100
         */
        for (int i = 1; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.println("El número " + i + " es primo");
            }
        }
        
    }

    private static boolean isPrime(int number) {
        boolean isPrime = true;
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                isPrime = false;
            }
        }
        return isPrime;
    }
}
