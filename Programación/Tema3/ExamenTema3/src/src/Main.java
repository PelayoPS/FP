package src;

public class Main {

    // Ejercicio 1 data
    private static int[] v = { 4, 6, 9, 3, 19, 10, 15, 2, 14, 12 };

    // Ejercicio 3 data
    private static int[][] matriz = { { 5, 7, 5, 4, 2 },
            { 5, 3, 9, 6, 5 },
            { 1, 4, 7, 7, 6 },
            { 8, 6, 2, 9, 4 } };
    private static int[] ctes = { 6, 2, 6, 2 };

    public static void main(String[] args) {
        // Ejercicio 1
        System.out.println("Ejercicio 1 output:");
        Ejercicio1.mostrarPuntosLuz(v);
        System.out.println("Expected:");
        System.out.println("Punto de luz en la posición 2 con valor 9");
        System.out.println("Punto de luz en la posición 6 con valor 15");

        // Ejercicio 3
        System.out.println("Ejercicio 3 output:");
        int[] primeraDerivada = Ejercicio3.calcularPrimeraDerivada(matriz, ctes);
        for (int i = 0; i < primeraDerivada.length; i++) {
            System.out.println("Fila " + i + ": " + primeraDerivada[i]);
        }
        System.out.println("Expected:");
        System.out.println("Fila 0: 27");
        System.out.println("Fila 1: 11");
        System.out.println("Fila 2: 30");
        System.out.println("Fila 3: 11");

        // Ejercicio 4
        System.out.println("Ejercicio 4 output:");
        int[][] tablero = Ejercicio4.generarTablero();// tamaño 9
        // fila valida
        Ejercicio4.colocarBarcoFila(tablero, 0, 0, 3);
        // fila invalida
        Ejercicio4.colocarBarcoFila(tablero, 0, 7, 3);
        // fila valida pero posicion ocupada
        Ejercicio4.colocarBarcoFila(tablero, 0, 0, 3);
        // columna valida
        Ejercicio4.colocarBarcoColumna(tablero, 1, 0, 3);
        // columna invalida
        Ejercicio4.colocarBarcoColumna(tablero, 7, 0, 3);
        // columna valida pero posicion ocupada
        Ejercicio4.colocarBarcoColumna(tablero, 0, 0, 3);
        Ejercicio4.mostrarTablero(tablero);
        System.out.println("Expected:");
        System.out.println("Barco colocado");
        System.out.println("No hay espacio suficiente");
        System.out.println("Posición ocupada");
        System.out.println("Barco colocado");
        System.out.println("No hay espacio suficiente");
        System.out.println("Posición ocupada");
        System.out.println("|1|1|1|0|0|0|0|0|0|\n" +
                "|1|0|0|0|0|0|0|0|0|\n" +
                "|1|0|0|0|0|0|0|0|0|\n" +
                "|1|0|0|0|0|0|0|0|0|\n" +
                "|0|0|0|0|0|0|0|0|0|\n" +
                "|0|0|0|0|0|0|0|0|0|\n" +
                "|0|0|0|0|0|0|0|0|0|\n" +
                "|0|0|0|0|0|0|0|0|0|\n" +
                "|0|0|0|0|0|0|0|0|0|");
        ;

    }

}
