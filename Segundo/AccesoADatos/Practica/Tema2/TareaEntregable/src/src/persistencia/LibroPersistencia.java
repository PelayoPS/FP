package src.persistencia;

import src.excepciones.LibroNoEncontradoException;
import src.excepciones.PersistenciaException;
import src.modelo.Libro;

import java.util.List;
import java.util.Optional;

public class LibroPersistencia {
    private static final String FILE_PATH = "libros.dat";
    private Persistencia persistencia;

    public LibroPersistencia() {
        persistencia = new Persistencia();
    }

    /**
     * Guarda un libro en el archivo de persistencia.
     *
     * @param libro el libro a guardar
     * @throws PersistenciaException si ocurre un error al guardar el libro
     */
    public void guardarLibro(Libro libro) throws PersistenciaException {
        try {
            persistencia.guardarObjeto(libro, FILE_PATH);
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el libro", e);
        }
    }

    /**
     * Lista todos los libros almacenados en el archivo de persistencia.
     *
     * @return una lista de libros
     * @throws PersistenciaException si ocurre un error al listar los libros
     */
    public List<Libro> listarLibros() throws PersistenciaException {
        try {
            return persistencia.listarObjetos(FILE_PATH);
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los libros", e);
        }
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id el ID del libro
     * @return un Optional que contiene el libro si se encuentra, o vacío si no
     * @throws PersistenciaException si ocurre un error al obtener el libro por ID
     */
    public Libro obtenerLibroPorId(int id) throws PersistenciaException {
        try {
            return listarLibros().stream().filter(l -> l.getId() == id).findFirst().get();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el libro por ID", e);
        }
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id el ID del libro a eliminar
     * @return true si el libro fue eliminado, false en caso contrario
     * @throws LibroNoEncontradoException si el libro no se encuentra
     */
    public boolean eliminarLibro(int id) throws LibroNoEncontradoException, PersistenciaException {
        try {
            List<Libro> libros = listarLibros();
            boolean removed = libros.removeIf(libro -> libro.getId() == id);
            if (!removed) {
                throw new LibroNoEncontradoException("Libro no encontrado con ID: " + id);
            }
            persistencia.guardarObjetosEnArchivo(libros, FILE_PATH);
            return removed;
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al eliminar el libro", e);
        }
    }
}