package com.example.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLException;

import persistencia.GestorJDBC;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        misattributesLayout();

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*mostrar la progress bar
                * si tengo preferencias: a la segunda ventana
                * si no, a la de login*/
                //controladorProgressBar();
                SharedPreferences preferencias =
                        getSharedPreferences(
                                getString(R.string.login),
                                MODE_PRIVATE
                        );
                String usuario = preferencias.getString("usuario", null);
                if (usuario!=null){
                    String password = preferencias.getString("password", null);
                    // llamo al login
                    login(usuario, password, true);
                }
                else {
                    // voy a la first activity
                    Intent primera =  new Intent(MainActivity.this,
                                    FirstActivity.class);
                    //extra: el public_id
                    startActivity(primera);
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**Hace un bucle que aumenta el valor del progressbar*/


    private void misattributesLayout() {

        this.button = findViewById(R.id.button);

    }


    /**
     * @param usuario
     * @param  password
     * @param conocidos si sé los datos o no
     * */
    private void login(String usuario, String password, boolean conocidos){

        // acciones en red: hilos
        new Thread(new Runnable() {
            @Override
            public void run() {
                //consulta a la base de datos
                try {
                    String public_id = GestorJDBC.getInstance().login(usuario, password);
                    // a la segunda ventana le paso el public_id para obtener la imagen
                    segundaVentana(public_id);
                }
                catch (SQLException e) {
                    System.out.println(e);
                    internalToast(e.toString(), Toast.LENGTH_SHORT);
                }
            }
        }).start();
    }

    private void segundaVentana(String public_id){
        // handler para asociar un proceso al hilo principal
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent segunda =
                        new Intent(MainActivity.this,
                                SecondActivity.class);
                //extra: el public_id
                segunda.putExtra("public_id", public_id);
                startActivity(segunda);
            }
        });
    }

    private void internalToast(String texto, int duracion){
        // handler para asociar un proceso al hilo principal
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), ""+texto, duracion).show();
            }
        });
    }

} // fin de class