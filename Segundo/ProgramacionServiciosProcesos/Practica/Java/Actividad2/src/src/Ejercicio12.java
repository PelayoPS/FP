package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ejercicio12 {

    public static void iniciarProceso() {
        try {
            // Crear el ProcessBuilder para ejecutar el comando Start-Process en PowerShell
            ProcessBuilder builder = new ProcessBuilder("powershell.exe", "/c", "Start-Process", "notepad.exe");

            // Iniciar el proceso
            Process proceso = builder.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            System.out.println("Salida del comando Start-Process:");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }

            // Esperar a que el proceso termine
            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con el código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar iniciar el proceso.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        iniciarProceso();
    }
}
