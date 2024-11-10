package logica;

import modelo.Autor;
import persistencia.AutorPersistencia;

import java.util.List;
import java.util.Optional;

public class AutorService {
    private AutorPersistencia persistencia;

    public AutorService() {
        persistencia = new AutorPersistencia();
    }

    public Autor agregarAutor(int id, String nombre, String fechaNacimiento, String nacionalidad) {
        Autor autor = new Autor(id, nombre, fechaNacimiento, nacionalidad);
        persistencia.guardarAutor(autor);
        return autor;
    }

    public Optional<Autor> buscarAutorPorId(int id) {
        return persistencia.obtenerAutorPorId(id);
    }

    public List<Autor> listarAutores() {
        return persistencia.listarAutores();
    }

    public boolean eliminarAutor(int id) {
        return persistencia.eliminarAutor(id);
    }
}
