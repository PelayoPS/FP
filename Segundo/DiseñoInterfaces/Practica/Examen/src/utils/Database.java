package utils;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/empresa";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Modificado para coincidir con tu configuración local
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito");
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    public List<String[]> buscarEmpleados() {
        List<String[]> empleados = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String[] empleado = {
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        String.valueOf(rs.getInt("edad")),
                        rs.getString("profesion"),
                        rs.getString("telefono")
                };
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar empleados: " + e.getMessage());
        }
        return empleados;
    }

    public List<String[]> buscarPorProfesion(String profesion) {
        List<String[]> empleados = new ArrayList<>();
        String query = "SELECT * FROM employees WHERE profesion = ?"; // Cambiado de LIKE a =

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, profesion); // Eliminado el % %
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String[] empleado = {
                        String.valueOf(rs.getInt("id")),
                        rs.getString("name"),
                        String.valueOf(rs.getInt("edad")),
                        rs.getString("profesion"),
                        rs.getString("telefono")
                };
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por profesión: " + e.getMessage());
        }
        return empleados;
    }

    public boolean insertarEmpleado(String nombre, int edad, String profesion, String telefono) {
        String query = "INSERT INTO employees (name, edad, profesion, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, edad);
            pstmt.setString(3, profesion);
            pstmt.setString(4, telefono);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
            return false;
        }
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
