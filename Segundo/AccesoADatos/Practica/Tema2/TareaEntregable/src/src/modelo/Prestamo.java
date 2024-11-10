package src.modelo;

import java.io.Serializable;
import java.util.Date;

public class Prestamo implements Serializable {
    private int id;
    private Libro libro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    /**
     * Constructor de la clase Prestamo.
     *
     * @param id el ID del préstamo
     * @param libro el libro prestado
     * @param fechaPrestamo la fecha del préstamo
     */
    public Prestamo(int id, Libro libro, Date fechaPrestamo, Date fechaDevolucion) {
        this.id = id;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Obtiene el ID del préstamo.
     *
     * @return el ID del préstamo
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del préstamo.
     *
     * @param id el nuevo ID del préstamo
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el libro prestado.
     *
     * @return el libro prestado
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * Establece el libro prestado.
     *
     * @param libro el nuevo libro prestado
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la fecha del préstamo.
     *
     * @return la fecha del préstamo
     */
    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Establece la fecha del préstamo.
     *
     * @param fechaPrestamo la nueva fecha del préstamo
     */
    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    /**
     * Obtiene la fecha de devolución del préstamo.
     *
     * @return la fecha de devolución del préstamo
     */
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Establece la fecha de devolución del préstamo.
     *
     * @param fechaDevolucion la nueva fecha de devolución del préstamo
     */
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Devuelve una representación en cadena del préstamo.
     *
     * @return una cadena que representa el préstamo
     */
    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", libro=" + libro +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                '}';
    }
}
