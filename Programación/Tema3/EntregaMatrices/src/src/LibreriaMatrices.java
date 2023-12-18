package src;

import java.util.Scanner;

/**
 * clase que contiene las funciones relacionadas con matrices
 */
public class LibreriaMatrices {

    /**
     * determina si una matriz es mccud
     * condiciones:
     * -todos los elementos de cada fila son distintos
     * -el mayor elemento de cada fila es menor que el mayor elemento de la
     * siguiente
     * fila
     * -la diferencia entre dos máximos de filas consecutivas es constante
     * -en cada columna sólo puede aparecer un máximo de cada fila
     * 
     * @param matriz: int[][] matriz a comprobar
     * @return boolean true si es mccud, false si no lo es
     */
    public static boolean mccud(int[][] matriz) {
        boolean result = true;
        // comprobamos que los elementos de cada fila son distintos
        for (int i = 0; i < matriz.length; i++) {
            result = result && todosDistintos(matriz[i]);// si alguno de los elementos de la fila no es
                                                         // distinto, result será false
        }

        // comprobamos que el mayor elemento de cada fila es menor que el mayor
        // elemento de la siguiente fila
        for (int i = 0; i < matriz.length - 1; i++) {
            result = result && maximo(matriz[i]) < maximo(matriz[i + 1]);
        }

        // comprobamos que la diferencia entre dos máximos de filas consecutivas es
        // constante
        int diferencia = maximo(matriz[1]) - maximo(matriz[0]);
        for (int i = 1; i < matriz.length - 1; i++) {
            result = result && diferencia == maximo(matriz[i + 1]) - maximo(matriz[i]);
        }

        // comprobamos que en cada columna sólo puede aparecer un máximo de cada fila
        // crea una fila con los máximos de cada columna
        // si para cada elemento de la fila element!=maximoFila(element) result será
        // false
        int[] maximosColumnas = new int[matriz[0].length];
        for (int i = 0; i < maximosColumnas.length; i++) {
            int[] columna = new int[matriz.length];
            for (int j = 0; j < columna.length; j++) {
                columna[j] = matriz[j][i];
            }
            maximosColumnas[i] = maximo(columna);
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < maximosColumnas.length; j++) {
                result = result && matriz[i][j] != maximosColumnas[j];
            }
        }

        return result;
    }

    /**
     * devuelve el máximo de una fila
     * 
     * @param fila: int[] fila de la matriz
     * @return int máximo de la fila
     */
    public static int maximo(int[] fila) {
        int maximo = fila[0];
        for (int i = 1; i < fila.length; i++) {
            if (fila[i] > maximo) {
                maximo = fila[i];
            }
        }
        return maximo;
    }

    /**
     * devuelve la columna donde está el máximo de una fila
     * 
     * @param fila: int[] fila de la matriz
     * @return int mínimo de la fila
     */
    public static int posMax(int[] fila) {
        int maximo = fila[0];
        int postMax = 0;
        for (int i = 1; i < fila.length; i++) {
            if (fila[i] > maximo) {
                maximo = fila[i];
                postMax = i;
            }
        }
        return postMax;
    }

    /**
     * imprime por pantalla una matriz
     * 
     * @param m: int[][] matriz a imprimir
     */
    public static void imprimeMatriz(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            imprimeFila(m[i]);
        }
    }

    /**
     * lee por teclado los valores de una matriz
     * sabiendo sus dimensiones 
     * @param m: int[][] matriz de la que se sacan las dimensiones
     */
    public static void leerMatriz(int[][] m) {
        Scanner keyboard = new Scanner(System.in);
        int filas = m.length;
        int columnas = m[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; i++) {
                System.out.println("Introduce el elemento " + i + " " + j);
                m[i][j] = keyboard.nextInt();
            }
        }

        keyboard.close();
        imprimeMatriz(m);
    }

    /**
     * determina si los elementos de una fila son distintos
     * 
     * @param fila: int[] fila de la matriz
     * @return boolean true si los elementos son distintos, false si no lo son
     */
    public static boolean todosDistintos(int[] fila) {
        boolean result = true;
        for (int i = 0; i < fila.length; i++) {
            for (int j = i + 1; j < fila.length; j++) {
                if (fila[i] == fila[j]) {
                    result = false;
                }
            }
        }
        return result;
    }

    // =================== FUNCIONES PRIVADAS ===================

    /**
     * imprime por pantalla una fila
     * con formato [ 1 2 3 ]
     * 
     * @param fila: int[] fila a imprimir
     */
    private static void imprimeFila(int[] fila) {
        System.out.print("[ ");
        for (int i = 0; i < fila.length; i++) {
            System.out.print(fila[i] + " ");
        }
        System.out.println("]");
    }

}
