package src;

public class Library {

    /**
     * Muestra por patalla el alumno y el curso que ha obtenido un 10
     * @param notas : int[][] matriz de notas
     */
    public static void notaMasAlta(int notas[][]){
        matrixError(notas);
        for (int i = 0; i < notas.length; i++) {
            for (int j = 0; j < notas[i].length; j++) {
                if (notas[i][j] == 10){
                    System.out.println("El alumno " + (j+1) + " del curso " + getRow(i) + " ha obtenido un 10");
                }
            }
        }
    }

    /**
     * Muestra por pantalla la nota media de cada curso según la letra que se le pase
     * @param curso : char curso que se quiere calcular la media
     * @param notas : int[][] matriz de notas
     */
    public static void calcularMediaCurso(char curso, int notas[][]){
        courseError(curso);
        matrixError(notas);
        int suma = 0;
        int contador = 0;
        for (int j = 0; j < notas[getRow(curso)].length; j++) {
            suma += notas[getRow(curso)][j];
            contador++;
        }
        System.out.println("La media del curso " + curso + " es " + (suma/contador));
    }

    /**
     * Indica el número de aprobados de un curso
     * @param curso : char curso que se quiere calcular el número de aprobados
     * @param notas : int[][] matriz de notas
     * @return int : número de aprobados del curso
     */
    public static int numeroAprobados(char curso, int notas[][]){
        courseError(curso);
        matrixError(notas);
        int contador = 0;
        for (int j = 0; j < notas[getRow(curso)].length; j++) {
            if (notas[getRow(curso)][j] >= 5){
                contador++;
            }
        }
        System.out.println("El número de aprobados del curso " + curso + " es " + contador);
        return contador;
    }

    /**
     * Indica el número de suspensos de un curso
     * @param curso : char curso que se quiere calcular el número de suspensos
     * @param notas : int[][] matriz de notas
     * @return int : número de suspensos del curso
     */
    public static int numeroSuspensos(char curso, int notas[][]){
        courseError(curso);
        matrixError(notas);
        int contador = 0;
        for (int j = 0; j < notas[getRow(curso)].length; j++) {
            if (notas[getRow(curso)][j] < 5){
                contador++;
            }
        }
        System.out.println("El número de suspensos del curso " + curso + " es " + contador);
        return contador;
    }

    /**
     * Muestra por pantalla la estadística de cada curso
     * @param notas : int[][] matriz de notas
     */
    public static void mostrarEstadistica(int notas[][]){
        matrixError(notas);
        for (int i = 0; i < notas.length; i++) {
            System.out.println("Curso " + getRow(i));
            numeroAprobados(getRow(i), notas);
            numeroSuspensos(getRow(i), notas);
        }
    }


//====== PRIVATE METHODS ======//

    /**
     * Devuelve el curso
     * @param i : int fila de la matriz
     * @return char : curso de la matriz
     */
    private static char getRow(int i){
        switch (i) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';        
            default:
                return '0';
        }
    }

    /**
     * Devuelve la fila de la matriz
     * @param curso : char curso que indica la fila de la matriz
     * @return int : fila de la matriz
     */
    private static int getRow(char curso){
        switch (curso) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;        
            default:
                return -1;
        }
    }
    
    /**
     * Comprueba que la matriz no sea nula y que tenga 4 filas y 10 columnas
     * @param notas
     * @throws IllegalArgumentException : si la matriz es nula o no tiene 4 filas o 10 columnas
     */
    private static void matrixError(int[][] notas) {
        if (notas == null) {
            throw new IllegalArgumentException("La matriz no puede ser nula");
        }
        if (notas.length != 4) {
            throw new IllegalArgumentException("La matriz debe tener 4 filas");
        }
        for (int[] is : notas) {
            if (is.length != 10) {
                throw new IllegalArgumentException("La matriz debe tener 10 columnas");
            }
        }
    }

    /**
     * Comprueba que el curso sea A, B, C o D
     * @param curso : char curso que se quiere comprobar
     * @throws IllegalArgumentException : si el curso no es A, B, C o D
     */
    private static void courseError(char curso) {
        if (curso != 'A' && curso != 'B' && curso != 'C' && curso != 'D') {
            throw new IllegalArgumentException("El curso debe ser A, B, C o D");
        }
    }

    
}
