package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.excepciones.PersistenciaException;
import src.modelo.Autor;
import src.modelo.Libro;
import src.persistencia.LibroPersistencia;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibroPersistenciaTest {
    private LibroPersistencia libroPersistencia;

    @BeforeEach
    public void setUp() {
        libroPersistencia = new LibroPersistencia();
    }

    @Test
    public void testGuardarLibro() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        assertDoesNotThrow(() -> libroPersistencia.guardar(libro));
    }

    @Test
    public void testListarLibros() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        try {
            libroPersistencia.guardar(libro);
            List<Libro> libros = libroPersistencia.listar();
            assertNotNull(libros);
            assertFalse(libros.isEmpty());
            assertEquals(1, libros.get(0).getId());
        } catch (PersistenciaException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testObtenerLibroPorId() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        try {
            libroPersistencia.guardar(libro);
            Libro libroObtenido = libroPersistencia.obtenerPorId(1);
            assertNotNull(libroObtenido);
            assertEquals(1, libroObtenido.getId());
        } catch (PersistenciaException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarLibro() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        try {
            libroPersistencia.guardar(libro);
            boolean eliminado = libroPersistencia.eliminar(1);
            assertTrue(eliminado);
            List<Libro> libros = libroPersistencia.listar();
            assertTrue(libros.isEmpty());
        } catch (PersistenciaException e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }
}