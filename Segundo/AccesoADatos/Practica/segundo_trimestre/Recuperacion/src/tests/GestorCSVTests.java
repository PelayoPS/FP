package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logica.Empleado;
import persistencia.GestorCSV;

public class GestorCSVTests {

    private GestorCSV gestorCSV;
    private String rutaArchivo;

    @Before
    public void setUp() throws IOException {
        gestorCSV = new GestorCSV();
        rutaArchivo = "test_empleados.csv";
        crearArchivoDePrueba();
    }

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

    @Test
    public void testLeerEmpleados() {
        List<Empleado> empleados = gestorCSV.leerEmpleados(rutaArchivo);
        assertEquals(2, empleados.size());
        assertEquals("Juan", empleados.get(0).getNombre());
        assertEquals("Ana", empleados.get(1).getNombre());
    }

    @Test
    public void testEscribirEmpleados() throws IOException {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(3, "Luis", "Martinez", 'M', "PE", 32000));
        gestorCSV.escribirEmpleados(empleados, rutaArchivo);

        List<Empleado> empleadosLeidos = gestorCSV.leerEmpleados(rutaArchivo);
        assertEquals(1, empleadosLeidos.size());
        assertEquals("Luis", empleadosLeidos.get(0).getNombre());
    }

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

    @After
    public void tearDown() {
        File file = new File(rutaArchivo);
        if (file.exists()) {
            file.delete();
        }
    }
}
