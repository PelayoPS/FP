package src;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class DGT {

    private TreeSet<Conductor> conductores;

    public DGT() {
        conductores = new TreeSet<>();
    }

    public void saveConductor(Conductor conductor) {
        if (!conductores.add(conductor)) {
            System.err.println("Error: DNI duplicado: " + conductor.getDNI());
        }
    }

    /**
     * Método que lee los datos del csv
     * Errores:
     * - Líneas incorrectas(formato:
     * DNI,NOMBRE,APELLIDO1,APELLIDO2,TIPO_CARNE,FECHA_APROBACION,PUNTOS,FECHA_RENOVACION)
     * - DNI duplicado
     * 
     * @param fichero
     */
    public void leerDatos() {
        String path = "resources\\dgt.csv";
        List<String> lines = Util.readCSV(path);

        // elimina las líneas con errores
        List<String> fixedLines = fixLines(lines);

        // añade el conductor sólo si el dni no existe ya en el set
        fixedLines.forEach(line -> {
        	Conductor conductor = parseLine(line);
            if (!conductores.add(conductor)) {
                System.err.println("Error: DNI duplicado: " + conductor.getDNI());
            }
        });

    }

    public void listConductores() {
        conductores.forEach(
                conductor -> System.out.println(conductor.toString()));
    }

    public void listNoPuntos() {
        conductores.stream()
                .filter(conductor -> conductor.getPuntos() == 0)
                .forEach(conductor -> System.out.println(conductor.toString()));
    }

    public void saveConductores() {
        String path = "resources\\";
        String fileName = path + "dgt_2.csv";
        List<String> lines = new ArrayList<>();
        conductores.forEach(conductor -> {
            if (conductor.getPuntos() == 15) {
                lines.add(conductor.getDNI() + ";" + conductor.getNombre() + ";" + conductor.getTipoCarnet());
            }
        });

        Util.saveCSV(fileName, lines);
    }

    private List<String> fixLines(List<String> lines) {
        List<String> fixedLines = new ArrayList<>();
        lines.removeFirst();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 8) {
                System.err.println("Error en la línea: " + line);
                continue;
            }
            fixedLines.add(line);
        }
        return fixedLines;
    }

    private Conductor parseLine(String line) throws NumberFormatException {
        String[] parts = line.split(",");
        String DNI = parts[0];
        String nombre = parts[1];
        String apellido1 = parts[2];
        String apellido2 = parts[3];
        String tipoCarnet = parts[4];
        String fechaAprobacion = parts[5];
        int puntos = Integer.parseInt(parts[6]);
        
       
        String fechaRenovacion = parts[7];

        return new Conductor(DNI, nombre, apellido1, apellido2, tipoCarnet, fechaAprobacion, puntos, fechaRenovacion);
    }

}
