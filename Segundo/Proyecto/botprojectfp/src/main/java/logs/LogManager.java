package logs;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class LogManager {
    private final TextArea logArea;

    public LogManager(TextArea logArea) {
        this.logArea = logArea;
    }

    public void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
