package src;

public class Main {
    public static void main(String[] args) {
        System.out.println("==Leer==");
        int[] v = LibreriaVectores.leerVector();
        System.out.println("==Imprimir==");
        LibreriaVectores.imprimirVector(v);
        System.out.println("==Buscar repetidos==");
        LibreriaVectores.mostrarRepetidos(v);
        System.out.println("==Buscar ausentes==");
        LibreriaVectores.buscarAusentes(v);
        System.out.println("==Valor promedio==");
        System.out.println("El promedio es: " + LibreriaVectores.promedio(v));
    }
}
