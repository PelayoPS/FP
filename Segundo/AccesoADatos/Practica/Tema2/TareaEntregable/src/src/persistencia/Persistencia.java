package src.persistencia;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.excepciones.PersistenciaException;

/**
 * Clase que maneja la persistencia de objetos en archivos.
 */
public class Persistencia {

    /**
     * Exporta toda la información de la base de datos a un archivo CSV.
     *
     * @param filePath la ruta del archivo CSV
     * @throws PersistenciaException si ocurre un error al exportar los datos
     */
    public void exportarDatosCSV(String filePath) throws PersistenciaException {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            // Exportar autores
            ResultSet rs = stmt.executeQuery("SELECT * FROM autores");
            writer.println("ID,Nombre,FechaNacimiento,Nacionalidad");
            while (rs.next()) {
                writer.printf("%d,%s,%s,%s%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("nacionalidad"));
            }

            // Exportar libros
            rs = stmt.executeQuery("SELECT * FROM libros");
            writer.println("\nID,Titulo,Genero,Anio,AutorID");
            while (rs.next()) {
                writer.printf("%d,%s,%s,%d,%d%n",
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("anio"),
                        rs.getInt("autor_id"));
            }

            // Exportar prestamos
            rs = stmt.executeQuery("SELECT * FROM prestamos");
            writer.println("\nID,LibroID,FechaPrestamo,FechaDevolucion");
            while (rs.next()) {
                writer.printf("%d,%d,%s,%s%n",
                        rs.getInt("id"),
                        rs.getInt("libro_id"),
                        rs.getString("fecha_prestamo"),
                        rs.getString("fecha_devolucion"));
            }

        } catch (SQLException | IOException e) {
            throw new PersistenciaException("Error al exportar datos a CSV", e);
        }
    }

    /**
     * Importa todos los registros almacenados en un archivo CSV a la base de datos.
     *
     * @param filePath la ruta del archivo CSV
     * @throws PersistenciaException si ocurre un error al importar los datos
     */
    public void importarDatosCSV(String filePath) throws PersistenciaException {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            // Importar autores
            reader.readLine(); // Saltar la cabecera
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                stmt.executeUpdate(String.format("INSERT INTO autores (id, nombre, fecha_nacimiento, nacionalidad) VALUES (%d, '%s', '%s', '%s')",
                        Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]));
            }

            // Importar libros
            reader.readLine(); // Saltar la cabecera
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                stmt.executeUpdate(String.format("INSERT INTO libros (id, titulo, genero, anio, autor_id) VALUES (%d, '%s', '%s', %d, %d)",
                        Integer.parseInt(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]), Integer.parseInt(fields[4])));
            }

            // Importar prestamos
            reader.readLine(); // Saltar la cabecera
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                stmt.executeUpdate(String.format("INSERT INTO prestamos (id, libro_id, fecha_prestamo, fecha_devolucion) VALUES (%d, %d, '%s', '%s')",
                        Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2], fields[3]));
            }

        } catch (SQLException | IOException e) {
            throw new PersistenciaException("Error al importar datos desde CSV", e);
        }
    }
}