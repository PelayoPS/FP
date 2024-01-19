package src;

import java.util.Scanner;

public class Ejercicio4 {

    /**
     * Pide una dimensión por teclado y genera una matriz de ceros de esa dimensión
     * 
     * @return int[][]: matriz de ceros
     */
    public static int[][] generarTablero() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce la dimensión del tablero:");
        int dimension = keyboard.nextInt();
        int[][] tablero = new int[dimension][dimension];
        keyboard.close();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = 0;
            }
        }
        return tablero;

    }

    /**
     * Coloca un barco en una fila mirando si hay espacio suficiente
     * si la posición está ocupada, no coloca el barco
     * @param int[][] tablero: tablero de juego
     * @param int fila: fila en la que colocar el barco
     * @param int columna: columna en la que colocar el barco
     * @param int tam: tamaño del barco
     */
    public static void colocarBarcoFila(int[][] tablero, int fila, int columna, int tam){
        // Comprueba si hay espacio suficiente
        if (columna + tam > tablero.length) {
            System.out.println("No hay espacio suficiente");
        } else {
            for (int i = 0; i < tam; i++) {
                if (tablero[fila][columna + i] != 0) {
                    System.out.println("Posición ocupada");
                    return;
                }
            }
            // Coloca el barco
            System.out.println("Barco colocado");
            for (int i = 0; i < tam; i++) {
                tablero[fila][columna + i] = 1;
            }
        }
    }

    /**
     * Coloca un barco en una columna mirando si hay espacio suficiente
     * @param int[][] tablero: tablero de juego
     * @param int fila: fila en la que colocar el barco
     * @param int columna: columna en la que colocar el barco
     * @param int tam: tamaño del barco
     */
    public static void colocarBarcoColumna(int[][] tablero, int fila, int columna, int tam){
        // Comprueba si hay espacio suficiente
        if (fila + tam > tablero.length) {
            System.out.println("No hay espacio suficiente");
        } else {
            for (int i = 0; i < tam; i++) {
                if (tablero[fila + i][columna] != 0) {
                    System.out.println("Posición ocupada");
                    return;
                }
            }
            // Coloca el barco
            System.out.println("Barco colocado");
            for (int i = 0; i < tam; i++) {
                tablero[fila + i][columna] = 1;
            }
        }
    }

    /**
     * Muestra el tablero de juego
     * @param int[][] tablero: tablero de juego
     */
    public static void mostrarTablero(int tablero[][]) {
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("|");
            for (int j = 0; j < tablero.length; j++) {
                System.out.print(tablero[i][j] + "|");
            }
            System.out.println();
        }
    }
}
