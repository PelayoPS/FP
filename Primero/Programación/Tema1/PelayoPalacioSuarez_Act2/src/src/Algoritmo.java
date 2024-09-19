package src;

import java.util.Scanner;

public class Algoritmo {
    public static void main(String[] args) {
        /*
         * Diseñar un algoritmo que pida por teclado una fecha(día, mes y año) y calcule
         * la fecha
         * obtenida al sumarle un día. Todos los datos se almacenan como número entero
         * positivo
         */
        int dia = 0;
        int mes = 0;
        int anio = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el dia");
        dia = keyboard.nextInt();
        System.out.println("Introduce el mes");
        mes = keyboard.nextInt();
        System.out.println("Introduce el año");
        anio = keyboard.nextInt();
        //imprime la fecha con formato: días/mes/año
        System.out.println("La fecha introducida es: " + dia + "/" + mes + "/" + anio);
        //incrementa un día
        dia++;
        //si el día es mayor que los días que tiene el mes, se pasa al siguiente mes
        if (dia > diasMes(mes, anio)) {
            dia = 1;
            mes++;
            //si el mes es mayor que 12, se pasa al siguiente año
            if (mes > 12) {
                mes = 1;
                anio++;
            }
        }
        //imprime la fecha con formato: días/mes/año
        System.out.println("La fecha del día siguiente es: " + dia + "/" + mes + "/" + anio);
        keyboard.close();
    }

    /**
     * método privado para saber si un año es bisiesto
     */
    private static boolean esBisiesto(int anio) {
        boolean bisiesto = false;
        if (anio % 4 == 0 && anio % 100 != 0 || anio % 400 == 0) {
            bisiesto = true;
        }
        return bisiesto;
    }

    /**
     * método privado que devuelde los días que tiene un mes
     * mirando si es bisiesto o no y recibiendo el mes como parámetro
     */
    private static int diasMes(int month, int anio) {
        int dias = 0;
        switch (month) {
            case 1:
                dias = 31;
                break;
            case 2:
                if(esBisiesto(anio))
                    dias = 29;
                else
                    dias = 28;
                break;
            case 3:
                dias = 31;
                break;
            case 4:
                dias = 30;
                break;
            case 5:
                dias = 31;
                break;
            case 6:
                dias = 30;
                break;
            case 7:
                dias = 31;
                break;
            case 8:
                dias = 31;
                break;
            case 9:
                dias = 30;
                break;
            case 10:
                dias = 31;
                break;
            case 11:
                dias = 30;
                break;
            case 12:
                dias = 31;
                break;
        }
        return dias;
    }

}
