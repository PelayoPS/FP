package logica;

import java.util.Date;
import java.util.List;

import modelo.Libro;
import modelo.Prestamo;
import persistencia.PrestamoPersistencia;

public class PrestamoService {
    private PrestamoPersistencia persistencia;
    private int id = 1;

    public PrestamoService() {
        persistencia = new PrestamoPersistencia();
    }

    public Prestamo registrarPrestamo(Libro libro) {
        id = persistencia.listarPrestamos().size() + 1;
        Prestamo prestamo = new Prestamo(id, libro, new Date(), null);
        persistencia.guardarPrestamo(prestamo);
        return prestamo;
    }

    public boolean devolverLibro(int idPrestamo) {
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

    public List<Prestamo> listarPrestamos() {
        return persistencia.listarPrestamos();
    }
}
