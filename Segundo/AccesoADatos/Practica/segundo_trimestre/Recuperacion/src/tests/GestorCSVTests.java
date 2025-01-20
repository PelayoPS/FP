package tests;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelo.Empleado;
import persistencia.GestorCSV;

/**
 * Clase de pruebas unitarias para la clase GestorCSV.
 */
public class GestorCSVTests {

    private GestorCSV gestorCSV;
    private String rutaArchivo;

    /**
     * Configura el entorno de prueba antes de cada test.
     * 
     * @throws IOException Si ocurre un error al crear el archivo de prueba.
     */
    @BeforeAll
    public void setUp() throws IOException {
        gestorCSV = new GestorCSV();
        rutaArchivo = "test_empleados.csv";
        crearArchivoDePrueba();
    }

    /**
     * Crea un archivo de prueba con datos de empleados.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    private void crearArchivoDePrueba() throws IOException {
        List<String> lineas = new ArrayList<>();
        lineas.add("1,Juan,Perez,M,PE,30000");
        lineas.add("2,Ana,Gomez,F,CA,28000");
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            for (String linea : lineas) {
                writer.write(linea + "\n");
            }
        }
    }

    /**
     * Prueba la lectura de empleados desde un archivo CSV.
     */
    @Test
    public void testLeerEmpleados() {
        List<Empleado> empleados = gestorCSV.leerEmpleados(rutaArchivo);
        assertEquals(2, empleados.size());
        assertEquals("Juan", empleados.get(0).getNombre());
        assertEquals("Ana", empleados.get(1).getNombre());
    }

    /**
     * Prueba la escritura de empleados en un archivo CSV.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    @Test
    public void testEscribirEmpleados() throws IOException {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(3, "Luis", "Martinez", 'M', "PE", 32000));
        gestorCSV.escribirEmpleados(empleados, rutaArchivo);

        List<Empleado> empleadosLeidos = gestorCSV.leerEmpleados(rutaArchivo);
        assertEquals(1, empleadosLeidos.size());
        assertEquals("Luis", empleadosLeidos.get(0).getNombre());
    }

    /**
     * Prueba la verificación de un archivo CSV.
     * 
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    @Test
    public void testVerificarArchivo() throws IOException {
        // Crear un archivo con líneas inválidas
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write("1,Juan,Perez,M,PE,30000\n");
            writer.write("2,Ana,Gomez,F,CA\n"); // Línea inválida
        }

        gestorCSV.verificarArchivo(rutaArchivo);

        List<Empleado> empleados = gestorCSV.leerEmpleados(rutaArchivo);
        assertEquals(1, empleados.size());
        assertEquals("Juan", empleados.get(0).getNombre());
    }

    /**
     * Limpia el entorno de prueba después de cada test.
     */
    @AfterAll
    public void tearDown() {
        File file = new File(rutaArchivo);
        if (file.exists()) {
            file.delete();
        }
    }
}
