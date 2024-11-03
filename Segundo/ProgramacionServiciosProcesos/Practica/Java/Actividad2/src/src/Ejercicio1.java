package src;
import java.io.IOException;

public class Ejercicio1 {

    // Función para lanzar un programa externo usando ProcessBuilder
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

    // Función para lanzar un programa externo usando Runtime
    public static void LanzarProgramaConRuntime(String comando) {
        try {
            Process proceso = Runtime.getRuntime().exec(comando);
            
            long pid = proceso.pid();
            System.out.println("Proceso lanzado con PID: " + pid);
            
        } catch (IOException e) {
            System.out.println("Error al intentar ejecutar el comando: " + comando);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LanzarPrograma("calc");
        LanzarProgramaConRuntime("notepad");
    }
}