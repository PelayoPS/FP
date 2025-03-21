package persistencia;

import static claves.ClavesJDBC.PASSWORD;
import static claves.ClavesJDBC.URI;
import static claves.ClavesJDBC.USER;

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

public class GestorJDBC extends Service {

    // singleton
    public static GestorJDBC gestor;

    // atributos
    private Connection conn;
    private Statement state;
    private boolean abierto; // si está abierto lo que necesito

    /**Método que abre todo si no estaba abierto*/
    private void open(){
        // si ya estaba todo abierto, pues nada
        if (abierto) return;
        try {
            // no hace falta: compruebo que tengo el driver necesario
            //Class.forName("com.mysql.cj.jdbc.Driver");
            // arranco el atributo conn
            conn = DriverManager.getConnection(URI, USER, PASSWORD);
            // con la conexión hecha, genero un statement
            if (conn!=null){
                state = conn.createStatement();
                System.out.println("Conexion correcta");
                abierto = true; // conseguí abrir
            }
            else{
                System.out.println("Conexión incorrecta");
            }
        }

        catch (SQLException e) {
            System.err.println("Error de conexion?");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**Método para cerrar conexiones*/
    private void close(){
        if (state!=null){
            try {
                state.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        abierto = false;
    }

    private GestorJDBC(){
        abierto = false; // me sobra
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // singleton: acceso a la instancia
    public static GestorJDBC getInstance(){
        if (gestor==null) gestor = new GestorJDBC();
        return gestor;
    }

    /**Método que con el usuario y la contraseña
     * me devuelve el public_id o null si no son
     * están en la base de datos*/
    public String login(String usuario, String password)
            throws SQLException {
        open(); // abro conexiones
        String query =
                "SELECT public_id " +
                "FROM Login "+
                "WHERE user = ? AND password = ?";
        PreparedStatement ps  = getInstance().
                getConnection().
                prepareStatement(query);
        ps.setString(1, usuario);
        ps.setString(2, password);
        // ejecuto la consulta en sí
        ResultSet rs = ps.executeQuery();
        String public_id = null;
        // si hay ese usuario y contraseña...
        if (rs.next()){
            public_id = rs.getString("public_id");
        }
        rs.close();
        ps.close();
        close(); // cierro conexiones

        return public_id;

    }

    private Connection getConnection() {
        return conn;
    }

}
