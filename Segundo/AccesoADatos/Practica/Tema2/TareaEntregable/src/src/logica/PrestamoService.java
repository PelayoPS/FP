package src.logica;

import java.util.Date;
import java.util.List;

import src.excepciones.PersistenciaException;
import src.excepciones.PrestamoNoEncontradoException;
import src.modelo.Libro;
import src.modelo.Prestamo;
import src.persistencia.PrestamoPersistencia;

public class PrestamoService {
    private PrestamoPersistencia persistencia;
    private int id = 1;

    public PrestamoService() {
        persistencia = new PrestamoPersistencia();
    }

    /**
     * Registra un nuevo préstamo en el sistema.
     *
     * @param libro el libro a prestar
     * @return el préstamo registrado
     * @throws PersistenciaException si ocurre un error al registrar el préstamo
     */
    public Prestamo registrarPrestamo(Libro libro) throws PersistenciaException {
        id = persistencia.listarPrestamos().size() + 1;
        Prestamo prestamo = new Prestamo(id, libro, new Date(), null);
        persistencia.guardarPrestamo(prestamo);
        return prestamo;
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
        if (persistencia.obtenerPrestamoPorId(idPrestamo).get() == null) {
            return false;
        } else {
            Prestamo prestamo = persistencia.obtenerPrestamoPorId(idPrestamo).get();
            if (prestamo != null && prestamo.getFechaDevolucion() == null) {
                prestamo.setFechaDevolucion(new Date());
                persistencia.actualizarPrestamo(prestamo);
                return true;
            }
        }

        return false;
    }

    /**
     * Lista todos los préstamos registrados en el sistema.
     *
     * @return una lista de préstamos
     * @throws PersistenciaException si ocurre un error al listar los préstamos
     */
    public List<Prestamo> listarPrestamos() throws PersistenciaException {
        return persistencia.listarPrestamos();
    }
}
