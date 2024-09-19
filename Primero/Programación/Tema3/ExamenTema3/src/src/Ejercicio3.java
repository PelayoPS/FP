package src;

public class Ejercicio3 {

    /**
     * calcula la primera derivada a partir de la media de los valores de la matriz
     * en la fila i multiplicados por el elemento i del vector de constantes
     * 
     * @param int[][] matriz: matriz de enteros
     * @param int[]   ctes: vector de constantes
     * @return int[]: vector con la primera derivada de la matriz
     */
    public static int[] calcularPrimeraDerivada(int[][] matriz, int[] ctes) {

        int[] primeraDerivada = new int[ctes.length];

        for (int i = 0; i < matriz.length; i++) {
            int media = 0;
            for (int j = 0; j < matriz[i].length; j++) {
                media += matriz[i][j] * ctes[i];
            }
            media /= matriz[i].length;
            primeraDerivada[i] = media;
        }
        return primeraDerivada;
    }
}
