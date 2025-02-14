package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Util {

	/**
	 * Lee el archivo csv
	 * @param path
	 * @return
	 */
    public static List<String> readCSV(String path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return lines;
    }

    /**
     * Guarda el archivo csv
     * @param path
     * @param lines
     */
    public static void saveCSV(String path, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }

    }
}
