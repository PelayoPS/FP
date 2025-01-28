package persistencia;

import java.sql.Connection;
import modelo.Empleado;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GestorJDBC {

    private Connection con;

    public GestorJDBC() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "1041");
		System.out.println("Base conectada ");
	}

    public void crearTablaEmpleado() {
        String sql = "CREATE TABLE IF NOT EXISTS empleado ("
                + "id INT PRIMARY KEY,"
                + "nombre VARCHAR(100),"
                + "apellidos VARCHAR(100),"
                + "genero CHAR(1),"
                + "categoriaProfesional VARCHAR(2),"
                + "salarioAnual DOUBLE)";
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (id, nombre, apellidos, genero, categoriaProfesional, salarioAnual) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, empleado.getId());
            pstmt.setString(2, empleado.getNombre());
            pstmt.setString(3, empleado.getApellidos());
            pstmt.setString(4, String.valueOf(empleado.getGenero()));
            pstmt.setString(5, empleado.getCategoriaProfesional());
            pstmt.setDouble(6, empleado.getSalarioAnual());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrarEmpleadoPorId(int id) {
        String sql = "DELETE FROM empleado WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Empleado buscarEmpleadoPorId(int id) {
        String sql = "SELECT * FROM empleado WHERE id = ?";
        Empleado empleado = null;
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                empleado = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("genero").charAt(0),
                        rs.getString("categoriaProfesional"),
                        rs.getDouble("salarioAnual")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public List<Empleado> listarTodosLosEmpleados() {
        String sql = "SELECT * FROM empleado";
        List<Empleado> empleados = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("genero").charAt(0),
                        rs.getString("categoriaProfesional"),
                        rs.getDouble("salarioAnual")
                );
                empleados.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public void exportarDatosMySqlACSV(String rutaCSV) {
        List<Empleado> empleados = listarTodosLosEmpleados();
        GestorCSV gestorCSV = new GestorCSV();
        gestorCSV.escribirEmpleados(empleados, rutaCSV);
    }

    public void importarDatosCSVAMySql(String rutaCSV) {
        GestorCSV gestorCSV = new GestorCSV();
        List<Empleado> empleados = gestorCSV.leerEmpleados(rutaCSV);
        for (Empleado empleado : empleados) {
            insertarEmpleado(empleado);
        }
    }
}
