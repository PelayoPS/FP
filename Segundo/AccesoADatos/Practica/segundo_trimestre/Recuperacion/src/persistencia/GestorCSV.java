package persistencia;

import java.util.ArrayList;
import java.util.List;

import logica.Empleado;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Clase que gestiona la lectura y escritura de empleados en un archivo CSV.
 */
public class GestorCSV {

    /**
     * Lee los empleados desde el archivo CSV.
     * 
     * @return Lista de empleados leídos del archivo.
     */
    public List<Empleado> leerEmpleados(String ruta) {
        List<Empleado> empleados = new ArrayList<Empleado>();
        verificarArchivo(ruta);
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String line;
            while ((line = br.readLine()) != null) {
                empleados.add(Empleado.deserializar(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    /**
     * Escribe los empleados en el archivo CSV.
     * 
     * @param empleados Lista de empleados a escribir en el archivo.
     */
    public void escribirEmpleados(List<Empleado> empleados, String ruta) {
        try {
            List<String> lines = empleados.stream()
                    .map(Empleado::serializar)
                    .collect(Collectors.toList());
            Files.write(Paths.get(ruta), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica la existencia del archivo y su contenido.
     * 
     * @param ruta Ruta del archivo a verificar.
     */
    public void verificarArchivo(String ruta) {
        File file = new File(ruta);
        if (!file.exists()) {
            System.out.println("El archivo no existe.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(ruta));
            List<String> validLines = lines.stream()
                    .filter(line -> line.split(",").length == 6)
                    .collect(Collectors.toList());

            Files.write(Paths.get(ruta), validLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
