package src.interfaz;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import src.log.Logger;
import src.persistencia.AutorPersistencia;
import src.persistencia.DatabaseConnection;
import src.persistencia.LibroPersistencia;
import src.persistencia.PrestamoPersistencia;
import src.logica.BibliotecaService;
import src.excepciones.PersistenciaException;

/**
 * Aplicación de consola para gestionar la biblioteca.
 */
public class BibliotecaApp {
    private final AutorUI autorUI;
    private final LibroUI libroUI;
    private final PrestamoUI prestamoUI;
    private final Scanner scanner;
    private final BibliotecaService bibliotecaService;
    public static String dbUrl;
    public static String dbUser;
    public static String dbPassword;
    /**
     * Constructor de la clase BibliotecaApp.
     * Inicializa las interfaces de usuario y el escáner.
     */
    public BibliotecaApp() {
        autorUI = new AutorUI();
        libroUI = new LibroUI();
        prestamoUI = new PrestamoUI();
        scanner = new Scanner(System.in);
        bibliotecaService = new BibliotecaService();
        pedirCredencialesBaseDeDatos();
    }

    /**
     * Solicita las credenciales de la base de datos al usuario.
     */
    private void pedirCredencialesBaseDeDatos() {
        System.out.print("Ingrese la URL de la base de datos: ");
        dbUrl = scanner.nextLine();
        System.out.print("Ingrese el usuario de la base de datos: ");
        dbUser = scanner.nextLine();
        System.out.print("Ingrese la contraseña de la base de datos: ");
        dbPassword = scanner.nextLine();
    }

    /**
     * Inicia la aplicación de la biblioteca.
     * Muestra el menú principal y gestiona las opciones seleccionadas por el usuario.
     */
    public void iniciar() {
        try {
            new DatabaseConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            Logger.logError("Error al conectar a la base de datos: " + e.getMessage());
            System.out.println("Error al conectar a la base de datos.");
            return;
        }

        crearTablas(); // Llamada al nuevo método para crear las tablas

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
                case 5 -> mostrarInformacionBaseDeDatos();
                case 6 -> exportarDatos();
                case 7 -> importarDatos();
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
     * Permite al usuario seleccionar entre las opciones de gestión de autores, libros y préstamos.
     */
    private void mostrarMenuPrincipal() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestión de Autores");
        System.out.println("2. Gestión de Libros");
        System.out.println("3. Gestión de Préstamos");
        System.out.println("4. Ver logs");
        System.out.println("5. Mostrar información de la base de datos");
        System.out.println("6. Exportar datos a CSV");
        System.out.println("7. Importar datos desde CSV");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Muestra información de la base de datos.
     * Lista las tablas y columnas de la base de datos "biblioteca".
     */
    private void mostrarInformacionBaseDeDatos() {
        try (Connection conn = DatabaseConnection.getInstance(dbUrl, dbUser, dbPassword).getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();
            String[] types = {"TABLE"};
            ResultSet tables = metaData.getTables("biblioteca", null, "%", types);
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Tabla: " + tableName);
                ResultSet columns = metaData.getColumns("biblioteca", null, tableName, "%");
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    System.out.println("  Columna: " + columnName + " - Tipo: " + columnType);
                }
            }
        } catch (SQLException e) {
            Logger.logError("Error al mostrar la información de la base de datos: " + e.getMessage());
            System.out.println("Error al mostrar la información de la base de datos.");
        }
    }

    /**
     * Exporta los datos de la base de datos a un archivo CSV.
     */
    private void exportarDatos() {
        System.out.print("Ingrese la ruta del archivo CSV para exportar los datos: ");
        String filePath = scanner.nextLine();
        try {
            bibliotecaService.exportarDatosCSV(filePath);
            System.out.println("Datos exportados correctamente a " + filePath);
        } catch (PersistenciaException e) {
            Logger.logError("Error al exportar datos: " + e.getMessage());
            System.out.println("Error al exportar datos.");
        }
    }

    /**
     * Importa los datos de un archivo CSV a la base de datos.
     */
    private void importarDatos() {
        System.out.print("Ingrese la ruta del archivo CSV para importar los datos: ");
        String filePath = scanner.nextLine();
        try {
            bibliotecaService.importarDatosCSV(filePath);
            System.out.println("Datos importados correctamente desde " + filePath);
        } catch (PersistenciaException e) {
            Logger.logError("Error al importar datos: " + e.getMessage());
            System.out.println("Error al importar datos.");
        }
    }

    /**
     * Crea las tablas en la base de datos utilizando las clases de persistencia.
     */
    private void crearTablas() {
        try {
            new AutorPersistencia().crearTabla();
            new LibroPersistencia().crearTabla();
            new PrestamoPersistencia().crearTabla();
            Logger.logInfo("Todas las tablas se han creado con éxito");
            System.out.println("Todas las tablas se han creado con éxito.");
        } catch (PersistenciaException e) {
            Logger.logError("Error al crear las tablas: " + e.getMessage());
            System.out.println("Error al crear las tablas.");
        }
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
