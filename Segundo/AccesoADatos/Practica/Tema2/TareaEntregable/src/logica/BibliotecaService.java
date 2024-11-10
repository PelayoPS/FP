package logica;

import modelo.Autor;
import modelo.Libro;
import modelo.Prestamo;

import java.util.List;
import java.util.Optional;

public class BibliotecaService {
    private AutorService autorService;
    private LibroService libroService;
    private PrestamoService prestamoService;

    public BibliotecaService() {
        autorService = new AutorService();
        libroService = new LibroService();
        prestamoService = new PrestamoService();
    }

    public Autor agregarAutor(int id, String nombre, String fechaNacimiento, String nacionalidad) {
        return autorService.agregarAutor(id, nombre, fechaNacimiento, nacionalidad);
    }

    public Libro agregarLibro(int id, String titulo, String genero, int anio, Autor autor) {
        return libroService.agregarLibro(id, titulo, genero, anio, autor);
    }

    public boolean eliminarAutor(int id) {
        return autorService.eliminarAutor(id);
    }

    public boolean eliminarLibro(int id) {
        return libroService.eliminarLibro(id);
    }

    public Optional<Autor> buscarAutorPorId(int id) {
        return autorService.buscarAutorPorId(id);
    }

    public Optional<Libro> buscarLibroPorId(int id) {
        return libroService.buscarLibroPorId(id);
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoService.listarPrestamos();
    }

    public Prestamo registrarPrestamo(Libro libro) {
        return prestamoService.registrarPrestamo(libro);
    }

    public boolean devolverLibro(int idPrestamo) {
        return prestamoService.devolverLibro(idPrestamo);
    }

    public List<Autor> listarAutores() {
        return autorService.listarAutores();
    }

    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }


}
