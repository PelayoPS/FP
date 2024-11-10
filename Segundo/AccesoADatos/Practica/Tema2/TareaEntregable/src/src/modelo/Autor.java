package src.modelo;

import java.io.Serializable;

public class Autor implements Serializable {
    private int id;
    private String nombre;
    private String fechaNacimiento;
    private String nacionalidad;

    /**
     * Constructor de la clase Autor.
     *
     * @param id el ID del autor
     * @param nombre el nombre del autor
     * @param fechaNacimiento la fecha de nacimiento del autor
     * @param nacionalidad la nacionalidad del autor
     */
    public Autor(int id, String nombre, String fechaNacimiento, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
    }

    /**
     * Obtiene el ID del autor.
     *
     * @return el ID del autor
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del autor.
     *
     * @param id el nuevo ID del autor
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del autor.
     *
     * @return el nombre del autor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del autor.
     *
     * @param nombre el nuevo nombre del autor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la fecha de nacimiento del autor.
     *
     * @return la fecha de nacimiento del autor
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del autor.
     *
     * @param fechaNacimiento la nueva fecha de nacimiento del autor
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la nacionalidad del autor.
     *
     * @return la nacionalidad del autor
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del autor.
     *
     * @param nacionalidad la nueva nacionalidad del autor
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Devuelve una representación en cadena del autor.
     *
     * @return una cadena que representa al autor
     */
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
