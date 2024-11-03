package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ejercicio4 {

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

            int exitCode = proceso.waitFor();
            System.out.println("El proceso JAR terminó con el código de salida: " + exitCode);

        } catch (IOException e) {
            System.out.println("Error al intentar ejecutar el archivo JAR.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("El proceso fue interrumpido.");
            e.printStackTrace();
        }
    }

    public static void ejecutarArchivoJARConRuntime() {
        try {
            Process proceso = Runtime.getRuntime().exec("cmd /c java -jar src\\src\\Ejercicio3.jar");

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            String linea;
            System.out.println("Salida del archivo JAR:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            int exitCode = proceso.waitFor();
            System.out.println("El proceso JAR terminó con el código de salida: " + exitCode);

        } catch (IOException e) {
            System.out.println("Error al intentar ejecutar el archivo JAR.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("El proceso fue interrumpido.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ejecutarArchivoJAR();
        ejecutarArchivoJARConRuntime();
    }
}
