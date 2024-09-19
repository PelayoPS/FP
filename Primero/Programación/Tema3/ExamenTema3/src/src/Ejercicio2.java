package src;

public class Ejercicio2 {

    /**
     * Muestra por pantalla los avisos de los vuelos
     * se mostrará un aviso cuando los aviones no respeten la distancia de seguridad
     * se conoce la posición inicial de cada avión y su tamaño
     * para ello se utiliza una matriz de enteros donde se guarda en una posición
     * el valor de casillas ocupadas por el avión
     * si en inicial+tamaño se encuentra otro avión se mandará aviso
     * 
     * @param int[][] vuelos: matriz de enteros con los vuelos
     */
    public static void mostrarAviso(int[][] vuelos) {
        // Recorre la matriz
        for (int i = 0; i < vuelos.length; i++) {
            for (int j = 0; j < vuelos[i].length; j++) {
                // busca un avión
                if (vuelos[i][j] != 0) {
                    // avión encontrado
                    // comprueba si hay otro avión en la posición inicial+tamaño
                    // loop desde inicial hasta inicial+valor
                    int[] fila = vuelos[i];
                    for (int k = j+1; k < j + fila[j]; k++) {
                        if (fila[k] != 0) {
                            // hay otro avión
                            int x = i + 1;
                            int y = k + 1;
                            System.out.println("WARNING(" + x + "," + y + ")");
                        }
                    }

                }
            }
        }

    }
}
