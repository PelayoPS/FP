package src.persistencia;

import src.excepciones.PersistenciaException;
import src.interfaz.BibliotecaApp;
import src.log.Logger;
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
        Logger.logInfo("Guardando autor: " + autor);
        String sql = "INSERT INTO Autor (id, nombre, fechaNacimiento, nacionalidad) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, autor.getId());
            pstmt.setString(2, autor.getNombre());
            pstmt.setString(3, autor.getFechaNacimiento());
            pstmt.setString(4, autor.getNacionalidad());
            pstmt.executeUpdate();
            Logger.logInfo("Autor guardado exitosamente: " + autor);
        } catch (SQLException e) {
            Logger.logError("Error al guardar el autor: " + e.getMessage());
            throw new PersistenciaException("Error al guardar el autor", e);
        }
    }

    @Override
    public List<Autor> listar() throws PersistenciaException {
        Logger.logInfo("Listando autores");
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
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
            Logger.logInfo("Autores listados exitosamente");
        } catch (SQLException e) {
            Logger.logError("Error al listar los autores: " + e.getMessage());
            throw new PersistenciaException("Error al listar los autores", e);
        }
        return autores;
    }

    @Override
    public Autor obtenerPorId(int id) throws PersistenciaException {
        Logger.logInfo("Obteniendo autor por ID: " + id);
        String sql = "SELECT * FROM Autor WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Autor autor = new Autor(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("fechaNacimiento"),
                            rs.getString("nacionalidad")
                    );
                    Logger.logInfo("Autor obtenido exitosamente: " + autor);
                    return autor;
                } else {
                    Logger.logError("Autor no encontrado con ID: " + id);
                    throw new PersistenciaException("Autor no encontrado con ID: " + id);
                }
            }
        } catch (SQLException e) {
            Logger.logError("Error al obtener el autor por ID: " + e.getMessage());
            throw new PersistenciaException("Error al obtener el autor por ID", e);
        }
    }

    @Override
    public boolean actualizar(Autor autor) throws PersistenciaException {
        Logger.logInfo("Actualizando autor: " + autor);
        String sql = "UPDATE Autor SET nombre = ?, fechaNacimiento = ?, nacionalidad = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor.getNombre());
            pstmt.setString(2, autor.getFechaNacimiento());
            pstmt.setString(3, autor.getNacionalidad());
            pstmt.setInt(4, autor.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                Logger.logInfo("Autor actualizado exitosamente: " + autor);
                return true;
            } else {
                Logger.logError("No se encontró el autor para actualizar: " + autor);
                return false;
            }
        } catch (SQLException e) {
            Logger.logError("Error al actualizar el autor: " + e.getMessage());
            throw new PersistenciaException("Error al actualizar el autor", e);
        }
    }

    @Override
    public boolean eliminar(int id) throws PersistenciaException {
        Logger.logInfo("Eliminando autor con ID: " + id);
        String sql = "DELETE FROM Autor WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                Logger.logInfo("Autor eliminado exitosamente con ID: " + id);
                return true;
            } else {
                Logger.logError("No se encontró el autor para eliminar con ID: " + id);
                return false;
            }
        } catch (SQLException e) {
            Logger.logError("Error al eliminar el autor: " + e.getMessage());
            throw new PersistenciaException("Error al eliminar el autor", e);
        }
    }

    public void crearTabla() throws PersistenciaException {
        Logger.logInfo("Creando tabla Autor");
        String sql = "CREATE TABLE IF NOT EXISTS Autor (" +
                     "id INT PRIMARY KEY," +
                     "nombre VARCHAR(255)," +
                     "fechaNacimiento VARCHAR(255)," +
                     "nacionalidad VARCHAR(255))";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            Logger.logInfo("Tabla Autor creada exitosamente");
        } catch (Exception e) {
            Logger.logError("Error al crear la tabla Autor: " + e.getMessage());
            throw new PersistenciaException("Error al crear la tabla Autor", e);
        }
    }
}