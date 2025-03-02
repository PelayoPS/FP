/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import logica.Biblioteca;

/**
 *
 * @author pelay
 */
public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        System.out.println("Todos los datos:");
        biblioteca.listarTodosLosDatos().forEach(System.out::println);

        System.out.println("Autores con número de libros:");
        biblioteca.listarAutores().forEach(System.out::println);

        System.out.println("Número de préstamos con expediente 11:");
        System.out.println(biblioteca.numPrestamosPorExpediente(11));

        System.out.println("Alumnos de 4º de ESO ordenados:");
        biblioteca.listarAlumnos4ESO().forEach(System.out::println);
    }

}
