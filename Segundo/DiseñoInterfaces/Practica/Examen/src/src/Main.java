package src;

/**
 * Clase principal que inicia la aplicación de la calculadora.
 */
public class Main {
    /**
     * Método principal que crea y muestra la ventana de la calculadora.
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Window window = new Window("Calculadora");
        window.setVisible(true);
    }
}