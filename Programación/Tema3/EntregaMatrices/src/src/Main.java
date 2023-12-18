package src;

public class Main {
    public static void main(String[] args) {
        int[][] mat_mccdu = { { 5, 6, 2, -4 },
                { 6, 7, 8, 3 },
                { 10, 4, -5, 9 },
                { 4, -7, 1, 12 } };
        int[][] not_mat_mccdu = { { 5, 6, 2, -4 },
                { 6, 7, 8, 3 },
                { 10, 4, -5, 9 },
                { 4, -7, 12, 1 } };

        // mat_mccdu
        if (LibreriaMatrices.mccud(mat_mccdu)) {
            System.out.println("mat_mccdu es mccud");
        } else {
            System.out.println("mat_mccdu no es mccud");
        }
        // not_mat_mccdu
        if (LibreriaMatrices.mccud(not_mat_mccdu)) {
            System.out.println("not_mat_mccdu es mccud");
        } else {
            System.out.println("not_mat_mccdu no es mccud");
        }

        int[] fila_distintos = { 1, 2, 3, 4, 5 };
        int[] fila_no_distintos = { 1, 2, 3, 4, 5, 1 };
        // fila_distintos
        if (LibreriaMatrices.todosDistintos(fila_distintos)) {
            System.out.println("fila_distintos tiene todos los elementos distintos");
        } else {
            System.out.println("fila_distintos no tiene todos los elementos distintos");
        }
        // fila_no_distintos
        if (LibreriaMatrices.todosDistintos(fila_no_distintos)) {
            System.out.println("fila_no_distintos tiene todos los elementos distintos");
        } else {
            System.out.println("fila_no_distintos no tiene todos los elementos distintos");
        }

        int[] fila = { 1, 2, 3, 4, 5 };
        int max = 5;

        // maximo
        if (LibreriaMatrices.maximo(fila) == max) {
            System.out.println("maximo(fila) devuelve el máximo de la fila");
        } else {
            System.out.println("maximo(fila) no devuelve el máximo de la fila");
        }

        int postMax = 4;

        // posMax
        if (LibreriaMatrices.posMax(fila) == postMax) {
            System.out.println("posMax(fila) devuelve la posición del máximo de la fila");
        } else {
            System.out.println("posMax(fila) no devuelve la posición del máximo de la fila");
        }


    }
}
