package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Ejercicio13 {

    public static void main(String[] args) {
        try {
            // Crear el primer proceso
            ProcessBuilder builder1 = new ProcessBuilder("cmd", "/c", "echo Proceso1");
            Process proceso1 = builder1.start();
            // imprime el resultado del proceso 1
            InputStream salida = proceso1.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(salida));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            int exitCode1 = proceso1.waitFor();
            System.out.println("Proceso1 terminó con el código de salida: " + exitCode1);

            // Verificar el código de salida del primer proceso
            if (exitCode1 == 0) {
                // Crear y ejecutar el proceso 1.1
                ProcessBuilder builder1_1 = new ProcessBuilder("cmd", "/c", "echo Proceso1.1");
                Process proceso1_1 = builder1_1.start();
                // imprime el resultado del proceso 1.1
                salida = proceso1_1.getInputStream();
                reader = new BufferedReader(new InputStreamReader(salida));
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
                int exitCode1_1 = proceso1_1.waitFor();
                System.out.println("Proceso1.1 terminó con el código de salida: " + exitCode1_1);
            } else {
                // Crear y ejecutar el proceso 1.2
                ProcessBuilder builder1_2 = new ProcessBuilder("cmd", "/c", "echo Proceso1.2");
                Process proceso1_2 = builder1_2.start();
                // imprime el resultado del proceso 1.2
                salida = proceso1_2.getInputStream();
                reader = new BufferedReader(new InputStreamReader(salida));

                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
                int exitCode1_2 = proceso1_2.waitFor();
                System.out.println("Proceso1.2 terminó con el código de salida: " + exitCode1_2);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al intentar ejecutar los procesos.");
            e.printStackTrace();
        }
    }
}
