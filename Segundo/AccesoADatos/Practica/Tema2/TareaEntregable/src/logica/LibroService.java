package logica;

import java.util.List;
import java.util.Optional;

import modelo.Autor;
import modelo.Libro;
import persistencia.LibroPersistencia;

public class LibroService {
    private LibroPersistencia persistencia;

    public LibroService() {
        persistencia = new LibroPersistencia();
    }

    public Libro agregarLibro(int id, String titulo, String genero, int anio, Autor autor) {
        Libro libro = new Libro(id, titulo, genero, anio, autor);
        persistencia.guardarLibro(libro);
        return libro;
    }

    public Optional<Libro> buscarLibroPorId(int id) {
        return persistencia.obtenerLibroPorId(id);
    }

    public List<Libro> listarLibros() {
        return persistencia.listarLibros();
    }

    public boolean eliminarLibro(int id) {
        return persistencia.eliminarLibro(id);
    }
}
