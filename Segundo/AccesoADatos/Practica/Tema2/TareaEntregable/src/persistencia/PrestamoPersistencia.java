package persistencia;

import modelo.Prestamo;

import java.util.List;
import java.util.Optional;

public class PrestamoPersistencia {
    private static final String FILE_PATH = "prestamos.dat";
    private Persistencia persistencia;

    public PrestamoPersistencia() {
        persistencia = new Persistencia();
    }

    public void guardarPrestamo(Prestamo prestamo) {
        persistencia.guardarObjeto(prestamo, FILE_PATH);
    }

    public List<Prestamo> listarPrestamos() {
        return persistencia.listarObjetos(FILE_PATH);
    }

    public Optional<Prestamo> obtenerPrestamoPorId(int id) {
        return listarPrestamos().stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean actualizarPrestamo(Prestamo prestamo) {
        List<Prestamo> prestamos = listarPrestamos();
        boolean found = false;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getId() == prestamo.getId()) {
                prestamos.set(i, prestamo);
                found = true;
                break;
            }
        }
        if (found) {
            persistencia.guardarObjetosEnArchivo(prestamos, FILE_PATH);
        }
        return found;
    }
}