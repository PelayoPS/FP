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
        assertDoesNotThrow(() -> libroPersistencia.guardarLibro(libro));
    }

    @Test
    public void testListarLibros() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        try {
            libroPersistencia.guardarLibro(libro);
            List<Libro> libros = libroPersistencia.listarLibros();
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
            libroPersistencia.guardarLibro(libro);
            Libro libroObtenido = libroPersistencia.obtenerLibroPorId(1);
            assertTrue(libroObtenido != null);
            assertEquals(1, libroObtenido != null ? libroObtenido.getId() : 0);
        } catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarLibro() {
        Autor autor = new Autor(1, "Autor Nombre", "01/01/1970", "Nacionalidad");
        Libro libro = new Libro(1, "Titulo", "Genero", 2021, autor);
        try {
            libroPersistencia.guardarLibro(libro);
            boolean eliminado = libroPersistencia.eliminarLibro(1);
            assertTrue(eliminado);
            List<Libro> libros = libroPersistencia.listarLibros();
            assertTrue(libros.isEmpty());
        } catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        }
    }
}