/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import persistencia.controllers.*;
import persistencia.modelo.*;

/**
 *
 * @author pelay
 */
public class Biblioteca {

    private IDAO<Libros> librosDAO;
    private IDAO<Alumno> alumnoDAO;
    private IDAO<Prestamo> prestamoDAO;

    private EntityManagerFactory emf;

    public Biblioteca() {
        emf = javax.persistence.Persistence.createEntityManagerFactory("examenPU");
        librosDAO = new LibrosJpaController(emf);
        alumnoDAO = new AlumnoJpaController(emf);
        prestamoDAO = new PrestamoJpaController(emf);
    }

    public List<String> listarTodosLosDatos() {
        List<String> datos = new ArrayList<String>();

        List<Libros> libros = librosDAO.listAll();
        List<Alumno> alumnos = alumnoDAO.listAll();
        List<Prestamo> prestamos = prestamoDAO.listAll();

        for (Libros libro : libros) {
            datos.add(libro.toString());
        }

        for (Alumno alumno : alumnos) {
            datos.add(alumno.toString());
        }

        for (Prestamo prestamo : prestamos) {
            datos.add(prestamo.toString());
        }

        return datos;
    }

    public List<String> listarAutores() {
        List<String> autoresConNumLibros = new ArrayList<String>();

        List<Libros> libros = librosDAO.listAll();

        for (Libros libro : libros) {
            String autor = libro.getAutor();
            int numLibros = 0;

            for (Libros libro2 : libros) {
                if (libro2.getAutor().equals(autor)) {
                    numLibros++;
                }
            }

            autoresConNumLibros.add(autor + " - " + numLibros);
        }

        return autoresConNumLibros;

    }

    public String numPrestamosPorExpediente(int numExpediente) {
        int numPrestamos = 0;

        List<Prestamo> prestamos = prestamoDAO.listAll();

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getPrestamoPK().getNExpediente() == numExpediente) {
                numPrestamos++;
            }
        }

        return "El alumno con expediente " + numExpediente + " ha realizado " + numPrestamos + " préstamos.";
    }

    // listar los nombres y apellidos de los alumnos de 4º de ESO ordenados
    // alfabéticamente por apellidos y en caso de coincidencia por nombre
    // descendente
    public List<String> listarAlumnos4ESO() {
        List<String> alumnos4ESO = new ArrayList<String>();

        List<Alumno> alumnos = alumnoDAO.listAll();

        // filtrar alumnos por 4º de ESO
        alumnos.removeIf(a -> !a.getCurso().equals("4-ESO"));

        // ordenar alumnos
        alumnos.sort((a1, a2) -> {
            if (a1.getApellidos().equals(a2.getApellidos())) {
                return a2.getNombre().compareTo(a1.getNombre());
            } else {
                return a1.getApellidos().compareTo(a2.getApellidos());
            }
        });

        // pasar a texto
        for (Alumno alumno : alumnos) {
            alumnos4ESO.add(alumno.getNombre() + " " + alumno.getApellidos());
        }

        return alumnos4ESO;
    }

}
