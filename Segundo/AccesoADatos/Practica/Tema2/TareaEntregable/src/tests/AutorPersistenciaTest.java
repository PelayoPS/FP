package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.excepciones.PersistenciaException;
import src.modelo.Autor;
import src.persistencia.AutorPersistencia;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AutorPersistenciaTest {
    private AutorPersistencia autorPersistencia;

    @BeforeEach
    public void setUp() {
        autorPersistencia = new AutorPersistencia();
    }

    @Test
    public void testGuardarAutor() {
        Autor autor = new Autor(1, "Nombre", "01/01/1970", "Nacionalidad");
        assertDoesNotThrow(() -> autorPersistencia.guardarAutor(autor));
    }

    @Test
    public void testListarAutores() {
        Autor autor = new Autor(1, "Nombre", "01/01/1970", "Nacionalidad");
        try {
            autorPersistencia.guardarAutor(autor);
            List<Autor> autores = autorPersistencia.listarAutores();
            assertNotNull(autores);
            assertFalse(autores.isEmpty());
            assertEquals(1, autores.get(0).getId());
        } catch (PersistenciaException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testObtenerAutorPorId() {
        Autor autor = new Autor(1, "Nombre", "01/01/1970", "Nacionalidad");
        try {
            autorPersistencia.guardarAutor(autor);
            Autor autorObtenido = autorPersistencia.obtenerAutorPorId(1);
            assertTrue(autorObtenido != null);
            assertEquals(1, autorObtenido != null ? autorObtenido.getId() : 0);
        } catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarAutor() {
        Autor autor = new Autor(1, "Nombre", "01/01/1970", "Nacionalidad");
        try {
            autorPersistencia.guardarAutor(autor);
            boolean eliminado = autorPersistencia.eliminarAutor(1);
            assertTrue(eliminado);
            List<Autor> autores = autorPersistencia.listarAutores();
            assertTrue(autores.isEmpty());
        } catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }
}