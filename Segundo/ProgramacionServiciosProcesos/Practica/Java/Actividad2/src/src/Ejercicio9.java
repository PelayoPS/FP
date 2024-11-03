package src;

import java.io.IOException;

public class Ejercicio9 {
public static void mostrarVariablesEntorno() {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "echo %MI_VARIABLE%");

            // usando Runtime
            // Process proceso = Runtime.getRuntime().exec("cmd /c echo %MI_VARIABLE%");

            // Modifica las variables de entorno del proceso
            java.util.Map<String, String> environment = builder.environment();
            environment.put("MI_VARIABLE", "VALOR_PERSONALIZADO");

            // Inicia el proceso
            Process proceso = builder.start();

            // Muestra las variables de entorno por consola
            System.out.println("Variables de entorno del proceso:");
            for (java.util.Map.Entry<String, String> entry : environment.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            // eliminar la variable de entorno
            environment.remove("MI_VARIABLE");

            // Espera a que el proceso termine
            int exitCode = proceso.waitFor();
            System.out.println("El proceso terminó con el código de salida: " + exitCode);

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar modificar las variables de entorno.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mostrarVariablesEntorno();
    }
}
