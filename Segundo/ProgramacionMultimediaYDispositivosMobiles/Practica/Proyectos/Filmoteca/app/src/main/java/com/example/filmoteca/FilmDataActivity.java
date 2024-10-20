package com.example.filmoteca;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FilmDataActivity extends AppCompatActivity {

    private Button editarPelicula;
    private Button volver;
    private TextView mostrarPelicula;
    private ImageView imagenPelicula;
    private int drawableId;

    private static final int EDIT_PELICULA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        HashMap<String, Integer> movieDrawables = new HashMap<>();
        movieDrawables.put("regreso al futuro", R.drawable.regreso_futuro);
        movieDrawables.put("el padrino", R.drawable.el_padrino);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_data);

        editarPelicula = findViewById(R.id.EditarPelicula);
        volver = findViewById(R.id.volverPrincipal);
        mostrarPelicula = findViewById(R.id.DatosPelicula);
        imagenPelicula = findViewById(R.id.imagen_pelicula);
        String nombreP;

        nombreP = this.getIntent().getExtras().get("nombre").toString();

        mostrarPelicula.setText(nombreP);

        editarPelicula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilmDataActivity.this, FilmEditActivity.class);
                intent.putExtra("image", drawableId);
                startActivityForResult(intent, 1);
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilmDataActivity.this, FilmListActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle = this.getIntent().getExtras();

        String pelicula = bundle.get("pelicula").toString();
        mostrarPelicula.setText(pelicula);
        String movieTitleLower = pelicula.toLowerCase();
        if (movieDrawables.containsKey(movieTitleLower)) {
            drawableId = movieDrawables.get(movieTitleLower);
            imagenPelicula.setImageDrawable(getResources().getDrawable(drawableId));
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_PELICULA_REQUEST) {
            if(resultCode == RESULT_OK) {
                mostrarPelicula.setText("Pelicula editada correctamente");
            } else if (resultCode == RESULT_CANCELED) {
                mostrarPelicula.setText("Edición cancelada");
            }
        }
    }
}
