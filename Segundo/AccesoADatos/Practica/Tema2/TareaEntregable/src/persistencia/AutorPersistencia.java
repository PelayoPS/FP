package persistencia;

import modelo.Autor;

import java.util.List;
import java.util.Optional;

public class AutorPersistencia {
    private static final String FILE_PATH = "autores.dat";
    private Persistencia persistencia;

    public AutorPersistencia() {
        persistencia = new Persistencia();
    }

    public void guardarAutor(Autor autor) {
        persistencia.guardarObjeto(autor, FILE_PATH);
    }

    public List<Autor> listarAutores() {
        return persistencia.listarObjetos(FILE_PATH);
    }

    public Optional<Autor> obtenerAutorPorId(int id) {
        return listarAutores().stream().filter(a -> a.getId() == id).findFirst();
    }

    public boolean eliminarAutor(int id) {
        List<Autor> autores = listarAutores();
        boolean removed = autores.removeIf(autor -> autor.getId() == id);
        if (removed) {
            persistencia.guardarObjetosEnArchivo(autores, FILE_PATH);
        }
        return removed;
    }
}