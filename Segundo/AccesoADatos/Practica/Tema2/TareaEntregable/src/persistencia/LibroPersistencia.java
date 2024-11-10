package persistencia;

import modelo.Libro;

import java.util.List;
import java.util.Optional;

public class LibroPersistencia {
    private static final String FILE_PATH = "libros.dat";
    private Persistencia persistencia;

    public LibroPersistencia() {
        persistencia = new Persistencia();
    }

    public void guardarLibro(Libro libro) {
        persistencia.guardarObjeto(libro, FILE_PATH);
    }

    public List<Libro> listarLibros() {
        return persistencia.listarObjetos(FILE_PATH);
    }

    public Optional<Libro> obtenerLibroPorId(int id) {
        return listarLibros().stream().filter(l -> l.getId() == id).findFirst();
    }

    public boolean eliminarLibro(int id) {
        List<Libro> libros = listarLibros();
        boolean removed = libros.removeIf(libro -> libro.getId() == id);
        if (removed) {
            persistencia.guardarObjetosEnArchivo(libros, FILE_PATH);
        }
        return removed;
    }
}