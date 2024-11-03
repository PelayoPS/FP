package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Ejercicio14 {

    public static void finalizarProceso() {
        try {
            // Proceso a finalizar 
            String proceso = "notepad";

            // Pedir confirmación al usuario
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("¿Desea finalizar el proceso " + proceso + "? (s/n): ");
            String respuesta = reader.readLine();

            if (respuesta.equalsIgnoreCase("s")) {
                // Crear el ProcessBuilder para ejecutar el comando Stop-Process en PowerShell
                ProcessBuilder builder = new ProcessBuilder("powershell.exe", "/c", "Stop-Process -Name " + proceso);

                // usando Runtime
                // Process proceso = Runtime.getRuntime().exec("powershell.exe /c Stop-Process -Name " + proceso);

                // Iniciar el proceso
                Process procesoFinalizar = builder.start();

                // Leer la salida del proceso
                BufferedReader outputReader = new BufferedReader(new InputStreamReader(procesoFinalizar.getInputStream()));
                String linea;
                System.out.println("Salida del comando Stop-Process:");
                while ((linea = outputReader.readLine()) != null) {
                    System.out.println(linea);
                }

                // Esperar a que el proceso termine
                int exitCode = procesoFinalizar.waitFor();
                System.out.println("El proceso terminó con el código de salida: " + exitCode);
            } else {
                System.out.println("Operación cancelada por el usuario.");
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar finalizar el proceso.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        finalizarProceso();
    }
}
