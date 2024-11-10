package src.interfaz;

import src.log.Logger;
import src.logica.BibliotecaService;
import src.modelo.Autor;

import java.util.Scanner;

public class AutorUI {
    private final BibliotecaService bibliotecaService;
    private final Scanner scanner;

    /**
     * Constructor de la clase AutorUI.
     * Inicializa los servicios de biblioteca y el escáner.
     */
    public AutorUI() {
        bibliotecaService = new BibliotecaService();
        scanner = new Scanner(System.in);
    }

    /**
     * Método para gestionar las opciones del menú de autores.
     * Permite agregar, listar y eliminar autores.
     */
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

    /**
     * Muestra el menú de opciones para la gestión de autores.
     */
    private void mostrarMenuAutor() {
        System.out.println("\n--- Gestión de Autores ---");
        System.out.println("1. Agregar Autor");
        System.out.println("2. Listar Autores");
        System.out.println("3. Eliminar Autor");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Agrega un nuevo autor a la biblioteca.
     * Solicita al usuario los datos del autor y los agrega al sistema.
     * Registra la operación en el log.
     */
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

        try {
            Autor autor = bibliotecaService.agregarAutor(id, nombre, fechaNacimiento, nacionalidad);
            System.out.println("Autor agregado: " + autor);
            Logger.logInfo("Autor agregado: " + autor);
        } catch (Exception e) {
            Logger.logError(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Operación finalizada.");
        }

    }

    /**
     * Lista todos los autores registrados en la biblioteca.
     * Imprime la lista de autores en la consola y registra la operación en el log.
     */
    private void listarAutores() {
        try {
            bibliotecaService.listarAutores().forEach(System.out::println);
            Logger.logInfo("Listado de autores.");
        } catch (Exception e) {
            Logger.logError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Elimina un autor de la biblioteca.
     * Solicita al usuario el ID del autor a eliminar y lo elimina del sistema.
     * Registra la operación en el log.
     */
    private void eliminarAutor() {
        System.out.print("Ingrese el ID del autor a eliminar: ");
        int id = scanner.nextInt();
        try {
            if (bibliotecaService.eliminarAutor(id)) {
                System.out.println("Autor eliminado.");
                Logger.logInfo("Autor eliminado.");
            } else {
                System.out.println("Autor no encontrado.");
                Logger.logWarning("Autor no encontrado.");
            }
        } catch (Exception e) {
            Logger.logError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}
