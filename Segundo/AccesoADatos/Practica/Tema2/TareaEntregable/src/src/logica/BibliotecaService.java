package src.logica;

import java.util.List;
import src.excepciones.AutorNoEncontradoException;
import src.excepciones.LibroNoEncontradoException;
import src.excepciones.PersistenciaException;
import src.excepciones.PrestamoNoEncontradoException;
import src.modelo.Autor;
import src.modelo.Libro;
import src.modelo.Prestamo;
import src.persistencia.Persistencia;

/**
 * Servicio de biblioteca que gestiona autores, libros y préstamos.
 */
public class BibliotecaService {
    private AutorService autorService;
    private LibroService libroService;
    private PrestamoService prestamoService;
    private Persistencia persistencia;


    /**
     * Constructor de BibliotecaService.
     * Inicializa los servicios de autor, libro, préstamo y persistencia.
     */
    public BibliotecaService() {
        autorService = new AutorService();
        libroService = new LibroService();
        prestamoService = new PrestamoService();
        persistencia = new Persistencia();
    }

    /**
     * Agrega un nuevo autor al sistema.
     *
     * @param id              el ID del autor
     * @param nombre          el nombre del autor
     * @param fechaNacimiento la fecha de nacimiento del autor
     * @param nacionalidad    la nacionalidad del autor
     * @return el autor agregado
     * @throws PersistenciaException si ocurre un error al guardar el autor
     */
    public Autor agregarAutor(int id, String nombre, String fechaNacimiento, String nacionalidad)
            throws PersistenciaException {
        return autorService.agregarAutor(id, nombre, fechaNacimiento, nacionalidad);
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
        return libroService.agregarLibro(id, titulo, genero, anio, autor);
    }

    /**
     * Elimina un autor del sistema por su ID.
     *
     * @param id el ID del autor a eliminar
     * @return true si el autor fue eliminado, false en caso contrario
     * @throws AutorNoEncontradoException si el autor no se encuentra
     * @throws PersistenciaException      si ocurre un error al eliminar el autor
     */
    public boolean eliminarAutor(int id) throws AutorNoEncontradoException, PersistenciaException {
        return autorService.eliminarAutor(id);
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
        return libroService.eliminarLibro(id);
    }

    /**
     * Busca un autor por su ID.
     *
     * @param id el ID del autor
     * @return el autor encontrado
     * @throws AutorNoEncontradoException si el autor no se encuentra
     * @throws PersistenciaException      si ocurre un error al buscar el autor
     */
    public Autor buscarAutorPorId(int id) throws AutorNoEncontradoException, PersistenciaException {
        return autorService.buscarAutorPorId(id);
    }

    /**
     * Busca un libro por su ID.
     *
     * @param id el ID del libro
     * @return el libro encontrado
     * @throws PersistenciaException      si ocurre un error al buscar el libro
     * @throws LibroNoEncontradoException si el libro no se encuentra
     */
    public Libro buscarLibroPorId(int id) throws PersistenciaException, LibroNoEncontradoException {
        return libroService.buscarLibroPorId(id);
    }

    /**
     * Lista todos los préstamos registrados en el sistema.
     *
     * @return una lista de préstamos
     * @throws PersistenciaException si ocurre un error al listar los préstamos
     */
    public List<Prestamo> listarPrestamos() throws PersistenciaException {
        return prestamoService.listarPrestamos();
    }

    /**
     * Registra un nuevo préstamo en el sistema.
     *
     * @param libro el libro a prestar
     * @return el préstamo registrado
     * @throws PersistenciaException si ocurre un error al registrar el préstamo
     */
    public Prestamo registrarPrestamo(Libro libro) throws PersistenciaException {
        return prestamoService.registrarPrestamo(libro);
    }

    /**
     * Devuelve un libro prestado al sistema.
     *
     * @param idPrestamo el ID del préstamo a devolver
     * @return true si el libro fue devuelto, false en caso contrario
     * @throws PersistenciaException         si ocurre un error al devolver el libro
     * @throws PrestamoNoEncontradoException si el préstamo no se encuentra
     */
    public boolean devolverLibro(int idPrestamo) throws PersistenciaException, PrestamoNoEncontradoException {
        return prestamoService.devolverLibro(idPrestamo);
    }

    /**
     * Lista todos los autores registrados en el sistema.
     *
     * @return una lista de autores
     * @throws PersistenciaException si ocurre un error al listar los autores
     */
    public List<Autor> listarAutores() throws PersistenciaException {
        return autorService.listarAutores();
    }

    /**
     * Lista todos los libros registrados en el sistema.
     *
     * @return una lista de libros
     * @throws PersistenciaException      si ocurre un error al listar los libros
     * @throws LibroNoEncontradoException si el libro no se encuentra
     */
    public List<Libro> listarLibros() throws PersistenciaException, LibroNoEncontradoException {
        return libroService.listarLibros();
    }

    /**
     * Exporta toda la información de la base de datos a un archivo CSV.
     *
     * @param filePath la ruta del archivo CSV
     * @throws PersistenciaException si ocurre un error al exportar los datos
     */
    public void exportarDatosCSV(String filePath) throws PersistenciaException {
        persistencia.exportarDatosCSV(filePath);
    }

    /**
     * Importa todos los registros almacenados en un archivo CSV a la base de datos.
     *
     * @param filePath la ruta del archivo CSV
     * @throws PersistenciaException si ocurre un error al importar los datos
     */
    public void importarDatosCSV(String filePath) throws PersistenciaException {
        persistencia.importarDatosCSV(filePath);
    }

    

}
