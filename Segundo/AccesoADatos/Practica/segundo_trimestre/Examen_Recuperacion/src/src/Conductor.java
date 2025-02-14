package src;

public class Conductor implements Comparable<Conductor> {

    /**
     * Campos:
     * - DNI : String
     * - nombre : String
     * - apellido1 : String
     * - apellido2 : String
     * - tipo de carnet : String
     * - fecha de aprobacion : string dd/mm/aaaa
     * - puntos : int
     * - fecha de renovacion : string dd/mm/aaaa
     */

    private String DNI;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String tipoCarnet;
    private String fechaAprobacion;
    private int puntos;
    private String fechaRenovacion;


    /**
     * Constructor de la clase Conductor
     * 
     * @param DNI             : String
     * @param nombre          : String
     * @param apellido1       : String
     * @param apellido2       : String
     * @param tipoCarnet      : String
     * @param fechaAprobacion : String
     * @param puntos          : int
     * @param fechaRenovacion : String
     */
    public Conductor(String DNI, String nombre, String apellido1, String apellido2, String tipoCarnet,
            String fechaAprobacion, int puntos, String fechaRenovacion) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.tipoCarnet = tipoCarnet;
        this.fechaAprobacion = fechaAprobacion;
        this.puntos = puntos;
        this.fechaRenovacion = fechaRenovacion;
    }

    // getters
    public String getDNI() {
        return DNI;
    }

    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoCarnet() {
        return tipoCarnet;
    }

    // toString
    @Override
    public String toString() {
        return "Conductor [DNI=" + DNI + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", fechaAprobacion="
                + fechaAprobacion + ", fechaRenovacion=" + fechaRenovacion + ", nombre=" + nombre + ", puntos=" + puntos
                + ", tipoCarnet=" + tipoCarnet + "]";
    }

    @Override
    public int compareTo(Conductor o) {
        // TODO Auto-generated method stub
    	return this.getDNI().equals(o.getDNI()) ? 0 : 1;
    }

}
