package src;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase que contiene las funciones relacionadas con vectores
 */
public class LibreriaVectores {

    /**
     * Función que lee el tamaño de un vector y pide sus datos al
     * usuario por teclado
     * 
     * @return int[] v: vector de enteros
     */
    public static int[] leerVector() {
        int n;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el tamaño del vector: ");
        n = keyboard.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Introduce el elemento " + (i + 1) + ": ");
            v[i] = keyboard.nextInt();
        }
        keyboard.close();
        return v;
    }

    /**
     * Función que imprime por pantalla los elementos de un vector
     * 
     * @param int[]: v vector de enteros
     */
    public static void imprimirVector(int[] v) {
        for (int i = 0; i < v.length; i++) {
            System.out.println("Elemento " + (i + 1) + ": " + v[i]);
        }
    }

    /**
     * Función que recibe un vector y devuelve el número de
     * veces que cada elemento aparece repetido
     * 
     * @param int[]: v vector de enteros
     */
    public static void mostrarRepetidos(int[] v) {
        int[] repetidos = new int[v.length];// vector auxiliar para contar los repetidos
        for (int i = 0; i < v.length; i++) {// bulce sobre el vector
            if (repetidos[i] == 0) {// si el elemento no se ha contado
                for (int j = i + 1; j < v.length; j++) {// bucle sobre el vector a partir de la posición i+1
                    if (v[i] == v[j]) {// si el elemento se repite
                        repetidos[i]++;// incrementamos el contador
                        repetidos[j]++;// incrementamos el contador
                        // se guarda el número de veces para cada posición del vector
                    }
                }
            }
        }
        // mostramos los elementos que se repiten
        for (int i = 0; i < repetidos.length; i++) {
            if (repetidos[i] > 0) {
                System.out.println("El elemento " + v[i] + " se repite " + repetidos[i] + " veces");
            }
        }

    }

    /**
     * Función que muestra por pantalla los elementos que estén entre el
     * max y el min y que no se encuentren en el vector
     * ejemplo: v = {1,2,3,4,8,9,10}
     * min = 1
     * max = 10
     * resultado = 5,6,7
     * 
     * @param int[]: v vector de enteros
     */
    public static void buscarAusentes(int[] v) {
        int min = buscarMinimo(v);// se inicializa el mínimo con el primer elemento del vector
        int max = buscarMaximo(v);// se inicializa el máximo con el primer elemento del vector
        // bucle sobre el vector buscando valores que estén entre el min y el max y que
        // no estén en el vector
        System.out.println(min + " " + max);
        for(int i=min; i<max; i++){
            System.out.println(i);
            if(!Arrays.asList(v).contains(i)){
                System.out.println("El elemento " + i + " no está en el vector");
            }
        }
    }

    /**
     * Busca el promedio de los elementos de un vector
     * para ello se calcula el valor medio como (max+min)/2
     * y se busca el elemento más cercano a ese valor
     * siendo |valorMedio - elemento| mínimo
     * 
     * @param int[]: v vector de enteros
     * @return int: promedio valor medio del vector
     */
    public static int promedio(int[] v) {
        int min = buscarMinimo(v);// se inicializa el mínimo con el primer elemento del vector
        int max = buscarMaximo(v);// se inicializa el máximo con el primer elemento del vector
        int valorMedio = (min + max) / 2;// se calcula el valor medio
        int distancia = Math.abs(valorMedio - v[0]);// se inicializa la distancia con el primer elemento del vector
        int promedio = v[0];// se inicializa el promedio con el primer elemento del vector
        for (int i = 0; i < v.length; i++) {// se recorre el vector
            if (Math.abs(valorMedio - v[i]) < distancia) {// si la distancia es menor que la distancia anterior
                distancia = Math.abs(valorMedio - v[i]);// se actualiza la distancia
                promedio = v[i];// se actualiza el promedio
            }
        }
        return promedio;
    }

    // =================== FUNCIONES PRIVADAS ===================

    /**
     * Función que recibe un vector y devuelve el valor mínimo
     * 
     * @param int[] v: vector de enteros
     * @return int min: valor mínimo del vector
     */
    private static int buscarMinimo(int[] v) {
        int min = v[0];// se inicializa el mínimo con el primer elemento del vector
        // buscamos el valor mínimo del vector
        for (int i = 0; i < v.length; i++) {
            if (v[i] < min) {
                min = v[i];// cuando se encuentra un valor menor que el mínimo, se actualiza
            }
        }
        return min;
    }

    /**
     * Función que recibe un vector y devuelve el valor máximo
     * 
     * @param int[] v: vector de enteros
     * @return int max: valor máximo del vector
     */
    private static int buscarMaximo(int[] v) {
        int max = v[0];// se inicializa el máximo con el primer elemento del vector
        // buscamos el valor máximo del vector
        for (int i = 0; i < v.length; i++) {
            if (v[i] > max) {
                max = v[i];// cuando se encuentra un valor mayor que el máximo, se actualiza
            }
        }
        return max;
    }

}
