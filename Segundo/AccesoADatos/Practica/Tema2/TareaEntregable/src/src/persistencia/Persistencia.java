package src.persistencia;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.excepciones.PersistenciaException;
import src.interfaz.BibliotecaApp;
import src.log.Logger;

/**
 * Clase que maneja la persistencia de objetos en archivos.
 */
public class Persistencia {

    /**
     * Exporta toda la información de la base de datos a archivos CSV separados.
     *
     * @param directoryPath la ruta del directorio donde se guardarán los archivos CSV
     * @throws PersistenciaException si ocurre un error al exportar los datos
     */
    public void exportarDatosCSV(String directoryPath) throws PersistenciaException {
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
             Statement stmt = conn.createStatement()) {

            // Eliminar archivos CSV existentes
            eliminarArchivoSiExiste(directoryPath + "/Autor.csv");
            eliminarArchivoSiExiste(directoryPath + "/Libro.csv");
            eliminarArchivoSiExiste(directoryPath + "/Prestamo.csv");

            // Exportar autores
            try (PrintWriter writer = new PrintWriter(new FileWriter(directoryPath + "/Autor.csv"))) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Autor");
                writer.println("ID,Nombre,FechaNacimiento,Nacionalidad");
                while (rs.next()) {
                    writer.printf("%d,%s,%s,%s%n",
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("fechaNacimiento"),
                            rs.getString("nacionalidad"));
                }
            }

            // Exportar libros
            try (PrintWriter writer = new PrintWriter(new FileWriter(directoryPath + "/Libro.csv"))) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Libro");
                writer.println("ID,Titulo,Genero,Anio,AutorID");
                while (rs.next()) {
                    writer.printf("%d,%s,%s,%d,%d%n",
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("genero"),
                            rs.getInt("anio"),
                            rs.getInt("autor_id"));
                }
            }

            // Exportar prestamos
            try (PrintWriter writer = new PrintWriter(new FileWriter(directoryPath + "/Prestamo.csv"))) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM Prestamo");
                writer.println("ID,LibroID,FechaPrestamo,FechaDevolucion");
                while (rs.next()) {
                    writer.printf("%d,%d,%s,%s%n",
                            rs.getInt("id"),
                            rs.getInt("libro_id"),
                            rs.getDate("fechaPrestamo"),
                            rs.getDate("fechaDevolucion"));
                }
            }

            Logger.logInfo("Datos exportados a CSV con éxito en el directorio: " + directoryPath);
        } catch (SQLException | IOException e) {
            Logger.logError("Error al exportar datos a CSV: " + e.getMessage());
            throw new PersistenciaException("Error al exportar datos a CSV", e);
        }
    }

    /**
     * Elimina un archivo si ya existe.
     *
     * @param filePath la ruta del archivo a eliminar
     */
    private void eliminarArchivoSiExiste(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (!file.delete()) {
                Logger.logWarning("No se pudo eliminar el archivo existente: " + filePath);
            }
        }
    }

    /**
     * Importa todos los registros almacenados en un archivo CSV a la base de datos.
     *
     * @param filePath la ruta del archivo CSV
     * @throws PersistenciaException si ocurre un error al importar los datos
     */
    public void importarDatosCSV(String filePath) throws PersistenciaException {
        try (Connection conn = DatabaseConnection.getInstance(
                BibliotecaApp.dbUrl, BibliotecaApp.dbUser, BibliotecaApp.dbPassword
        ).getConnection();
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
            Logger.logInfo("Datos importados desde CSV con éxito: " + filePath);
        } catch (SQLException | IOException e) {
            Logger.logError("Error al importar datos desde CSV: " + e.getMessage());
            throw new PersistenciaException("Error al importar datos desde CSV", e);
        }
    }
}