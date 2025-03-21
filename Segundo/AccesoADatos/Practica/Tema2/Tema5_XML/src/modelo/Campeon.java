package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase que representa un campeón en el juego League of Legends.
 * Contiene información sobre el nombre, rol, dificultad, poder, fecha de
 * lanzamiento,
 * habilidades y disponibilidad del campeón.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class Campeon {
    private String nombre;
    private String rol;
    private int dificultad; // 1-10
    private double poder; // Estadística de poder
    private Date fechaLanzamiento;
    private List<String> habilidades;
    private boolean disponible;
    private static final Set<String> ROLES_VALIDOS = new HashSet<>(
            Arrays.asList("Top", "Jungle", "Mid", "ADC", "Support"));

    /**
     * Constructor por defecto que inicializa un campeón con valores aleatorios.
     * La dificultad es aleatoria entre 1 y 10, el poder entre 0 y 100,
     * y la fecha de lanzamiento es la fecha actual.
     */
    public Campeon() {
        this.dificultad = Math.random() < 0.5 ? 1 : 10; // Dificultad aleatoria entre 1 y 10
        this.poder = Math.round(Math.random() * 10000.0) / 100.0; // Poder aleatorio entre 0 y 100 con 2 decimales
        this.fechaLanzamiento = new Date();
        this.habilidades = new ArrayList<>();
        this.disponible = true;
    }

    /**
     * Constructor que inicializa un campeón con el nombre y rol especificados.
     * La dificultad, poder y fecha de lanzamiento se inicializan con valores por
     * defecto.
     *
     * @param nombre Nombre del campeón
     * @param rol    Rol del campeón
     */
    public Campeon(String nombre, String rol) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (rol == null || !ROLES_VALIDOS.contains(rol)) {
            throw new IllegalArgumentException("Rol inválido. Debe ser uno de: " + String.join(", ", ROLES_VALIDOS));
        }
        this.nombre = nombre;
        this.rol = rol;
        this.dificultad = Math.random() < 0.5 ? 1 : 10; // Dificultad aleatoria entre 1 y 10
        this.poder = Math.round(Math.random() * 10000.0) / 100.0; // Poder aleatorio entre 0 y 100 con 2 decimales
        this.fechaLanzamiento = new Date();
        this.habilidades = new ArrayList<>();
        this.disponible = true;
    }

    /**
     * Constructor que inicializa un campeón con el nombre, rol, dificultad y poder
     * especificados.
     *
     * @param nombre     Nombre del campeón
     * @param rol        Rol del campeón
     * @param dificultad Dificultad del campeón (1-10)
     * @param poder      Poder del campeón (0-100)
     */
    public Campeon(String nombre, String rol, int dificultad, double poder) {
        this(nombre, rol);
        this.dificultad = dificultad;
        this.poder = poder;
    }

    /**
     * Getter para el nombre del campeón.
     * 
     * @return Nombre del campeón
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter para el nombre del campeón.
     * 
     * @param nombre Nombre del campeón
     * @throws IllegalArgumentException Si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    /**
     * Getter para el rol del campeón.
     * 
     * @return Rol del campeón
     */
    public String getRol() {
        return rol;
    }

    /**
     * Setter para el rol del campeón.
     * 
     * @param rol Rol del campeón
     * @throws IllegalArgumentException Si el rol es nulo o vacío
     */
    public void setRol(String rol) throws IllegalArgumentException {
        if (rol == null || !ROLES_VALIDOS.contains(rol)) {
            throw new IllegalArgumentException("Rol inválido. Debe ser uno de: " + String.join(", ", ROLES_VALIDOS));
        }
        this.rol = rol;
    }

    /**
     * Getter para la dificultad del campeón.
     * 
     * @return Dificultad del campeón (1-10)
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * Setter para la dificultad del campeón.
     * 
     * @param dificultad Dificultad del campeón (1-10)
     * @throws IllegalArgumentException Si la dificultad no está en el rango 1-10
     */
    public void setDificultad(int dificultad) throws IllegalArgumentException {
        if (dificultad < 1 || dificultad > 10) {
            throw new IllegalArgumentException("La dificultad debe estar entre 1 y 10");
        }
        this.dificultad = dificultad;
    }

    /**
     * Getter para el poder del campeón.
     * 
     * @return Poder del campeón (0-100)
     */
    public double getPoder() {
        return poder;
    }

    /**
     * Setter para el poder del campeón.
     * 
     * @param poder Poder del campeón (0-100)
     * @throws IllegalArgumentException Si el poder no está en el rango 0-100
     */
    public void setPoder(double poder) throws IllegalArgumentException {
        if (poder < 0 || poder > 100) {
            throw new IllegalArgumentException("El poder debe estar entre 0 y 100");
        }
        this.poder = poder;
    }

    /**
     * Getter para la fecha de lanzamiento del campeón.
     * 
     * @return Fecha de lanzamiento del campeón
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    /**
     * Setter para la fecha de lanzamiento del campeón.
     * 
     * @param fechaLanzamiento Fecha de lanzamiento del campeón
     * @throws IllegalArgumentException Si la fecha de lanzamiento es nula
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) throws IllegalArgumentException {
        if (fechaLanzamiento == null) {
            throw new IllegalArgumentException("La fecha de lanzamiento no puede ser nula");
        }
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Getter para la lista de habilidades del campeón.
     * 
     * @return Lista de habilidades del campeón
     */
    public List<String> getHabilidades() {
        return habilidades;
    }

    /**
     * Setter para la lista de habilidades del campeón.
     * 
     * @param habilidades Lista de habilidades del campeón
     * @throws IllegalArgumentException Si la lista de habilidades es nula
     */
    public void setHabilidades(List<String> habilidades) throws IllegalArgumentException {
        if (habilidades == null) {
            throw new IllegalArgumentException("La lista de habilidades no puede ser nula");
        }
        this.habilidades = habilidades;
    }

    /**
     * Método para agregar una habilidad a la lista de habilidades del campeón.
     * 
     * @param habilidad Habilidad a agregar
     * @throws IllegalArgumentException Si la habilidad es nula o vacía
     */
    public void agregarHabilidad(String habilidad) throws IllegalArgumentException {
        if (habilidad == null || habilidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La habilidad no puede estar vacía");
        }
        this.habilidades.add(habilidad);
    }

    /**
     * Método para eliminar una habilidad de la lista de habilidades del campeón.
     * 
     * @param habilidad Habilidad a eliminar
     * @throws IllegalArgumentException Si la habilidad es nula o vacía
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Setter para la disponibilidad del campeón.
     * 
     * @param disponible true si el campeón está disponible, false en caso contrario
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * ToString para representar el campeón en forma de cadena.
     * 
     * @return Cadena que representa el campeón
     */
    @Override
    public String toString() {
        return "Campeon [nombre=" + nombre + ", rol=" + rol + ", dificultad=" + dificultad + ", poder=" + poder
                + ", habilidades=" + habilidades.size() + ", disponible=" + disponible + "]";
    }
}
