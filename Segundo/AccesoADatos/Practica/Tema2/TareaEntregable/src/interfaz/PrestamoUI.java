package interfaz;

import logica.BibliotecaService;
import modelo.Libro;
import modelo.Prestamo;

import java.util.Scanner;

public class PrestamoUI {
    private final BibliotecaService bibliotecaService;
    private final Scanner scanner;

    public PrestamoUI() {
        bibliotecaService = new BibliotecaService();
        scanner = new Scanner(System.in);
    }

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

    private void mostrarMenuPrestamo() {
        System.out.println("\n--- Gestión de Préstamos ---");
        System.out.println("1. Registrar Préstamo");
        System.out.println("2. Listar Préstamos");
        System.out.println("3. Devolver Libro");
        System.out.println("0. Volver");
        System.out.print("Seleccione una opción: ");
    }

    private void registrarPrestamo() {
        System.out.println("Ingrese el ID del libro a prestar: ");
        int libroId = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer

        Libro libro = bibliotecaService.buscarLibroPorId(libroId).orElse(null);
        if (libro != null) {
            Prestamo prestamo = bibliotecaService.registrarPrestamo(libro);
            System.out.println("Préstamo registrado: " + prestamo);
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarPrestamos() {
        bibliotecaService.listarPrestamos().forEach(System.out::println);
    }

    private void devolverLibro() {
        System.out.print("Ingrese el ID del préstamo a devolver: ");
        int prestamoId = scanner.nextInt();
        if (bibliotecaService.devolverLibro(prestamoId)) {
            System.out.println("Libro devuelto.");
        } else {
            System.out.println("Préstamo no encontrado o ya devuelto.");
        }
    }
}
