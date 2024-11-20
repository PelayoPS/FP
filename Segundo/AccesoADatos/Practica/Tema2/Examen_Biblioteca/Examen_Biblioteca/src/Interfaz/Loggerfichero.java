package Interfaz;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Loggerfichero {
    private static Loggerfichero log;
    BufferedWriter buffer;

    private Loggerfichero() {
        try {
            String ficheroLog = "src\\logs\\log.txt";
            // true añade al final
            buffer = new BufferedWriter(new FileWriter(ficheroLog, true));
        } catch (IOException ex) {
            System.out.println("ERROR al abrir fichero");
        }
    }

    public static Loggerfichero getInstance() {
        if (null == log) {
            log = new Loggerfichero();
        }
        return log;
    }

    public void writeSmg(String msg) {
        String linea = LocalDateTime.now() + "\n";
        linea += "\t" + msg;
        try {
            buffer.write(linea);
            buffer.flush();
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
    }

    public void closeLog() {
        try {
            buffer.close();
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
    }
}
