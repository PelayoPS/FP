package src.interfaz;

import src.log.Logger;
import src.logica.BibliotecaService;
import src.modelo.Autor;
import src.modelo.Libro;

import java.util.Scanner;

public class LibroUI {
    private final BibliotecaService bibliotecaService;
    private final Scanner scanner;

    /**
     * Constructor de la clase LibroUI.
     * Inicializa los servicios de biblioteca y el escáner.
     */
    public LibroUI() {
        bibliotecaService = new BibliotecaService();
        scanner = new Scanner(System.in);
    }

    /**
     * Método para gestionar las opciones del menú de libros.
     * Permite agregar, listar y eliminar libros.
     */
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

    /**
     * Muestra el menú de opciones para la gestión de libros.
     */
    private void mostrarMenuLibro() {
        System.out.println("\n--- Gestión de Libros ---");
        System.out.println("1. Agregar Libro");
        System.out.println("2. Listar Libros");
        System.out.println("3. Eliminar Libro");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Agrega un nuevo libro a la biblioteca.
     * Solicita al usuario los datos del libro y los agrega al sistema.
     * Registra la operación en el log.
     */
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

        try {
            Autor autor = bibliotecaService.buscarAutorPorId(autorId);
            if (autor != null) {
                Libro libro = bibliotecaService.agregarLibro(id, titulo, genero, anio, autor);
                System.out.println("Libro agregado: " + libro);
                Logger.logInfo("Libro agregado: " + libro);
            } else {
                System.out.println("Autor no encontrado.");
                Logger.logWarning("Autor no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar el libro: " + e.getMessage());
            Logger.logError("Error al agregar el libro: " + e.getMessage());
        }
    }

    /**
     * Lista todos los libros registrados en la biblioteca.
     * Imprime la lista de libros en la consola y registra la operación en el log.
     */
    private void listarLibros() {
        try {
            bibliotecaService.listarLibros().forEach(System.out::println);
            Logger.logInfo("Listado de libros");
        } catch (Exception e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
            Logger.logError("Error al listar los libros: " + e.getMessage());
        }
    }

    /**
     * Elimina un libro de la biblioteca.
     * Solicita al usuario el ID del libro a eliminar y lo elimina del sistema.
     * Registra la operación en el log.
     */
    private void eliminarLibro() {
        System.out.print("Ingrese el ID del libro a eliminar: ");
        int id = scanner.nextInt();
        try {
            if (bibliotecaService.eliminarLibro(id)) {
                System.out.println("Libro eliminado.");
                Logger.logInfo("Libro eliminado");
            } else {
                System.out.println("Libro no encontrado.");
                Logger.logWarning("Libro no encontrado");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el libro: " + e.getMessage());
            Logger.logError("Error al eliminar el libro: " + e.getMessage());
        }
    }
}
