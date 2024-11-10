package log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE_PATH = "app.log";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // ANSI color codes for console output
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String BLUE_COLOR = "\u001B[34m";
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String RED_COLOR = "\u001B[31m";

    public static void logInfo(String message) {
        log("INFO", message, BLUE_COLOR);
    }

    public static void logWarning(String message) {
        log("WARN", message, YELLOW_COLOR);
    }

    public static void logError(String message) {
        log("ERROR", message, RED_COLOR);
    }

    private static void log(String level, String message, String color) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String logMessage = String.format("[%s] [%s] %s", timestamp, level, message);

        // Print colored message to the console
        System.out.println(color + logMessage + RESET_COLOR);

        // Write uncolored message to the log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
