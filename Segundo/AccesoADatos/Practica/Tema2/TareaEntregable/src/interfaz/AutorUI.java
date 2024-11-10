package interfaz;

import logica.BibliotecaService;
import modelo.Autor;

import java.util.Scanner;

public class AutorUI {
    private final BibliotecaService bibliotecaService;
    private final Scanner scanner;

    public AutorUI() {
        bibliotecaService = new BibliotecaService();
        scanner = new Scanner(System.in);
    }

    public void gestionarAutores() {
        int opcion;
        do {
            mostrarMenuAutor();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1 -> agregarAutor();
                case 2 -> listarAutores();
                case 3 -> eliminarAutor();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuAutor() {
        System.out.println("\n--- Gestión de Autores ---");
        System.out.println("1. Agregar Autor");
        System.out.println("2. Listar Autores");
        System.out.println("3. Eliminar Autor");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
    }

    private void agregarAutor() {

        System.out.println("\n--- Agregar Autor ---");
        System.out.println("Ingrese el id del autor: ");
        int id = scanner.nextInt();
        System.out.println("Ingrese el nombre del autor: ");
        String nombre = scanner.next();
        System.out.println("Ingrese la fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNacimiento = scanner.next();
        System.out.println("Ingrese la nacionalidad: ");
        String nacionalidad = scanner.next();

        Autor autor = bibliotecaService.agregarAutor(id, nombre, fechaNacimiento, nacionalidad);
        System.out.println("Autor agregado: " + autor);
    }

    private void listarAutores() {
        bibliotecaService.listarAutores().forEach(System.out::println);
    }

    private void eliminarAutor() {
        System.out.print("Ingrese el ID del autor a eliminar: ");
        int id = scanner.nextInt();
        if (bibliotecaService.eliminarAutor(id)) {
            System.out.println("Autor eliminado.");
        } else {
            System.out.println("Autor no encontrado.");
        }
    }
}
