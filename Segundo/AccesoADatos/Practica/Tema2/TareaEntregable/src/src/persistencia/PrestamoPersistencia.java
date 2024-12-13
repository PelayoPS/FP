package src.persistencia;

import src.excepciones.PersistenciaException;
import src.interfaz.BibliotecaApp;
import src.log.Logger;
import src.modelo.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoPersistencia implements IPersistencia<Prestamo> {

    public PrestamoPersistencia() {
    }

    @Override
    public void guardar(Prestamo prestamo) throws PersistenciaException {
        String sql = "INSERT INTO Prestamo (id, libro_id, fechaPrestamo, fechaDevolucion) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, prestamo.getId());
            pstmt.setInt(2, prestamo.getLibro().getId());
            pstmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            pstmt.setDate(4, prestamo.getFechaDevolucion() != null ? new java.sql.Date(prestamo.getFechaDevolucion().getTime()) : null);
            pstmt.executeUpdate();
            Logger.logInfo("Préstamo guardado con éxito: " + prestamo.getId());
        } catch (SQLException e) {
            Logger.logError("Error al guardar el préstamo: " + e.getMessage());
            throw new PersistenciaException("Error al guardar el préstamo", e);
        }
    }

    @Override
    public List<Prestamo> listar() throws PersistenciaException {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM Prestamo";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("id"),
                        // Aquí se debería obtener el libro por su ID
                        null,
                        rs.getDate("fechaPrestamo"),
                        rs.getDate("fechaDevolucion")
                );
                prestamos.add(prestamo);
            }
            Logger.logInfo("Listado de préstamos obtenido con éxito");
        } catch (SQLException e) {
            Logger.logError("Error al listar los préstamos: " + e.getMessage());
            throw new PersistenciaException("Error al listar los préstamos", e);
        }
        return prestamos;
    }

    @Override
    public Prestamo obtenerPorId(int id) throws PersistenciaException {
        String sql = "SELECT * FROM Prestamo WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Logger.logInfo("Préstamo obtenido con éxito: " + id);
                    return new Prestamo(
                            rs.getInt("id"),
                            // Aquí se debería obtener el libro por su ID
                            null,
                            rs.getDate("fechaPrestamo"),
                            rs.getDate("fechaDevolucion")
                    );
                } else {
                    Logger.logWarning("Préstamo no encontrado con ID: " + id);
                    throw new PersistenciaException("Préstamo no encontrado con ID: " + id);
                }
            }
        } catch (SQLException e) {
            Logger.logError("Error al obtener el préstamo por ID: " + e.getMessage());
            throw new PersistenciaException("Error al obtener el préstamo por ID", e);
        }
    }

    @Override
    public boolean actualizar(Prestamo prestamo) throws PersistenciaException {
        String sql = "UPDATE Prestamo SET libro_id = ?, fechaPrestamo = ?, fechaDevolucion = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, prestamo.getLibro().getId());
            pstmt.setDate(2, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
            pstmt.setDate(3, prestamo.getFechaDevolucion() != null ? new java.sql.Date(prestamo.getFechaDevolucion().getTime()) : null);
            pstmt.setInt(4, prestamo.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                Logger.logInfo("Préstamo actualizado con éxito: " + prestamo.getId());
            } else {
                Logger.logWarning("No se encontró el préstamo para actualizar: " + prestamo.getId());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.logError("Error al actualizar el préstamo: " + e.getMessage());
            throw new PersistenciaException("Error al actualizar el préstamo", e);
        }
    }

    @Override
    public boolean eliminar(int id) throws PersistenciaException {
        String sql = "DELETE FROM Prestamo WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                Logger.logInfo("Préstamo eliminado con éxito: " + id);
            } else {
                Logger.logWarning("No se encontró el préstamo para eliminar: " + id);
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            Logger.logError("Error al eliminar el préstamo: " + e.getMessage());
            throw new PersistenciaException("Error al eliminar el préstamo", e);
        }
    }

    public void crearTabla() throws PersistenciaException {
        String sql = "CREATE TABLE IF NOT EXISTS Prestamo (" +
                     "id INT PRIMARY KEY," +
                     "libro_id INT," +
                     "fechaPrestamo DATE," +
                     "fechaDevolucion DATE," +
                     "FOREIGN KEY (libro_id) REFERENCES Libro(id))";
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            Logger.logInfo("Tabla Prestamo creada con éxito");
        } catch (Exception e) {
            Logger.logError("Error al crear la tabla Prestamo: " + e.getMessage());
            throw new PersistenciaException("Error al crear la tabla Prestamo", e);
        }
    }
}