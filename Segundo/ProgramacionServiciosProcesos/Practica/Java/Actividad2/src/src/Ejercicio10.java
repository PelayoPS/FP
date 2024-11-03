package src;

import java.io.File;
import java.io.IOException;

public class Ejercicio10 {

    public static void listarArchivosDirectorio() {
        try {
            String directorio = "src\\src";

            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "dir", directorio);

            // usando Runtime
            // Process proceso = Runtime.getRuntime().exec("cmd /c dir " + directorio);

            File outputFile = new File("output.txt");
            builder.redirectOutput(outputFile);

            Process proceso = builder.start();

            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con el código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar listar los archivos del directorio.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        listarArchivosDirectorio();
    }
}
