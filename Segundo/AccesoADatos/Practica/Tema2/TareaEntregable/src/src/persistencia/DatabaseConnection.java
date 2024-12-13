package src.persistencia;

import src.log.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    public DatabaseConnection(String url, String user, String password) throws SQLException {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            Logger.logError("Error al cargar el driver JDBC de MySQL: " + e.getMessage());
            throw new SQLException("Error al cargar el driver JDBC de MySQL", e);
        } catch (SQLException e) {
            Logger.logError("Error al conectar a la base de datos: " + e.getMessage());
            throw new SQLException("Error al conectar a la base de datos", e);
        }
    }

    public static DatabaseConnection getInstance(String url, String user, String password) throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection(url, user, password);
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection(url, user, password);
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}