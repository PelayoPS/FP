package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ejercicio6 {

    public static void ejecutarArchivoJAR() {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "java -jar src\\src\\Ejercicio3.jar");
            
            Process proceso = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            String linea;
            System.out.println("Salida del archivo JAR:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            // Lee y muestra la salida de error del JAR
            System.out.println("Errores del archivo JAR:");
            while ((linea = errorReader.readLine()) != null) {
                System.out.println(linea);
            }

            int exitCode = proceso.waitFor();
            System.out.println("El proceso JAR terminó con el código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar ejecutar el archivo JAR.");
            e.printStackTrace();
        }
    }

    public static void lanzarPrograma(String programa) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", programa);
            Process proceso = builder.start();
            int exitCode = proceso.waitFor();
            System.out.println("El proceso " + programa + " terminó con el código de salida: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar ejecutar el programa: " + programa);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ejecutarArchivoJAR();
        lanzarPrograma("notepad");
        lanzarPrograma("calc");
    }
}