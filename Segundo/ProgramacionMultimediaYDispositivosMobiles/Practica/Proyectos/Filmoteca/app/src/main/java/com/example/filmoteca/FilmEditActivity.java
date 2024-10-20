package com.example.filmoteca;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FilmEditActivity extends AppCompatActivity {

    private Button guardar;
    private Button cancelar;
    private ImageView verImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_film_edit);

        guardar = findViewById(R.id.guardar);
        cancelar = findViewById(R.id.cancelar);
        verImagen = findViewById(R.id.imagenPelicula);

        int imagen = this.getIntent().getExtras().getInt("image");

        verImagen.setImageDrawable(getResources().getDrawable(imagen));


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}
