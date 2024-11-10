package src.log;

public class LogManager {
    public enum LogLevel {
        INFO,
        WARN,
        ERROR
    }

    private static LogLevel currentLogLevel = LogLevel.INFO;

    /**
     * Establece el nivel de registro actual.
     *
     * @param logLevel el nivel de registro a establecer
     */
    public static void setLogLevel(LogLevel logLevel) {
        currentLogLevel = logLevel;
    }

    /**
     * Registra un mensaje informativo si el nivel de registro actual lo permite.
     *
     * @param message el mensaje a registrar
     */
    public static void logInfo(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            Logger.logInfo(message);
        }
    }

    /**
     * Registra un mensaje de advertencia si el nivel de registro actual lo permite.
     *
     * @param message el mensaje a registrar
     */
    public static void logWarning(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.WARN.ordinal()) {
            Logger.logWarning(message);
        }
    }

    /**
     * Registra un mensaje de error si el nivel de registro actual lo permite.
     *
     * @param message el mensaje a registrar
     */
    public static void logError(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            Logger.logError(message);
        }
    }
}
