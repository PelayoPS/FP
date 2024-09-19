package src;

public class Actividad7 {
    public static void main(String[] args) {
        /*
         * Escribir una función que muestre las permutaciones con repetición
         * que pueden hacerse con los números 1,2,3 y 4. También que muestre
         * el número de estas.
         */
        //bucle que muestra las permutaciones
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++){
                for (int k = 1; k < 5; k++){
                    for (int l = 1; l < 5; l++){
                        System.out.print(i + " " + j + " " + k + " " + l + "  ");
                    }
                    System.out.println();
                }
            }
            
        }
    }

    
}
