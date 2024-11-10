package interfaz;

import java.util.Scanner;

public class BibliotecaApp {
    private final AutorUI autorUI;
    private final LibroUI libroUI;
    private final PrestamoUI prestamoUI;
    private final Scanner scanner;

    public BibliotecaApp() {
        autorUI = new AutorUI();
        libroUI = new LibroUI();
        prestamoUI = new PrestamoUI();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1 -> autorUI.gestionarAutores();
                case 2 -> libroUI.gestionarLibros();
                case 3 -> prestamoUI.gestionarPrestamos();
                case 0 -> System.out.println("Saliendo de la aplicación...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestión de Autores");
        System.out.println("2. Gestión de Libros");
        System.out.println("3. Gestión de Préstamos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.iniciar();
    }
}
