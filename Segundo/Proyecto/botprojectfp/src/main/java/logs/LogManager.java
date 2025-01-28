package logs;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class LogManager {
    private final TextArea logArea;

    // Colors:
    // - Black: Normal
    // - Red: Error
    // - Blue: Info
    // - Green: Success
    // - Orange: Warning
    String types = "NBGRO";

    public LogManager(TextArea logArea) {
        this.logArea = logArea;
    }

    public void log(String type, String message) {
        // Get the color based on the type
        String color = switch (type) {
            case "N" -> "black";
            case "E" -> "red";
            case "I" -> "blue";
            case "S" -> "green";
            case "W" -> "orange";
            default -> "black";
        };

        // Append the message to the logArea
        Platform.runLater(() -> {
            logArea.appendText(String.format("[%s] %s\n", type, message));
            logArea.setStyle(String.format("-fx-text-fill: %s;", color));
        });

    }

    public void clear() {
        Platform.runLater(() -> logArea.clear());
    }
}
