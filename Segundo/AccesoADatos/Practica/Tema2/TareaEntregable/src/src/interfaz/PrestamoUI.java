package src.interfaz;

import src.log.Logger;
import src.logica.BibliotecaService;
import src.modelo.Libro;
import src.modelo.Prestamo;

import java.util.Scanner;

public class PrestamoUI {
    private final BibliotecaService bibliotecaService;
    private final Scanner scanner;

    /**
     * Constructor de la clase PrestamoUI.
     * Inicializa los servicios de biblioteca y el escáner.
     */
    public PrestamoUI() {
        bibliotecaService = new BibliotecaService();
        scanner = new Scanner(System.in);
    }

    /**
     * Método para gestionar las opciones del menú de préstamos.
     * Permite registrar, listar y devolver préstamos.
     */
    public void gestionarPrestamos() {
        int opcion;
        do {
            mostrarMenuPrestamo();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1 -> registrarPrestamo();
                case 2 -> listarPrestamos();
                case 3 -> devolverLibro();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Muestra el menú de opciones para la gestión de préstamos.
     */
    private void mostrarMenuPrestamo() {
        System.out.println("\n--- Gestión de Préstamos ---");
        System.out.println("1. Registrar Préstamo");
        System.out.println("2. Listar Préstamos");
        System.out.println("3. Devolver Libro");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Registra un nuevo préstamo en la biblioteca.
     * Solicita al usuario el ID del libro a prestar y lo registra en el sistema.
     */
    private void registrarPrestamo() {
        System.out.println("Ingrese el ID del libro a prestar: ");
        int libroId = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer

        try {
            Libro libro = bibliotecaService.buscarLibroPorId(libroId);
            if (libro != null) {
                Prestamo prestamo = bibliotecaService.registrarPrestamo(libro);
                System.out.println("Préstamo registrado: " + prestamo);
                Logger.logInfo("Préstamo registrado: " + prestamo);
            } else {
                System.out.println("Libro no encontrado.");
                Logger.logWarning("Libro no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Libro no encontrado.");
            Logger.logWarning("Libro no encontrado.");
        }
    }

    /**
     * Lista todos los préstamos registrados en la biblioteca.
     * Imprime la lista de préstamos en la consola.
     */
    private void listarPrestamos() {
        try {
            bibliotecaService.listarPrestamos().forEach(System.out::println);
            Logger.logInfo("Listado de préstamos");
        } catch (Exception e) {
            System.out.println("Error al listar los préstamos: " + e.getMessage());
            Logger.logError("Error al listar los préstamos: " + e.getMessage());
        }
    }

    /**
     * Devuelve un libro prestado a la biblioteca.
     * Solicita al usuario el ID del préstamo a devolver y lo actualiza en el
     * sistema.
     */
    private void devolverLibro() {
        System.out.print("Ingrese el ID del préstamo a devolver: ");
        int prestamoId = scanner.nextInt();
        try {
            if (bibliotecaService.devolverLibro(prestamoId)) {
                System.out.println("Libro devuelto.");
                Logger.logInfo("Libro devuelto.");
            } else {
                System.out.println("Préstamo no encontrado o ya devuelto.");
                Logger.logWarning("Préstamo no encontrado o ya devuelto.");
            }
        } catch (Exception e) {
            System.out.println("Error al devolver el libro: " + e.getMessage());
            Logger.logError("Error al devolver el libro: " + e.getMessage());
        }
    }
}
