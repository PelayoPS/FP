package claves;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClavesJDBC {

    // constantes de clase de la conexión
    private static final String HOST = "bcmeoubxg58ehm98q6rj-mysql.services.clever-cloud.com";
    private static final String DB = "bcmeoubxg58ehm98q6rj";
    private static final int PORT = 3306;

    // públicas
    public static final String USER = "usqp6aiqemo4toih";
    public static final String PASSWORD = "UddUxCrHtEO5s1IlnXSj";
    public static final String URI = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB;

}
