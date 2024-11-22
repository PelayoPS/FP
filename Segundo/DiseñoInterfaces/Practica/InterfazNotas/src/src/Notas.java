package src;

import java.util.ArrayList;
import java.util.List;

public class Notas {
    private List<String> notas;

    public Notas() {
        this.notas = new ArrayList<>();
    }

    public List<String> obtenerNotas() {
        return notas;
    }

    public double calcularPromedio() {
        double suma = 0;
        for (String nota : notas) {
            suma += Double.parseDouble(nota);
        }
        return suma / notas.size();
    }

    public double calcularDesviacion() {
        double promedio = calcularPromedio();
        double suma = 0;
        for (String nota : notas) {
            suma += Math.pow(Double.parseDouble(nota) - promedio, 2);
        }
        return Math.sqrt(suma / notas.size());
    }

    public double calcularMenor() {
        double menor = Double.parseDouble(notas.get(0));
        for (String nota : notas) {
            double notaDouble = Double.parseDouble(nota);
            if (notaDouble < menor) {
                menor = notaDouble;
            }
        }
        return menor;
    }

    public double calcularMayor() {
        double mayor = Double.parseDouble(notas.get(0));
        for (String nota : notas) {
            double notaDouble = Double.parseDouble(nota);
            if (notaDouble > mayor) {
                mayor = notaDouble;
            }
        }
        return mayor;
    }
}
