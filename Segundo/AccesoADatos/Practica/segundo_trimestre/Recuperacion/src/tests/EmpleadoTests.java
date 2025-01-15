package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import logica.Empleado;

public class EmpleadoTests {

    @Test
    public void testConstructor() {
        Empleado empleado = new Empleado(1, "Juan", "Perez", 'M', "PE", 30000);
        assertEquals(1, empleado.getId());
        assertEquals("Juan", empleado.getNombre());
        assertEquals("Perez", empleado.getApellidos());
        assertEquals('M', empleado.getGenero());
        assertEquals("PE", empleado.getCategoriaProfesional());
        assertEquals(30000, empleado.getSalarioAnual(), 0.01);
    }

    @Test
    public void testSerializar() {
        Empleado empleado = new Empleado(1, "Juan", "Perez", 'M', "PE", 30000);
        String serializado = empleado.serializar();
        assertEquals("1,Juan,Perez,M,PE,30000.0", serializado);
    }

    @Test
    public void testDeserializar() {
        String datos = "1,Juan,Perez,M,PE,30000.0";
        Empleado empleado = Empleado.deserializar(datos);
        assertEquals(1, empleado.getId());
        assertEquals("Juan", empleado.getNombre());
        assertEquals("Perez", empleado.getApellidos());
        assertEquals('M', empleado.getGenero());
        assertEquals("PE", empleado.getCategoriaProfesional());
        assertEquals(30000, empleado.getSalarioAnual(), 0.01);
    }

    @Test
    public void testToString() {
        Empleado empleado = new Empleado(1, "Juan", "Perez", 'M', "PE", 30000);
        String esperado = "Empleado{id=1, nombre=Juan, apellidos=Perez, genero=M, categoriaProfesional=PE, salarioAnual=30000.0}";
        assertEquals(esperado, empleado.toString());
    }


}
