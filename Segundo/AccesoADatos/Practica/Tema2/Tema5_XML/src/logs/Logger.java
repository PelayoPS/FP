package logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Clase Logger para registrar mensajes de log en un archivo y en la consola.
 * Permite establecer diferentes niveles de log (INFO, WARNING, ERROR, FATAL)
 * y personalizar la salida.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class Logger {
    // Niveles de log
    /**
     * Enum que define los niveles de log disponibles.
     * Cada nivel tiene un nombre, un código de color y un nivel numérico.
     */
    public enum LogLevel {
        INFO(0, "INFO", "\u001B[32m"), // Verde
        WARNING(1, "WARNING", "\u001B[33m"), // Amarillo
        ERROR(2, "ERROR", "\u001B[31m"), // Rojo
        FATAL(3, "FATAL", "\u001B[35m"); // Púrpura

        private final int level;
        private final String name;
        private final String colorCode;

        LogLevel(int level, String name, String colorCode) {
            this.level = level;
            this.name = name;
            this.colorCode = colorCode;
        }

        public int getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }

        public String getColorCode() {
            return colorCode;
        }
    }

    // Constantes
    private static final String RESET_COLOR = "\u001B[0m";
    private static final String DEFAULT_LOG_PATH = "logs/logs.txt";
    private static final String LOG_FORMAT = "[%s] %s: %s";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Instancia singleton
    private static Logger instance;

    // Atributos
    private String logFilePath;
    private LogLevel minLevel;
    private boolean consoleOutput;

    /**
     * Constructor privado (patrón Singleton)
     * Inicializa el logger con la ruta por defecto y el nivel mínimo de log.
     */
    // Constructor privado (patrón Singleton)
    private Logger() {
        this.logFilePath = DEFAULT_LOG_PATH;
        this.minLevel = LogLevel.INFO;
        this.consoleOutput = true;

        // Crear directorio si no existe
        File logDir = new File(Paths.get(logFilePath).getParent().toString());
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }

    /**
     * Obtener la instancia única del logger (patrón Singleton)
     * 
     * @return Instancia de Logger
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Establecer la ruta del archivo de log
     * 
     * @param logFilePath Ruta del archivo de log
     */
    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    /**
     * Establecer el nivel mínimo de log
     * 
     * @param level Nivel mínimo de log
     */
    public void setMinLevel(LogLevel level) {
        this.minLevel = level;
    }

    /**
     * Establecer si se debe mostrar la salida en consola
     * 
     * @param consoleOutput true para habilitar la salida en consola, false para
     *                      deshabilitarla
     */
    public void setConsoleOutput(boolean consoleOutput) {
        this.consoleOutput = consoleOutput;
    }

    /**
     * Registrar un mensaje informativo
     * 
     * @param message Mensaje a registrar
     */
    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    /**
     * Registrar un mensaje de advertencia
     * 
     * @param message Mensaje a registrar
     */
    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    /**
     * Registrar un mensaje de error
     * 
     * @param message Mensaje a registrar
     */
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    /**
     * Registrar un mensaje fatal
     * 
     * @param message Mensaje a registrar
     */
    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    /**
     * Registrar un mensaje con un nivel específico
     * 
     * @param level   Nivel de log
     * @param message Mensaje a registrar
     */
    public void log(LogLevel level, String message) {
        // Comprobar nivel mínimo
        if (level.getLevel() < minLevel.getLevel()) {
            return;
        }

        // Preparar el mensaje formateado
        String timestamp = DATE_FORMAT.format(new Date());
        String formattedMessage = String.format(LOG_FORMAT, timestamp, level.getName(), message);
        String coloredMessage = level.getColorCode() + formattedMessage + RESET_COLOR;

        // Mostrar en consola si está habilitado
        if (consoleOutput) {
            System.out.println(coloredMessage);
        }

        // Escribir en archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(formattedMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Leer todos los logs del archivo
     * 
     * @return Lista de líneas de log
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public List<String> readAllLogs() throws IOException {
        return Files.readAllLines(Paths.get(logFilePath));
    }

    /**
     * Leer los logs y devolverlos con colores
     * 
     * @return String con los logs coloreados
     */
    public String getColoredLogs() {
        StringBuilder result = new StringBuilder();
        try {
            List<String> logs = readAllLogs();
            for (String log : logs) {
                // Determinar nivel y aplicar color correspondiente
                String colorCode = LogLevel.INFO.getColorCode();
                if (log.contains("WARNING")) {
                    colorCode = LogLevel.WARNING.getColorCode();
                } else if (log.contains("ERROR")) {
                    colorCode = LogLevel.ERROR.getColorCode();
                } else if (log.contains("FATAL")) {
                    colorCode = LogLevel.FATAL.getColorCode();
                }

                result.append(colorCode).append(log).append(RESET_COLOR).append("\n");
            }
        } catch (IOException e) {
            result.append(LogLevel.ERROR.getColorCode())
                    .append("Error al leer los logs: ").append(e.getMessage())
                    .append(RESET_COLOR);
        }
        return result.toString();
    }

    /**
     * Limpiar el archivo de log
     * 
     */
    public void clearLogs() {
        try {
            Files.write(Paths.get(logFilePath), new byte[0]);
        } catch (IOException e) {
            System.err.println("Error al limpiar el archivo de log: " + e.getMessage());
        }
    }
}
