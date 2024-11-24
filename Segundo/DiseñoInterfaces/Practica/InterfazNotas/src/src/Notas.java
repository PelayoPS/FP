package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Notas que maneja una lista de notas y realiza varias operaciones sobre ellas.
 */
public class Notas {
    private List<String> notas;

    /**
     * Constructor que inicializa la lista de notas.
     */
    public Notas() {
        this.notas = new ArrayList<>();
    }

    /**
     * Obtiene la lista de notas.
     * 
     * @return la lista de notas.
     */
    public List<String> obtenerNotas() {
        return notas;
    }

    /**
     * Calcula el promedio de las notas.
     * 
     * @return el promedio de las notas, o 0 si todas las notas son -1.
     */
    public double calcularPromedio() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1)) {
            return 0;
        }

        double suma = 0;
        for (String nota : notas) {
            suma += Double.parseDouble(nota);
        }
        return suma / notas.size();
    }

    /**
     * Calcula la desviación estándar de las notas.
     * 
     * @return la desviación estándar de las notas, o 0 si todas las notas son -1.
     */
    public double calcularDesviacion() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1)) {
            return 0;
        }
        double promedio = calcularPromedio();
        double suma = 0;
        for (String nota : notas) {
            suma += Math.pow(Double.parseDouble(nota) - promedio, 2);
        }
        return Math.sqrt(suma / notas.size());
    }

    /**
     * Calcula la nota menor.
     * 
     * @return la nota menor, o 0 si todas las notas son -1.
     */
    public double calcularMenor() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1)) {
            return 0;
        }
        double menor = Double.parseDouble(notas.get(0));
        for (String nota : notas) {
            double notaDouble = Double.parseDouble(nota);
            if (notaDouble < menor) {
                menor = notaDouble;
            }
        }
        return menor;
    }

    /**
     * Calcula la nota mayor.
     * 
     * @return la nota mayor, o 0 si todas las notas son -1.
     */
    public double calcularMayor() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1)) {
            return 0;
        }
        double mayor = Double.parseDouble(notas.get(0));
        for (String nota : notas) {
            double notaDouble = Double.parseDouble(nota);
            if (notaDouble > mayor) {
                mayor = notaDouble;
            }
        }
        return mayor;
    }

    /**
     * Calcula la suma de las notas.
     * 
     * @return la suma de las notas, o 0 si todas las notas son -1.
     */
    public double opSuma() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1)) {
            return 0;
        }
        double suma = 0.0;

        for (String nota : notas) {
            suma += Double.parseDouble(nota);
        }
        return suma;
    }

    /**
     * Calcula la resta de las dos primeras notas.
     * 
     * @return la resta de las dos primeras notas, o 0 si todas las notas son -1.
     */
    public double opResta() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1)) {
            return 0;
        }
        return Double.parseDouble(notas.get(0)) - Double.parseDouble(notas.get(1));
    }

    /**
     * Calcula el producto de las notas.
     * 
     * @return el producto de las notas, o 0 si todas las notas son -1.
     */
    public double opProducto() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1)) {
            return 0;
        }
        double producto = 1.0;

        for (String nota : notas) {
            producto *= Double.parseDouble(nota);
        }
        return producto;
    }

    /**
     * Calcula la división de las dos primeras notas.
     * 
     * @return la división de las dos primeras notas, o 0 si todas las notas son -1 o la segunda nota es 0.
     */
    public double opDivision() {
        // si todas las notas son -1 devuelve 0
        if (notas.isEmpty() || notas.stream().allMatch(nota -> Double.parseDouble(nota) == -1) || Double.parseDouble(notas.get(1)) == 0) {
            return 0;
        }
        return Double.parseDouble(notas.get(0)) / Double.parseDouble(notas.get(1));
    }
}
