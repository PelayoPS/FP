package com.example.filmoteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class FilmListActivity extends AppCompatActivity {

    private Button verPelicula;
    private Button acercaDe;
    private EditText textoPelicula;
    private List<String> listaPeliculas = Arrays.asList("regreso al futuro", "el padrino");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);

        verPelicula = findViewById(R.id.ver_Pelicula);
        acercaDe = findViewById(R.id.acercaDe);
        textoPelicula = findViewById(R.id.pelicula);

        verPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilmListActivity.this, FilmDataActivity.class);
                intent.putExtra("nombre", textoPelicula.getText());
                if (textoPelicula.getText()!=null && !textoPelicula.getText().toString().isEmpty() &&
                        listaPeliculas.contains(textoPelicula.getText().toString().toLowerCase())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("pelicula", textoPelicula.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilmListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    };
}
