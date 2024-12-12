package src.persistencia;

import src.excepciones.PersistenciaException;
import src.modelo.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la persistencia de objetos de tipo Libro.
 */
public class LibroPersistencia implements IPersistencia<Libro> {

    public LibroPersistencia() {
    }

    @Override
    public void guardar(Libro libro) throws PersistenciaException {
        String sql = "INSERT INTO Libro (id, titulo, genero, anio, autor_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, libro.getId());
            pstmt.setString(2, libro.getTitulo());
            pstmt.setString(3, libro.getGenero());
            pstmt.setInt(4, libro.getAnio());
            pstmt.setInt(5, libro.getAutor().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Error al guardar el libro", e);
        }
    }

    @Override
    public List<Libro> listar() throws PersistenciaException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libro";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("anio"),
                        // Aquí se debería obtener el autor por su ID
                        null
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al listar los libros", e);
        }
        return libros;
    }

    @Override
    public Libro obtenerPorId(int id) throws PersistenciaException {
        String sql = "SELECT * FROM Libro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Libro(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("genero"),
                            rs.getInt("anio"),
                            // Aquí se debería obtener el autor por su ID
                            null
                    );
                } else {
                    throw new PersistenciaException("Libro no encontrado con ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el libro por ID", e);
        }
    }

    @Override
    public boolean actualizar(Libro libro) throws PersistenciaException {
        String sql = "UPDATE Libro SET titulo = ?, genero = ?, anio = ?, autor_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, libro.getTitulo());
            pstmt.setString(2, libro.getGenero());
            pstmt.setInt(3, libro.getAnio());
            pstmt.setInt(4, libro.getAutor().getId());
            pstmt.setInt(5, libro.getId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar el libro", e);
        }
    }

    @Override
    public boolean eliminar(int id) throws PersistenciaException {
        String sql = "DELETE FROM Libro WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al eliminar el libro", e);
        }
    }

    public void crearTabla() throws PersistenciaException {
        String sql = "CREATE TABLE IF NOT EXISTS Libro (" +
                     "id INT PRIMARY KEY," +
                     "titulo VARCHAR(255)," +
                     "genero VARCHAR(255)," +
                     "anio INT," +
                     "autor_id INT," +
                     "FOREIGN KEY (autor_id) REFERENCES Autor(id))";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            throw new PersistenciaException("Error al crear la tabla Libro", e);
        }
    }
}