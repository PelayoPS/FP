package src;

public class Ejercicio8 {

    public static void mostrarVariablesEntorno() {
        // Obtiene las variables de entorno
        java.util.Map<String, String> variablesEntorno = System.getenv();

        // Muestra las variables de entorno por consola
        System.out.println("Variables de entorno del proceso:");
        for (java.util.Map.Entry<String, String> entry : variablesEntorno.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        mostrarVariablesEntorno();
    }
}
