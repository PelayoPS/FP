package src.modelo;

import java.io.Serializable;

public class Libro implements Serializable {
    private int id;
    private String titulo;
    private String genero;
    private int anio;
    private Autor autor;

    /**
     * Constructor de la clase Libro.
     *
     * @param id el ID del libro
     * @param titulo el título del libro
     * @param genero el género del libro
     * @param anio el año de publicación del libro
     * @param autor el autor del libro
     */
    public Libro(int id, String titulo, String genero, int anio, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anio = anio;
        this.autor = autor;
    }

    /**
     * Obtiene el ID del libro.
     *
     * @return el ID del libro
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del libro.
     *
     * @param id el nuevo ID del libro
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return el título del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param titulo el nuevo título del libro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el género del libro.
     *
     * @return el género del libro
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del libro.
     *
     * @param genero el nuevo género del libro
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene el año de publicación del libro.
     *
     * @return el año de publicación del libro
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Establece el año de publicación del libro.
     *
     * @param anio el nuevo año de publicación del libro
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return el autor del libro
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param autor el nuevo autor del libro
     */
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    /**
     * Devuelve una representación en cadena del libro.
     *
     * @return una cadena que representa el libro
     */
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", anio=" + anio +
                ", autor=" + autor +
                '}';
    }

}
