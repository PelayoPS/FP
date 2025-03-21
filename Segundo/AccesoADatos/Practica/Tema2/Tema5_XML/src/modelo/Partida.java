package modelo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa una partida en el juego League of Legends.
 * Contiene información sobre el equipo, oponente, resultado, fecha,
 * duración, estadísticas y puntuación.
 * 
 * @author PelayoPS
 * @version 1.0
 */
public class Partida {
    private String equipo;
    private String oponente;
    private String resultado;
    private Date fecha;
    private int duracionMinutos;
    private Map<String, Integer> estadisticas; // Mapa de estadísticas (kills, deaths, etc.)
    private int puntuacionEquipo;
    private int puntuacionOponente;

    /**
     * Constructor por defecto que inicializa una partida con valores por defecto.
     * La fecha es la fecha actual, la duración es 0 minutos, y las estadísticas
     * son un mapa vacío.
     */
    public Partida() {
        this.fecha = new Date();
        this.duracionMinutos = 0;
        this.estadisticas = new HashMap<>();
        this.puntuacionEquipo = 0;
        this.puntuacionOponente = 0;
    }

    /**
     * Constructor que inicializa una partida con el equipo, oponente y resultado
     * especificados.
     * La fecha es la fecha actual, la duración es 0 minutos, y las estadísticas
     * son un mapa vacío.
     *
     * @param equipo    Nombre del equipo
     * @param oponente  Nombre del oponente
     * @param resultado Resultado de la partida (Ganado, Perdido, Empate)
     */
    public Partida(String equipo, String oponente, String resultado) {
        this();
        this.equipo = equipo;
        this.oponente = oponente;
        this.resultado = resultado;
    }

    /**
     * Constructor que inicializa una partida con el equipo, oponente, resultado y
     * duración especificados.
     * La fecha es la fecha actual, y las estadísticas son un mapa vacío.
     *
     * @param equipo          Nombre del equipo
     * @param oponente        Nombre del oponente
     * @param resultado       Resultado de la partida (Ganado, Perdido, Empate)
     * @param duracionMinutos Duración de la partida en minutos
     */
    public Partida(String equipo, String oponente, String resultado, int duracionMinutos) {
        this(equipo, oponente, resultado);
        this.duracionMinutos = duracionMinutos;
    }

    /**
     * Getter para el nombre del equipo.
     * 
     * @return Nombre del equipo
     */
    public String getEquipo() {
        return equipo;
    }

    /**
     * Setter para el nombre del equipo.
     * 
     * @param equipo Nombre del equipo
     * @throws IllegalArgumentException Si el nombre del equipo es nulo o vacío
     */
    public void setEquipo(String equipo) throws IllegalArgumentException {
        if (equipo == null || equipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El equipo no puede estar vacío");
        }
        this.equipo = equipo;
    }

    /**
     * Getter para el nombre del oponente.
     * 
     * @return Nombre del oponente
     */
    public String getOponente() {
        return oponente;
    }

    /**
     * Setter para el nombre del oponente.
     * 
     * @param oponente Nombre del oponente
     * @throws IllegalArgumentException Si el nombre del oponente es nulo o vacío
     */
    public void setOponente(String oponente) throws IllegalArgumentException {
        if (oponente == null || oponente.trim().isEmpty()) {
            throw new IllegalArgumentException("El oponente no puede estar vacío");
        }
        this.oponente = oponente;
    }

    /**
     * Getter para el resultado de la partida.
     * 
     * @return Resultado de la partida (Ganado, Perdido, Empate)
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Setter para el resultado de la partida.
     * 
     * @param resultado Resultado de la partida (Ganado, Perdido, Empate)
     * @throws IllegalArgumentException Si el resultado es nulo o no es válido
     */
    public void setResultado(String resultado) throws IllegalArgumentException {
        if (resultado == null || resultado.trim().isEmpty()) {
            throw new IllegalArgumentException("El resultado no puede estar vacío");
        }
        if (!resultado.equals("Ganado") && !resultado.equals("Perdido") && !resultado.equals("Empate")) {
            throw new IllegalArgumentException("El resultado debe ser 'Ganado', 'Perdido' o 'Empate'");
        }
        this.resultado = resultado;
    }

    /**
     * Getter para la fecha de la partida.
     * 
     * @return Fecha de la partida
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Setter para la fecha de la partida.
     * 
     * @param fecha Fecha de la partida
     * @throws IllegalArgumentException Si la fecha es nula
     */
    public void setFecha(Date fecha) throws IllegalArgumentException {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula");
        }
        this.fecha = fecha;
    }

    /**
     * Getter para la duración de la partida en minutos.
     * 
     * @return Duración de la partida en minutos
     */
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    /**
     * Setter para la duración de la partida en minutos.
     * 
     * @param duracionMinutos Duración de la partida en minutos
     * @throws IllegalArgumentException Si la duración es negativa
     */
    public void setDuracionMinutos(int duracionMinutos) throws IllegalArgumentException {
        if (duracionMinutos < 0) {
            throw new IllegalArgumentException("La duración no puede ser negativa");
        }
        this.duracionMinutos = duracionMinutos;
    }

    /**
     * Getter para las estadísticas de la partida.
     * 
     * @return Mapa de estadísticas (kills, deaths, etc.)
     */
    public Map<String, Integer> getEstadisticas() {
        return estadisticas;
    }

    /**
     * Setter para las estadísticas de la partida.
     * 
     * @param estadisticas Mapa de estadísticas (kills, deaths, etc.)
     * @throws IllegalArgumentException Si el mapa de estadísticas es nulo
     */
    public void setEstadisticas(Map<String, Integer> estadisticas) throws IllegalArgumentException {
        if (estadisticas == null) {
            throw new IllegalArgumentException("Las estadísticas no pueden ser nulas");
        }
        this.estadisticas = estadisticas;
    }

    /**
     * Método para agregar una estadística a la partida.
     * 
     * @param clave Clave de la estadística (ej. "kills", "deaths")
     * @param valor Valor de la estadística (ej. 5, 3)
     * @throws IllegalArgumentException Si la clave es nula o vacía
     */
    public void agregarEstadistica(String clave, int valor) throws IllegalArgumentException {
        if (clave == null || clave.trim().isEmpty()) {
            throw new IllegalArgumentException("La clave de estadística no puede estar vacía");
        }
        this.estadisticas.put(clave, valor);
    }

    /**
     * Getter para la puntuación del equipo.
     * 
     * @return Puntuación del equipo
     */
    public int getPuntuacionEquipo() {
        return puntuacionEquipo;
    }

    /**
     * Setter para la puntuación del equipo.
     * 
     * @param puntuacionEquipo Puntuación del equipo
     * @throws IllegalArgumentException Si la puntuación es negativa
     */
    public void setPuntuacionEquipo(int puntuacionEquipo) throws IllegalArgumentException {
        if (puntuacionEquipo < 0) {
            throw new IllegalArgumentException("La puntuación no puede ser negativa");
        }
        this.puntuacionEquipo = puntuacionEquipo;
    }

    /**
     * Getter para la puntuación del oponente.
     * 
     * @return Puntuación del oponente
     */
    public int getPuntuacionOponente() {
        return puntuacionOponente;
    }

    /**
     * Setter para la puntuación del oponente.
     * 
     * @param puntuacionOponente Puntuación del oponente
     * @throws IllegalArgumentException Si la puntuación es negativa
     */
    public void setPuntuacionOponente(int puntuacionOponente) throws IllegalArgumentException {
        if (puntuacionOponente < 0) {
            throw new IllegalArgumentException("La puntuación no puede ser negativa");
        }
        this.puntuacionOponente = puntuacionOponente;
    }

    /**
     * toString() para representar la partida como una cadena.
     * 
     * @return Cadena que representa la partida
     */
    @Override
    public String toString() {
        return "Partida [equipo=" + equipo + ", oponente=" + oponente + ", resultado=" + resultado + ", fecha=" + fecha
                + ", duración=" + duracionMinutos + "min, puntuación=" + puntuacionEquipo + "-" + puntuacionOponente
                + "]";
    }
}