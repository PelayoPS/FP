package src.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection(String url, String user, String password) throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos", e);
        }
    }

    public static DatabaseConnection getInstance()  {
        if (instance == null) {
            throw new IllegalStateException("DatabaseConnection no ha sido inicializado");
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}