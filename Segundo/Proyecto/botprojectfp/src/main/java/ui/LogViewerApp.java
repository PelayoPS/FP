package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogViewerApp extends Application {

    private TextArea logArea; // Área de texto para los logs

    @Override
    public void start(Stage stage) {
        // Crear el área de texto para los logs
        logArea = new TextArea();
        logArea.setEditable(false); // Solo lectura
        logArea.setWrapText(true);  // Ajustar texto automáticamente

        // Botón para simular entrada de log
        Button addLogButton = new Button("Añadir Log");
        addLogButton.setOnAction(event -> addLog("Este es un nuevo mensaje de log"));

        // Disposición vertical
        VBox layout = new VBox(10, logArea, addLogButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));
        
        // Configurar la escena
        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle("Visor de Logs");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Añade un mensaje al área de logs.
     * @param message El mensaje a añadir.
     */
    private void addLog(String message) {
        logArea.appendText(message + "\n");
    }

    public static void main(String[] args) {
        // Lanzar la aplicación JavaFX
        launch(args);
    }
}
