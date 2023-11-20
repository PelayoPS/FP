package src;

import java.util.Scanner;

public class Library {
    public static Scanner keyboard = new Scanner(System.in);
    /**
     * Función que pide al usuario seleccionar una de las siguientes opciones:
     * 1. Calcular el area de un triángulo
     * 2. Calcular el area de un trapecio
     * 3. Calcular el area de un rectángulo
     * 4. Calcular el area de un cuadrado
     * y devuelve el número de la opción seleccionada
     * @return
     */
	public static int menu() {
		System.out.println("CALCULO DE AREAS ====================");
        System.out.println("1. Calcular el area de un triángulo");
        System.out.println("2. Calcular el area de un trapecio");
        System.out.println("3. Calcular el area de un rectángulo");
        System.out.println("4. Calcular el area de un cuadrado");;
        return keyboard.nextInt();
	}

    /**
     * Función que calcula el area de un triángulo
     * y la muestra por pantalla
     */
    public static void triangleArea() {
        System.out.println("Introduce la base del triángulo");
        double base = keyboard.nextDouble();
        System.out.println("Introduce la altura del triángulo");
        double height = keyboard.nextDouble();
        double area = base * height / 2;
        if (area < 0 || base < 0 || height < 0) {
            System.out.println("El area del triángulo no puede ser negativa");
            return;
        }
        System.out.println("El area del triángulo es " + area);
    }

    /**
     * Función que calcula el area de un trapecio
     * y la muestra por pantalla
     */
    public static void trapezoidArea() {
        System.out.println("Introduce la base mayor del trapecio");
        double B = keyboard.nextDouble();
        System.out.println("Introduce la base menor del trapecio");
        double b = keyboard.nextDouble();
        System.out.println("Introduce la altura del trapecio");
        double height = keyboard.nextDouble();
        double area = (B + b) * height / 2;
        if (area < 0 || B < 0 || b < 0 || height < 0) {
            System.out.println("El area del trapecio no puede ser negativa");
            return;
        }
        System.out.println("El area del trapecio es " + area);
    }

    /**
     * Función que calcula el area de un rectángulo
     * y la muestra por pantalla
     */
    public static void rectangleArea() {
        System.out.println("Introduce la base del rectángulo");
        double base = keyboard.nextDouble();
        System.out.println("Introduce la altura del rectángulo");
        double height = keyboard.nextDouble();
        double area = base * height;
        if (area < 0 || base < 0 || height < 0) {
            System.out.println("El area del rectángulo no puede ser negativa");
            return;
        }
        System.out.println("El area del rectángulo es " + area);
    }

    /**
     * Función que calcula el area de un cuadrado
     * y la muestra por pantalla
     */
    public static void squareArea() {
        System.out.println("Introduce el lado del cuadrado");
        double side = keyboard.nextDouble();
        double area = side * side;
        if (area < 0 || side < 0) {
            System.out.println("El area del cuadrado no puede ser negativa");
            return;
        }
        System.out.println("El area del cuadrado es " + area);
    }
}
