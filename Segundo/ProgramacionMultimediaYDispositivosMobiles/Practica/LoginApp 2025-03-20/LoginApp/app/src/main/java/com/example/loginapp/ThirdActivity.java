package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import persistencia.CloudinaryGestor;

public class ThirdActivity extends AppCompatActivity {

    private static final String TAG = "ThirdActivity"; // Tag para logs
    // Constante con el nombre de la carpeta
    private static final String FOLDER_NAME = "palacio_suarez_pelayo";

    // Atributos de tipo ImageView
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Mostrar mensaje al iniciar
        Toast.makeText(this, "Buscando imágenes en Cloudinary...", Toast.LENGTH_LONG).show();

        // Llamadas a los métodos
        mapeaAtributosAVista();
        cargaImagenesCloudinary();
    }

    /**
     * Ejecuta un Runnable en el hilo principal de la UI
     */
    private void ejecutaEnHiloPrincipal(Runnable runnable) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(runnable);
    }

    /**
     * Establece una imagen (bitmap) en el ImageView especificado
     */
    private void setImagen(Bitmap bitmap, ImageView imageView) {
        ejecutaEnHiloPrincipal(() -> {
            if (bitmap != null && imageView != null) {
                imageView.setImageBitmap(bitmap);
                Log.d(TAG, "Imagen cargada correctamente en ImageView");
            } else {
                Log.e(TAG, "No se pudo cargar la imagen: bitmap o imageView es null");
            }
        });
    }

    /**
     * Mapea los atributos de la clase a las vistas del layout
     */
    private void mapeaAtributosAVista() {
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        Log.d(TAG, "Vistas mapeadas correctamente");
    }

    /**
     * Carga las imágenes desde Cloudinary
     */
    private void cargaImagenesCloudinary() {
        // Crear una lista con los ImageView
        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(imageView1);
        imageViews.add(imageView2);
        imageViews.add(imageView3);
        imageViews.add(imageView4);

        // Crear un nuevo hilo
        Thread thread = new Thread(() -> {
            try {
                Log.d(TAG, "Iniciando carga de imágenes desde Cloudinary");
                // Obtener los public_ids de la carpeta usando CloudinaryGestor
                CloudinaryGestor gestor = CloudinaryGestor.getInstance();
                List<String> publicIds = gestor.listAssets(FOLDER_NAME);

                Log.d(TAG, "Public IDs encontrados: " + publicIds.size());

                if (publicIds.isEmpty()) {
                    ejecutaEnHiloPrincipal(() -> {
                        Toast.makeText(ThirdActivity.this,
                                "No se encontraron imágenes en la carpeta " + FOLDER_NAME,
                                Toast.LENGTH_LONG).show();
                    });
                    return;
                }

                // Recorrer las listas por posiciones
                for (int i = 0; i < Math.min(imageViews.size(), publicIds.size()); i++) {
                    // Obtener el ImageView correspondiente
                    ImageView imageView = imageViews.get(i);

                    // Obtener el public_id correspondiente
                    String publicId = publicIds.get(i);
                    Log.d(TAG, "Procesando imagen " + (i + 1) + ", public_id: " + publicId);

                    // Obtener la URL segura directamente desde CloudinaryGestor
                    String urlString = gestor.get(publicId);

                    if (urlString != null) {
                        Log.d(TAG, "URL obtenida: " + urlString);
                        // Crear objeto URL
                        URL url = new URL(urlString);

                        // Crear bitmap desde la URL
                        Bitmap bitmap = crearBitmapDesdeUrl(url);

                        // Establecer el bitmap en el ImageView
                        setImagen(bitmap, imageView);
                    } else {
                        Log.e(TAG, "URL nula para public_id: " + publicId);
                    }
                }

                // Si no hay suficientes imágenes, mostrar un mensaje
                if (publicIds.size() < imageViews.size()) {
                    ejecutaEnHiloPrincipal(() -> {
                        Toast.makeText(ThirdActivity.this,
                                "Solo se encontraron " + publicIds.size() + " imágenes",
                                Toast.LENGTH_SHORT).show();
                    });
                }

            } catch (Exception e) {
                Log.e(TAG, "Error al cargar imágenes: " + e.getMessage(), e);
                ejecutaEnHiloPrincipal(() -> {
                    Toast.makeText(ThirdActivity.this,
                            "Error al cargar imágenes: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
            }
        });

        // Arrancar el hilo
        thread.start();
        Log.d(TAG, "Hilo de carga de imágenes iniciado");
    }

    /**
     * Crea un bitmap a partir de una URL
     */
    private Bitmap crearBitmapDesdeUrl(URL url) throws Exception {
        try {
            InputStream inputStream = url.openConnection().getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            if (bitmap != null) {
                Log.d(TAG, "Bitmap creado correctamente: " + bitmap.getWidth() + "x" + bitmap.getHeight());
                return bitmap;
            } else {
                Log.e(TAG, "BitmapFactory devolvió null");
                throw new Exception("No se pudo decodificar la imagen");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al crear bitmap desde URL: " + e.getMessage(), e);
            throw e;
        }
    }
}
