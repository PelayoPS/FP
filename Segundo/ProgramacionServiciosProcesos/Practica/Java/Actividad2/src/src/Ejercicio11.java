package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ejercicio11 {

    public static void listarProcesos() {
        try {
            // Crear el ProcessBuilder para ejecutar el comando Get-Process en PowerShell
            ProcessBuilder builder = new ProcessBuilder("powershell.exe", "/c", "Get-Process");

            // Iniciar el proceso
            Process proceso = builder.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            System.out.println("Procesos en ejecución:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que el proceso termine
            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con el código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar listar los procesos.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        listarProcesos();
    }
}
