package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.excepciones.PersistenciaException;
import src.modelo.Autor;
import src.modelo.Libro;
import src.modelo.Prestamo;
import src.persistencia.PrestamoPersistencia;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoPersistenciaTest {
    private PrestamoPersistencia prestamoPersistencia;

    @BeforeEach
    public void setUp() {
        prestamoPersistencia = new PrestamoPersistencia();
    }

    @Test
    public void testGuardarPrestamo() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        Prestamo prestamo = new Prestamo(1, libro, new Date(), null);
        assertDoesNotThrow(() -> prestamoPersistencia.guardar(prestamo));
    }

    @Test
    public void testListarPrestamos() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        Prestamo prestamo = new Prestamo(1, libro, new Date(), null);
        try {
            prestamoPersistencia.guardar(prestamo);
            List<Prestamo> prestamos = prestamoPersistencia.listar();
            assertNotNull(prestamos);
            assertFalse(prestamos.isEmpty());
            assertEquals(1, prestamos.get(0).getId());
        } catch (PersistenciaException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testObtenerPrestamoPorId() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        Prestamo prestamo = new Prestamo(1, libro, new Date(), null);
        try {
            prestamoPersistencia.guardar(prestamo);
            Prestamo prestamoObtenido = prestamoPersistencia.obtenerPorId(1);
            assertNotNull(prestamoObtenido);
            assertEquals(1, prestamoObtenido.getId());
        } catch (PersistenciaException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testActualizarPrestamo() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        Prestamo prestamo = new Prestamo(1, libro, new Date(), null);
        try {
            prestamoPersistencia.guardar(prestamo);
            Prestamo prestamoObtenido = prestamoPersistencia.obtenerPorId(1);
            assertNotNull(prestamoObtenido);
            prestamoObtenido.setFechaDevolucion(new Date());
            prestamoPersistencia.actualizar(prestamoObtenido);
            Prestamo prestamoActualizado = prestamoPersistencia.obtenerPorId(1);
            assertNotNull(prestamoActualizado);
            assertNotNull(prestamoActualizado.getFechaDevolucion());
        } catch (PersistenciaException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }
}