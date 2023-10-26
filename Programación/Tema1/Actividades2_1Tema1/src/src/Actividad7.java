package src;

public class Actividad7 {
    public static void main(String[] args) {
        /*
         * Escribir una función que muestre las permutaciones con repetición
         * que pueden hacerse con los números 1,2,3 y 4. También que muestre
         * el número de estas.
         */
        int[] numbers = {1, 2, 3, 4};
        //bucle que muestra las permutaciones
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++){
                for (int k = 0; k < numbers.length; k++){
                    for (int l = 0; l < numbers.length; l++){
                        System.out.print(numbers[i] + " " + numbers[j] + " " + numbers[k] + " " + numbers[l] + "  ");
                    }
                    System.out.println();
                }
            }
            
        }
    }

    
}
