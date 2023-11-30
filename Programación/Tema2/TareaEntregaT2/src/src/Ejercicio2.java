package src;

import java.util.Scanner;

public class Ejercicio2 {

    /**
     * función main que solicita los datos relativos a la fecha hasta que se
     * correspondan con una fecha válida. Los valores se almacenan como números enteros
     * @param args
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce el día: ");
        int day = keyboard.nextInt();
        System.out.println("Introduce el mes: ");
        int month = keyboard.nextInt();
        System.out.println("Introduce el año: ");
        int year = keyboard.nextInt();
        while (!validarFecha(day, month, year)) {
            System.out.println("Fecha no válida. Introduce otra fecha: ");
            System.out.println("Introduce el día: ");
            day = keyboard.nextInt();
            System.out.println("Introduce el mes: ");
            month = keyboard.nextInt();
            System.out.println("Introduce el año: ");
            year = keyboard.nextInt();
        }
        System.out.println("Fecha válida");

        keyboard.close();
    }

    /**
     * función que comprueba si una fecha es válida
     * posterior al 01/01/1978 y 0<mes<13 y 0<dia<dias_mes
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static boolean validarFecha(int day, int month, int year) {
        boolean isValid = false;
        if (year > 1978 && month > 0 && month < 13) {
            int daysOfMonth = calcularDias(month, year);
            if (day > 0 && day <= daysOfMonth) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * función que devuelve el número de días de un mes
     * @param month
     * @param year
     * @return
     */
    public static int calcularDias(int month, int year) {
        int daysOfMonth = 0;
        switch (month) {
            case 1:
                daysOfMonth = 31;
                break;
            case 2:
                if (anioBisiesto(year)) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }
                break;
            case 3:
                daysOfMonth = 31;
                break;
            case 4:
                daysOfMonth = 30;
                break;
            case 5:
                daysOfMonth = 31;
                break;
            case 6:
                daysOfMonth = 30;
                break;
            case 7:
                daysOfMonth = 31;
                break;
            case 8:
                daysOfMonth = 31;
                break;
            case 9:
                daysOfMonth = 30;
                break;
            case 10:
                daysOfMonth = 31;
                break;
            case 11:
                daysOfMonth = 30;
                break;
            case 12:
                daysOfMonth = 31;
                break;
        }
        return daysOfMonth;
    }

    /**
     * función que comprueba si un año es bisiesto
     * @param year
     * @return
     */
    public static boolean anioBisiesto(int year) {
        boolean isLeap = false;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            isLeap = true;
        }
        return isLeap;
    }
    

    
}
