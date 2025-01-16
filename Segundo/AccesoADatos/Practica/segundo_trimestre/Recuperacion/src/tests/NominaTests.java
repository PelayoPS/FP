package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.Empleado;
import logica.Nominas;

/**
 * Clase de pruebas unitarias para la clase Nominas.
 */
public class NominaTests {

    private Nominas nominas;
    private Empleado empleado1;
    private Empleado empleado2;

    /**
     * Configura el entorno de prueba antes de cada test.
     */
    @BeforeAll
    public void setUp() {
        List<Empleado> empleados = new ArrayList<>();
        empleado1 = new Empleado(1, "Juan", "Perez", 'M', "PE", 30000);
        empleado2 = new Empleado(2, "Ana", "Gomez", 'F', "CA", 28000);
        empleados.add(empleado1);
        empleados.add(empleado2);
        nominas = new Nominas(empleados);
    }

    /**
     * Prueba el alta de un nuevo empleado.
     */
    @Test
    public void testAltaEmpleado() {
        Empleado nuevoEmpleado = new Empleado(3, "Luis", "Martinez", 'M', "PE", 32000);
        nominas.altaEmpleado(nuevoEmpleado);
        assertEquals(nuevoEmpleado, nominas.buscarEmpleado(3));
    }

    /**
     * Prueba la búsqueda de un empleado por su ID.
     */
    @Test
    public void testBuscarEmpleado() {
        Empleado encontrado = nominas.buscarEmpleado(1);
        assertEquals(empleado1, encontrado);
    }

    /**
     * Prueba la eliminación de un empleado.
     */
    @Test
    public void testBorrarEmpleado() {
        nominas.borrarEmpleado(1);
        assertNull(nominas.buscarEmpleado(1));
    }

    /**
     * Prueba la lista de empleados.
     */
    @Test
    public void testListarEmpleados() {
        String listaEsperada = empleado1.toString() + "\n" + empleado2.toString() + "\n";
        assertEquals(listaEsperada, nominas.listarEmpleados());
    }

    /**
     * Prueba el cálculo del salario mensual de un empleado.
     */
    @Test
    public void testCalcularSalarioMensual() {
        double salarioMensualPE = nominas.calcularSalarioMensual(1);
        assertEquals(2500, salarioMensualPE, 0.01);

        double salarioMensualCA = nominas.calcularSalarioMensual(2);
        assertEquals(2000, salarioMensualCA, 0.01);
    }

    /**
     * Prueba el cálculo del salario mensual para un empleado no existente.
     */
    @Test
    public void testCalcularSalarioMensualEmpleadoNoExistente() {
        double salarioMensual = nominas.calcularSalarioMensual(99);
        assertEquals(-1, salarioMensual, 0.01);
    }
}
