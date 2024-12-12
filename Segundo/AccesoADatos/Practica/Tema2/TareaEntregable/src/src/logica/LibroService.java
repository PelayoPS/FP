package src.logica;

import java.util.List;

import src.excepciones.LibroNoEncontradoException;
import src.excepciones.PersistenciaException;
import src.modelo.Autor;
import src.modelo.Libro;
import src.persistencia.LibroPersistencia;

public class LibroService {
    private LibroPersistencia persistencia;

    public LibroService() {
        persistencia = new LibroPersistencia();
    }

    /**
     * Agrega un nuevo libro al sistema.
     *
     * @param id     el ID del libro
     * @param titulo el título del libro
     * @param genero el género del libro
     * @param anio   el año de publicación del libro
     * @param autor  el autor del libro
     * @return el libro agregado
     * @throws PersistenciaException si ocurre un error al guardar el libro
     */
    public Libro agregarLibro(int id, String titulo, String genero, int anio, Autor autor)
            throws PersistenciaException {
        Libro libro = new Libro(id, titulo, genero, anio, autor);
        persistencia.guardar(libro);
        return libro;
    }

    /**
     * Busca un libro por su ID.
     *
     * @param id el ID del libro
     * @return un Optional que contiene el libro si se encuentra, o vacío si no
     * @throws PersistenciaException      si ocurre un error al obtener el libro por
     *                                    ID
     * @throws LibroNoEncontradoException si el libro no se encuentra
     */
    public Libro buscarLibroPorId(int id) throws PersistenciaException, LibroNoEncontradoException {
        return persistencia.obtenerPorId(id);
    }

    /**
     * Lista todos los libros registrados en el sistema.
     *
     * @return una lista de libros
     * @throws PersistenciaException      si ocurre un error al listar los libros
     * @throws LibroNoEncontradoException si el libro no se encuentra
     */
    public List<Libro> listarLibros() throws PersistenciaException, LibroNoEncontradoException {
        return persistencia.listar();
    }

    /**
     * Elimina un libro del sistema por su ID.
     *
     * @param id el ID del libro a eliminar
     * @return true si el libro fue eliminado, false en caso contrario
     * @throws LibroNoEncontradoException si el libro no se encuentra
     * @throws PersistenciaException      si ocurre un error al eliminar el libro
     */
    public boolean eliminarLibro(int id) throws LibroNoEncontradoException, PersistenciaException {
        return persistencia.eliminar(id);
    }
}
