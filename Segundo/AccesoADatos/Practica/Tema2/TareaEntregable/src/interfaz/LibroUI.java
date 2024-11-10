package interfaz;

import logica.BibliotecaService;
import modelo.Autor;
import modelo.Libro;

import java.util.Scanner;

public class LibroUI {
    private final BibliotecaService bibliotecaService;
    private final Scanner scanner;

    public LibroUI() {
        bibliotecaService = new BibliotecaService();
        scanner = new Scanner(System.in);
    }

    public void gestionarLibros() {
        int opcion;
        do {
            mostrarMenuLibro();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1 -> agregarLibro();
                case 2 -> listarLibros();
                case 3 -> eliminarLibro();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuLibro() {
        System.out.println("\n--- Gestión de Libros ---");
        System.out.println("1. Agregar Libro");
        System.out.println("2. Listar Libros");
        System.out.println("3. Eliminar Libro");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
    }

    private void agregarLibro() {

        System.out.println("\n--- Agregar Libro ---");
        System.out.println("Ingrese el id del libro: ");
        int id = scanner.nextInt();
        System.out.println("Ingrese el título del libro: ");
        String titulo = scanner.next();
        System.out.println("Ingrese el género del libro: ");
        String genero = scanner.next();
        System.out.println("Ingrese el año de publicación: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer
        System.out.println("Ingrese el ID del autor: ");
        int autorId = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer

        Autor autor = bibliotecaService.buscarAutorPorId(autorId).orElse(null);
        if (autor != null) {
            Libro libro = bibliotecaService.agregarLibro(id, titulo, genero, anio, autor);
            System.out.println("Libro agregado: " + libro);
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private void listarLibros() {
        bibliotecaService.listarLibros().forEach(System.out::println);
    }

    private void eliminarLibro() {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        int id = scanner.nextInt();
        if (bibliotecaService.eliminarLibro(id)) {
            System.out.println("Libro eliminado.");
        } else {
            System.out.println("Libro no encontrado.");
        }
    }
}
