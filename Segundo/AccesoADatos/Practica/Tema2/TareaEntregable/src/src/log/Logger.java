package src.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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

    /**
     * Logs an informational message.
     *
     * @param message the message to log
     */
    public static void logInfo(String message) {
        log("INFO", message, BLUE_COLOR);
    }

    /**
     * Logs a warning message.
     *
     * @param message the message to log
     */
    public static void logWarning(String message) {
        log("WARN", message, YELLOW_COLOR);
    }

    /**
     * Logs an error message.
     *
     * @param message the message to log
     */
    public static void logError(String message) {
        log("ERROR", message, RED_COLOR);
    }

    /**
     * Muestra los logs en consola con el código de color correspondiente.
     */
    public static void mostrarLogs() {
        Logger.logInfo("Mostrar logs");
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("[INFO]")) {
                    System.out.println(BLUE_COLOR + line + RESET_COLOR);
                } else if (line.contains("[WARN]")) {
                    System.out.println(YELLOW_COLOR + line + RESET_COLOR);
                } else if (line.contains("[ERROR]")) {
                    System.out.println(RED_COLOR + line + RESET_COLOR);
                } else {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs a message with a specified level and color.
     *
     * @param level   the level of the log (INFO, WARN, ERROR)
     * @param message the message to log
     * @param color   the color to use for console output
     */
    private static void log(String level, String message, String color) {
        String timestamp = LocalDateTime.now().format(DATE_FORMATTER);
        String logMessage = String.format("[%s] [%s] %s", timestamp, level, message);

        // Print colored message to the console
        //System.out.println(color + logMessage + RESET_COLOR);

        // Write uncolored message to the log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
