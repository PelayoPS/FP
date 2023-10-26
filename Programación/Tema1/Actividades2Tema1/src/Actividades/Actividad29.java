package Actividades;

import java.util.Scanner;

public class Actividad29 {
    public static void main(String[] args) {
        /*
         * Realiza un algoritmo que introducidas las notas de N alumnos nos
         * indicará cuantos aprobados y cuantos suspensos hay. Generaliza este
         * ejercicio para una cantidad indefinida de notas (pararemos con una
         * nota negativa). Amplia el ejercicio indicando no solo cuantos
         * aprobados hay, también cuantos sobresalientes, notables, bienes,
         * aprobados y suspensos.
         */
        Scanner keyboard = new Scanner(System.in);
        int aprobados = 0;
        int sobresalientes = 0;
        int notables = 0;
        int bienes = 0;
        int suspensos = 0;

        System.out.print("Introduzca el número de alumnos: ");
        int nota = 0;
        //loop sobre todos los alumnos preguntando por nota
        while (nota >= 0) {
            System.out.print("Introduzca la nota del alumno : ");
            nota = keyboard.nextInt();
            //incrementamos el contador de aprobados
            if (Nota.getNota(nota) == Nota.APROBADO) {
                aprobados++;
            }
            //incrementamos el contador de sobresalientes
            if (Nota.getNota(nota) == Nota.SOBRESALIENTE) {
                sobresalientes++;
            }
            //incrementamos el contador de notables
            if (Nota.getNota(nota) == Nota.NOTABLE) {
                notables++;
            }
            //incrementamos el contador de bienes
            if (Nota.getNota(nota) == Nota.BIEN) {
                bienes++;
            }
            //incrementamos el contador de suspensos
            if (Nota.getNota(nota) == Nota.SUSPENSO) {
                suspensos++;
            }
        }
        keyboard.close();
        //mostramos los resultados en forma de tabla
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Sobresalientes: " + sobresalientes);
        System.out.println("Notables: " + notables);
        System.out.println("Bienes: " + bienes);
        System.out.println("Suspensos: " + suspensos);

    }

    /**
     * Enum con las notas y un método que mira si la nota es sobresaliente, notable, bien
     * aprobado o suspenso.
     */
    private enum Nota {
        SUSPENSO, APROBADO, BIEN, NOTABLE, SOBRESALIENTE;
        public static Nota getNota(int nota) {
            if (nota < 5) {
                return SUSPENSO;
            } else if (nota < 6) {
                return APROBADO;
            } else if (nota < 7) {
                return BIEN;
            } else if (nota < 9) {
                return NOTABLE;
            } else {
                return SOBRESALIENTE;
            }
        }
    }

}
