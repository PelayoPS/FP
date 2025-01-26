package interfaz;

import persistencia.GestorJDBC;
import modelo.Empleado;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorJDBC gestorJDBC = null;
        try {
            gestorJDBC = new GestorJDBC();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            scanner.close();
            return;
        }

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear tabla Empleado");
            System.out.println("2. Insertar Empleado");
            System.out.println("3. Borrar Empleado por ID");
            System.out.println("4. Buscar Empleado por ID");
            System.out.println("5. Listar todos los Empleados");
            System.out.println("6. Exportar datos a CSV");
            System.out.println("7. Importar datos desde CSV");
            System.out.println("8. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    gestorJDBC.crearTablaEmpleado();
                    break;
                case 2:
                    System.out.println("Ingrese los datos del empleado (id, nombre, apellidos, genero, categoriaProfesional, salarioAnual):");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    String nombre = scanner.nextLine();
                    String apellidos = scanner.nextLine();
                    char genero = scanner.nextLine().charAt(0);
                    String categoriaProfesional = scanner.nextLine();
                    double salarioAnual = scanner.nextDouble();
                    Empleado empleado = new Empleado(id, nombre, apellidos, genero, categoriaProfesional, salarioAnual);
                    gestorJDBC.insertarEmpleado(empleado);
                    break;
                case 3:
                    System.out.println("Ingrese el ID del empleado a borrar:");
                    int idBorrar = scanner.nextInt();
                    gestorJDBC.borrarEmpleadoPorId(idBorrar);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del empleado a buscar:");
                    int idBuscar = scanner.nextInt();
                    Empleado empleadoEncontrado = gestorJDBC.buscarEmpleadoPorId(idBuscar);
                    System.out.println(empleadoEncontrado);
                    break;
                case 5:
                    List<Empleado> empleados = gestorJDBC.listarTodosLosEmpleados();
                    empleados.forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Ingrese la ruta del archivo CSV para exportar:");
                    String rutaExportar = scanner.nextLine();
                    gestorJDBC.exportarDatosMySqlACSV(rutaExportar);
                    break;
                case 7:
                    System.out.println("Ingrese la ruta del archivo CSV para importar:");
                    String rutaImportar = scanner.nextLine();
                    gestorJDBC.importarDatosCSVAMySql(rutaImportar);
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
