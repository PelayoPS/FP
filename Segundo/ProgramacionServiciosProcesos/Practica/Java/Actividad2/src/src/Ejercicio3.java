package src;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ejercicio3 {

    public static void ejecutarPing() {
        try {
            ProcessBuilder builder = new ProcessBuilder("ping", "-n", "3", "google.com");
            Process proceso = builder.start();

            InputStream salida = proceso.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(salida));

            String linea;
            System.out.println("Salida del comando ping:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con el código de salida: " + exitCode);

        } catch (IOException e) {
            System.out.println("Error al ejecutar el comando ping.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("El proceso fue interrumpido.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Ejecuta la función para realizar el ping
        ejecutarPing();
    }
}

