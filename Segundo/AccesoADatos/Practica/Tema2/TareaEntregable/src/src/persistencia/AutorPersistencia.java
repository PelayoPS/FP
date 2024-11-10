package src.persistencia;

import src.excepciones.AutorNoEncontradoException;
import src.excepciones.PersistenciaException;
import src.modelo.Autor;

import java.util.List;

public class AutorPersistencia {
    private static final String FILE_PATH = "autores.dat";
    private Persistencia persistencia;

    public AutorPersistencia() {
        persistencia = new Persistencia();
    }

    /**
     * Guarda un autor en el archivo de persistencia.
     *
     * @param autor el autor a guardar
     * @throws PersistenciaException si ocurre un error al guardar el autor
     */
    public void guardarAutor(Autor autor) throws PersistenciaException {
        try {
            persistencia.guardarObjeto(autor, FILE_PATH);
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el autor", e);
        }
    }

    /**
     * Lista todos los autores almacenados en el archivo de persistencia.
     *
     * @return una lista de autores
     * @throws PersistenciaException si ocurre un error al listar los autores
     */
    public List<Autor> listarAutores() throws PersistenciaException {
        try {
            return persistencia.listarObjetos(FILE_PATH);
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los autores", e);
        }
    }

    /**
     * Obtiene un autor por su ID.
     *
     * @param id el ID del autor
     * @return el autor si se encuentra, o lanza una excepción si no
     * @throws AutorNoEncontradoException si el autor no se encuentra
     */
    public Autor obtenerAutorPorId(int id) throws AutorNoEncontradoException, PersistenciaException {
        try {
            return listarAutores().stream().filter(a -> a.getId() == id).findFirst()
                    .orElseThrow(() -> new AutorNoEncontradoException("Autor no encontrado con ID: " + id));
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al obtener el autor por ID", e);
        }
    }

    /**
     * Elimina un autor por su ID.
     *
     * @param id el ID del autor a eliminar
     * @return true si el autor fue eliminado, false en caso contrario
     * @throws AutorNoEncontradoException si el autor no se encuentra
     * @throws PersistenciaException si ocurre un error al eliminar el autor
     */
    public boolean eliminarAutor(int id) throws AutorNoEncontradoException, PersistenciaException {
        try {
            List<Autor> autores = listarAutores();
            boolean removed = autores.removeIf(autor -> autor.getId() == id);
            if (!removed) {
                throw new AutorNoEncontradoException("Autor no encontrado con ID: " + id);
            }
            persistencia.guardarObjetosEnArchivo(autores, FILE_PATH);
            return removed;
        } catch (PersistenciaException e) {
            throw new PersistenciaException("Error al eliminar el autor", e);
        }
    }
}