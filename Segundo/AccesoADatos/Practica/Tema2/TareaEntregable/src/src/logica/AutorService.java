package src.logica;

import src.excepciones.AutorNoEncontradoException;
import src.excepciones.PersistenciaException;
import src.modelo.Autor;
import src.persistencia.AutorPersistencia;

import java.util.List;

public class AutorService {
    private AutorPersistencia persistencia;

    /**
     * Constructor de la clase AutorService.
     * Inicializa la persistencia de autores.
     */
    public AutorService() {
        persistencia = new AutorPersistencia();
    }

    /**
     * Agrega un nuevo autor al sistema.
     *
     * @param id el ID del autor
     * @param nombre el nombre del autor
     * @param fechaNacimiento la fecha de nacimiento del autor
     * @param nacionalidad la nacionalidad del autor
     * @return el autor agregado
     * @throws PersistenciaException si ocurre un error al guardar el autor
     */
    public Autor agregarAutor(int id, String nombre, String fechaNacimiento, String nacionalidad) throws PersistenciaException {
        Autor autor = new Autor(id, nombre, fechaNacimiento, nacionalidad);
        persistencia.guardar(autor);
        return autor;
    }

    /**
     * Busca un autor por su ID.
     *
     * @param id el ID del autor
     * @return un Optional que contiene el autor si se encuentra, o vacío si no
     * @throws AutorNoEncontradoException si el autor no se encuentra
     */
    public Autor buscarAutorPorId(int id) throws AutorNoEncontradoException, PersistenciaException {
        return persistencia.obtenerPorId(id);
    }

    /**
     * Lista todos los autores registrados en el sistema.
     *
     * @return una lista de autores
     * @throws PersistenciaException si ocurre un error al listar los autores
     */
    public List<Autor> listarAutores() throws PersistenciaException {
        return persistencia.listar();
    }

    /**
     * Elimina un autor del sistema por su ID.
     *
     * @param id el ID del autor a eliminar
     * @return true si el autor fue eliminado, false en caso contrario
     * @throws AutorNoEncontradoException si el autor no se encuentra
     */
    public boolean eliminarAutor(int id) throws AutorNoEncontradoException, PersistenciaException {
        return persistencia.eliminar(id);
    }
}
