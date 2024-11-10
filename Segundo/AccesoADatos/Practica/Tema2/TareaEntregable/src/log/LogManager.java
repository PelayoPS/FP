package log;

public class LogManager {
    public enum LogLevel {
        INFO,
        WARN,
        ERROR
    }

    private static LogLevel currentLogLevel = LogLevel.INFO;

    public static void setLogLevel(LogLevel logLevel) {
        currentLogLevel = logLevel;
    }

    public static void logInfo(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            Logger.logInfo(message);
        }
    }

    public static void logWarning(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.WARN.ordinal()) {
            Logger.logWarning(message);
        }
    }

    public static void logError(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            Logger.logError(message);
        }
    }
}
