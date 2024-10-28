package com.example.examentema3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button goSecondaryButton;
    private Button goWebsiteButton;
    private EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goSecondaryButton = findViewById(R.id.secondaryActivity);
        goWebsiteButton = findViewById(R.id.website);
        inputText = findViewById(R.id.nameEditText);

        goSecondaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondaryActivity.class);
                // Pasar el texto del EditText a la otra Activity
                String textToSend = inputText.getText().toString();
                intent.putExtra("text_key", textToSend);
                startActivity(intent);
            }
        });

        goWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(android.net.Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
        });
    }
}