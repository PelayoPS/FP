package src;

import java.io.IOException;

public class Ejercicio7 {

    public static void abrirDocumentoTexto() {
        try {
            // Ruta al archivo de texto en el escritorio
            String rutaArchivo = System.getProperty("user.home") + "\\Desktop\\texto.txt";
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "start", rutaArchivo);

            // usando Runtime
            // Process proceso = Runtime.getRuntime().exec("cmd /c start " + rutaArchivo);

            Process proceso = builder.start();
            int exitCode = proceso.waitFor();
            System.out.println("El proceso para abrir el documento de texto terminó con el código de salida: " + exitCode);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar abrir el documento de texto.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        abrirDocumentoTexto();
    }
}
