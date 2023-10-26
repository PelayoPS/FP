package src;

import java.util.Scanner;

public class Actividad19 {
    public static void main(String[] args) {
        /*
         * Diseña una función que pregunte al usuario la fecha actual y la fecha
         * de nacimiento de una persona; el programa determinará la edad.
         */
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduce la fecha actual (dd/mm/aaaa)");
        String date = keyboard.nextLine();
        System.out.println("Introduce la fecha de nacimiento (dd/mm/aaaa)");
        String birthDate = keyboard.nextLine();
        keyboard.close();
        System.out.println("La edad es: " + calculateAge(date, birthDate));
    }

    private static int calculateAge(String date, String birthDate) {
        // calcula la edad mirando si ya cumplió años o no
        int age = 0;
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));
        int birthDay = Integer.parseInt(birthDate.substring(0, 2));
        int birthMonth = Integer.parseInt(birthDate.substring(3, 5));
        int birthYear = Integer.parseInt(birthDate.substring(6, 10));
        if (birthMonth < month) {
            age = year - birthYear;
        } else if (birthMonth == month) {
            if (birthDay <= day) {
                age = year - birthYear;
            } else {
                age = year - birthYear - 1;
            }
        } else {
            age = year - birthYear - 1;
        }
        return age;
    }

}
