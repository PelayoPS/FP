package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginApp extends Application {

    private Stage primaryStage;
    private StackPane rootPane;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.rootPane = new StackPane();
        showLoginScreen();
        
        Scene scene = new Scene(rootPane, 400, 300);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("Aplicación");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLoginScreen() {
        // Crear los elementos de la interfaz
        Label userLabel = new Label("Usuario:");
        TextField userField = new TextField();
        
        Label passLabel = new Label("Contraseña:");
        PasswordField passField = new PasswordField();
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> showMainScreen());
        
        // Crear el layout y añadir los elementos
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginButton, 1, 2);
        
        // Añadir el layout al rootPane
        rootPane.getChildren().setAll(grid);
    }

    private void showMainScreen() {
        // Crear el botón para cargar la ventana de logs
        Button showLogsButton = new Button("Mostrar Logs");
        showLogsButton.setOnAction(event -> showLogViewerScreen());
        
        // Crear el layout y añadir el botón
        VBox layout = new VBox(10, showLogsButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));
        
        // Añadir el layout al rootPane
        rootPane.getChildren().setAll(layout);
    }

    private void showLogViewerScreen() {
        // Crear el área de texto para los logs
        TextArea logArea = new TextArea();
        logArea.setEditable(false); // Solo lectura
        logArea.setWrapText(true);  // Ajustar texto automáticamente

        // Botón para simular entrada de log
        Button addLogButton = new Button("Añadir Log");
        addLogButton.setOnAction(event -> logArea.appendText("Este es un nuevo mensaje de log\n"));

        // Disposición vertical
        VBox layout = new VBox(10, logArea, addLogButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));
        
        // Añadir el layout al rootPane
        rootPane.getChildren().setAll(layout);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
