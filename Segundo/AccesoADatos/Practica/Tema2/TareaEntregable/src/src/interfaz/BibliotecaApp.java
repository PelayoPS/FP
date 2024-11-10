package src.interfaz;

import java.util.Scanner;

import src.log.Logger;

public class BibliotecaApp {
    private final AutorUI autorUI;
    private final LibroUI libroUI;
    private final PrestamoUI prestamoUI;
    private final Scanner scanner;

    /**
     * Constructor de la clase BibliotecaApp.
     * Inicializa las interfaces de usuario y el escáner.
     */
    public BibliotecaApp() {
        autorUI = new AutorUI();
        libroUI = new LibroUI();
        prestamoUI = new PrestamoUI();
        scanner = new Scanner(System.in);
    }

    /**
     * Inicia la aplicación de la biblioteca.
     * Muestra el menú principal y gestiona las opciones seleccionadas por el
     * usuario.
     */
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
                case 4 -> Logger.mostrarLogs();
                case 0 -> {
                    Logger.logInfo("Saliendo de la aplicación");
                    System.out.println("Saliendo de la aplicación...");
                }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Muestra el menú principal de la aplicación.
     * Permite al usuario seleccionar entre las opciones de gestión de autores,
     * libros y préstamos.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestión de Autores");
        System.out.println("2. Gestión de Libros");
        System.out.println("3. Gestión de Préstamos");
        System.out.println("4. Ver logs");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Método principal que arranca la aplicación de la biblioteca.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        Logger.logInfo("Aplicación arrancada");
        app.iniciar();

    }
}
