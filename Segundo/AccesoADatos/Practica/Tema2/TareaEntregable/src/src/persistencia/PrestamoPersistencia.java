package src.persistencia;

import src.excepciones.PersistenciaException;
import src.excepciones.PrestamoNoEncontradoException;
import src.modelo.Prestamo;

import java.util.List;

public class PrestamoPersistencia {
    private static final String FILE_PATH = "prestamos.dat";
    private Persistencia persistencia;

    public PrestamoPersistencia() {
        persistencia = new Persistencia();
    }

    /**
     * Guarda un préstamo en el archivo de persistencia.
     *
     * @param prestamo el préstamo a guardar
     * @throws PersistenciaException si ocurre un error al guardar el préstamo
     */
    public void guardarPrestamo(Prestamo prestamo) throws PersistenciaException {
        try {
            persistencia.guardarObjeto(prestamo, FILE_PATH);
        } catch (Exception e) {
            throw new PersistenciaException("Error al guardar el préstamo", e);
        }
    }

    /**
     * Lista todos los préstamos almacenados en el archivo de persistencia.
     *
     * @return una lista de préstamos
     * @throws PersistenciaException si ocurre un error al listar los préstamos
     */
    public List<Prestamo> listarPrestamos() throws PersistenciaException {
        try {
            return persistencia.listarObjetos(FILE_PATH);
        } catch (Exception e) {
            throw new PersistenciaException("Error al listar los préstamos", e);
        }
    }

    /**
     * Obtiene un préstamo por su ID.
     *
     * @param id el ID del préstamo
     * @return un Optional que contiene el préstamo si se encuentra, o vacío si no
     * @throws PersistenciaException si ocurre un error al obtener el préstamo por ID
     */
    public Prestamo obtenerPrestamoPorId(int id) throws PersistenciaException {
        try {
            return listarPrestamos().stream().filter(p -> p.getId() == id).findFirst().get();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el préstamo por ID", e);
        }
    }

    /**
     * Actualiza un préstamo en el archivo de persistencia.
     *
     * @param prestamo el préstamo a actualizar
     * @return true si el préstamo fue actualizado, false en caso contrario
     * @throws PersistenciaException si ocurre un error al actualizar el préstamo
     * @throws PrestamoNoEncontradoException si el préstamo no se encuentra
     */
    public boolean actualizarPrestamo(Prestamo prestamo) throws PersistenciaException {
        try {
            List<Prestamo> prestamos = listarPrestamos();
            for (int i = 0; i < prestamos.size(); i++) {
                if (prestamos.get(i).getId() == prestamo.getId()) {
                    prestamos.set(i, prestamo);
                    persistencia.guardarObjetosEnArchivo(prestamos, FILE_PATH);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new PersistenciaException("Error al actualizar el préstamo", e);
        }
    }
}