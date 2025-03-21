package com.example.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLException;

import persistencia.GestorJDBC;

public class FirstActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etPassword;
    private Button btLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first);

        mapeaAtributosAVista();
        /*acción del botón:
        * - ¿usuario y contraseña campos válidos?
        * - si son campos válidos: tabla de login
        * -> ¿login correcto? siguiente activity...
        * -> ¿login incorrecto? Toast
        * */

        /*miro si tengo preguardados el usuario y la contraseña*/
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        String usuario = preferencias.getString("usuario", null);
        if (usuario!=null){
            String password = preferencias.getString("password", null);
            // llamo al login
            login(usuario, password, true);
        }

        this.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // miro que hay usuario escrito
                String usuario = etUsuario.getText().toString();
                if (usuario.isBlank()){
                    Toast.makeText(
                            getApplicationContext(),
                            "¿La de escribir el usuario te la sabes?",
                            Toast.LENGTH_SHORT
                            ).show();
                    return; // corto el método
                }
                // miro que hay contraseña escrita
                String password = etPassword.getText().toString();
                if (password.isBlank()){
                    Toast.makeText(
                            getApplicationContext(),
                            "¿La de escribir la contraseña te la sabes?",
                            Toast.LENGTH_SHORT
                    ).show();
                    return; // corto el método
                }

                // método login
                login(usuario, password, false); // no conozco datos hulio

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void mapeaAtributosAVista() {
        this.etUsuario = findViewById(R.id.etUsuario);
        this.etPassword = findViewById(R.id.etPassword);
        this.btLogin = findViewById(R.id.btLogin);
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

    private void segundaVentana(String public_id){
        // handler para asociar un proceso al hilo principal
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Intent segunda =
                        new Intent(FirstActivity.this,
                                SecondActivity.class);
                //extra: el public_id
                segunda.putExtra("public_id", public_id);
                startActivity(segunda);
            }
        });
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
                    // si el public id es nulo, no existes en la BD
                    if (public_id==null){
                        internalToast("I dont' even know who you are...", Toast.LENGTH_SHORT);
                    }
                    else{

                        if (!conocidos){
                            // guardo en preferencias los datos válidos
                            SharedPreferences preferencias =
                                    getSharedPreferences(
                                            getString(R.string.login),
                                            MODE_PRIVATE
                                    );
                            SharedPreferences.Editor editor = preferencias.edit();
                            editor.putString("usuario", usuario);
                            editor.putString("password", password);
                            //editor.commit();
                            editor.apply();
                        }

                        // a la segunda ventana le paso el public_id para obtener la imagen
                        segundaVentana(public_id);
                    }

                }
                catch (SQLException e) {
                            /*Toast.makeText(FirstActivity.this,
                                    e.toString(),
                                    Toast.LENGTH_SHORT).show();*/
                    System.out.println(e);
                    internalToast(e.toString(), Toast.LENGTH_SHORT);
                }
            }
        }).start();
    }

} // fin de class