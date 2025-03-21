package modelo;

import java.util.Date;

/**
 * Clase que representa un jugador en el juego League of Legends.
 * Contiene información sobre el nombre, rol, nivel, experiencia,
 * fecha de registro y estado del jugador.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class Jugador {
    private String nombre;
    private String rol;
    private int nivel;
    private double experiencia;
    private Date fechaRegistro;
    private boolean activo;

    /**
     * Constructor por defecto que inicializa un jugador con valores por defecto.
     * El nivel es 1, la experiencia es 0.0, la fecha de registro es la fecha
     * actual,
     * y el estado activo es verdadero.
     */
    public Jugador() {
        this.nivel = 1;
        this.experiencia = 0.0;
        this.fechaRegistro = new Date();
        this.activo = true;
    }

    /**
     * Constructor que inicializa un jugador con el nombre y rol especificados.
     * El nivel es 1, la experiencia es 0.0, la fecha de registro es la fecha
     * actual,
     * y el estado activo es verdadero.
     *
     * @param nombre Nombre del jugador
     * @param rol    Rol del jugador
     */
    public Jugador(String nombre, String rol) {
        this();
        this.nombre = nombre;
        this.rol = rol;
    }

    /**
     * Constructor que inicializa un jugador con el nombre, rol, nivel y experiencia
     * especificados.
     * La fecha de registro es la fecha actual, y el estado activo es verdadero.
     *
     * @param nombre      Nombre del jugador
     * @param rol         Rol del jugador
     * @param nivel       Nivel del jugador (1-100)
     * @param experiencia Experiencia del jugador (0.0 o mayor)
     */
    public Jugador(String nombre, String rol, int nivel, double experiencia) {
        this(nombre, rol);
        this.nivel = nivel;
        this.experiencia = experiencia;
    }

    /**
     * Getter para el nombre del jugador.
     * 
     * @return Nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter para el nombre del jugador.
     * 
     * @param nombre Nombre del jugador
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     * Getter para el rol del jugador.
     * 
     * @return Rol del jugador
     */
    public String getRol() {
        return rol;
    }

    /**
     * Setter para el rol del jugador.
     * 
     * @param rol Rol del jugador
     * @throws IllegalArgumentException si el rol es nulo o vacío
     */
    public void setRol(String rol) throws IllegalArgumentException {
        if (rol == null || rol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío");
        }
        this.rol = rol;
    }

    /**
     * Getter para el nivel del jugador.
     * 
     * @return Nivel del jugador (1-100)
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Setter para el nivel del jugador.
     * 
     * @param nivel Nivel del jugador (1-100)
     * @throws IllegalArgumentException si el nivel no está entre 1 y 100
     */
    public void setNivel(int nivel) throws IllegalArgumentException {
        if (nivel < 1 || nivel > 100) {
            throw new IllegalArgumentException("El nivel debe estar entre 1 y 100");
        }
        this.nivel = nivel;
    }

    /**
     * Getter para la experiencia del jugador.
     * 
     * @return Experiencia del jugador (0.0 o mayor)
     */
    public double getExperiencia() {
        return experiencia;
    }

    /**
     * Setter para la experiencia del jugador.
     * 
     * @param experiencia Experiencia del jugador (0.0 o mayor)
     * @throws IllegalArgumentException si la experiencia es negativa
     */
    public void setExperiencia(double experiencia) throws IllegalArgumentException {
        if (experiencia < 0) {
            throw new IllegalArgumentException("La experiencia no puede ser negativa");
        }
        this.experiencia = experiencia;
    }

    /**
     * Getter para la fecha de registro del jugador.
     * 
     * @return Fecha de registro del jugador
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Setter para la fecha de registro del jugador.
     * 
     * @param fechaRegistro Fecha de registro del jugador
     * @throws IllegalArgumentException si la fecha de registro es nula
     */
    public void setFechaRegistro(Date fechaRegistro) throws IllegalArgumentException {
        if (fechaRegistro == null) {
            throw new IllegalArgumentException("La fecha de registro no puede ser nula");
        }
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Getter para el estado activo del jugador.
     * 
     * @return true si el jugador está activo, false en caso contrario
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Setter para el estado activo del jugador.
     * 
     * @param activo true si el jugador está activo, false en caso contrario
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * ToString para representar al jugador en forma de cadena.
     * 
     * @return Cadena que representa al jugador
     */
    @Override
    public String toString() {
        return "Jugador [nombre=" + nombre + ", rol=" + rol + ", nivel=" + nivel + ", experiencia=" + experiencia
                + ", activo=" + activo + "]";
    }
}