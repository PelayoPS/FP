package com.example.loginapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import persistencia.CloudinaryGestor;

public class SecondActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        mapeaAtributosAVista();

        // Obtener el public_id solo para registro/depuración

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el botón de Logout
        Button btnLogout = findViewById(R.id.btnLogout);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Borrar las preferencias guardadas
                    SharedPreferences preferences = getSharedPreferences(getString(R.string.login), MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();

                    // Navegar a FirstActivity
                    Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        // Configurar el botón de Exit
        Button btnExit = findViewById(R.id.btnExit);
        if (btnExit != null) {
            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Cerrar la aplicación completamente
                    finishAffinity();
                    System.exit(0);
                }
            });
        }

        // Configurar el botón de Galería
        Button btnGaleria = findViewById(R.id.btnGaleria);
        if (btnGaleria != null) {
            btnGaleria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // Navegar a ThirdActivity
                        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void mapeaAtributosAVista() {
        this.imageView = findViewById(R.id.imageView);
        this.progressBar = findViewById(R.id.progressBar);
    }

    private void setImagen(Bitmap bitmap) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
                imageView.setImageBitmap(bitmap);
            }
        };
        ejecutaEnHiloPrincipal(r);
    }

    private void ejecutaEnHiloPrincipal(Runnable r) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(r);
    }
}