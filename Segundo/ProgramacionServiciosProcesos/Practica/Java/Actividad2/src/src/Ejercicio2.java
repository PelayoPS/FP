package src;

import java.io.IOException;

public class Ejercicio2 {

    // Función para lanzar un programa externo
    public static void LanzarPrograma(String comando) {
        try {
            Process proceso = new ProcessBuilder(comando).start();
            
            long pid = proceso.pid();
            System.out.println("Proceso lanzado con PID: " + pid);
            
        } catch (IOException e) {
            System.out.println("Error al intentar ejecutar el comando: " + comando);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LanzarPrograma("mspaint");
        LanzarPrograma("mspaint");
        
    }
}
