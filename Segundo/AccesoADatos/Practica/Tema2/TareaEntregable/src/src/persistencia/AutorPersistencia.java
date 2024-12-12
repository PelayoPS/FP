package src.persistencia;

import src.excepciones.PersistenciaException;
import src.modelo.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la persistencia de objetos de tipo Autor.
 */
public class AutorPersistencia implements IPersistencia<Autor> {

    public AutorPersistencia() {
    }

    @Override
    public void guardar(Autor autor) throws PersistenciaException {
        String sql = "INSERT INTO Autor (id, nombre, fechaNacimiento, nacionalidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, autor.getId());
            pstmt.setString(2, autor.getNombre());
            pstmt.setString(3, autor.getFechaNacimiento());
            pstmt.setString(4, autor.getNacionalidad());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al guardar el autor", e);
        }
    }

    @Override
    public List<Autor> listar() throws PersistenciaException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Autor autor = new Autor(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("fechaNacimiento"),
                        rs.getString("nacionalidad")
                );
                autores.add(autor);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al listar los autores", e);
        }
        return autores;
    }

    @Override
    public Autor obtenerPorId(int id) throws PersistenciaException {
        String sql = "SELECT * FROM Autor WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Autor(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("fechaNacimiento"),
                            rs.getString("nacionalidad")
                    );
                } else {
                    throw new PersistenciaException("Autor no encontrado con ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el autor por ID", e);
        }
    }

    @Override
    public boolean actualizar(Autor autor) throws PersistenciaException {
        String sql = "UPDATE Autor SET nombre = ?, fechaNacimiento = ?, nacionalidad = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor.getNombre());
            pstmt.setString(2, autor.getFechaNacimiento());
            pstmt.setString(3, autor.getNacionalidad());
            pstmt.setInt(4, autor.getId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar el autor", e);
        }
    }

    @Override
    public boolean eliminar(int id) throws PersistenciaException {
        String sql = "DELETE FROM Autor WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al eliminar el autor", e);
        }
    }

    public void crearTabla() throws PersistenciaException {
        String sql = "CREATE TABLE IF NOT EXISTS Autor (" +
                     "id INT PRIMARY KEY," +
                     "nombre VARCHAR(255)," +
                     "fechaNacimiento VARCHAR(255)," +
                     "nacionalidad VARCHAR(255))";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            throw new PersistenciaException("Error al crear la tabla Autor", e);
        }
    }
}