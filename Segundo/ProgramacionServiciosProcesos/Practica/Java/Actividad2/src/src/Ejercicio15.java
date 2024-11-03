package src;

import java.io.File;
import java.io.IOException;

public class Ejercicio15 {

    public static void mostrarUsoCPU() {
        try {
            // Crear el ProcessBuilder para ejecutar el comando en PowerShell
            ProcessBuilder builder = new ProcessBuilder("powershell.exe", "/c", "Get-Process | Sort-Object -Property CPU");

            // Redirigir la salida al fichero cpu.txt
            File outputFile = new File("cpu.txt");
            builder.redirectOutput(outputFile);

            // Iniciar el proceso
            Process proceso = builder.start();

            // Esperar a que el proceso termine
            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con el código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar mostrar el uso de CPU.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mostrarUsoCPU();
    }
}
