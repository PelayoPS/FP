package src;

public class Entrenador extends Persona {

    private String equipo;
    private String titulacion;

    public Entrenador(String nombre, String apellidos, String dni, Direccion direccion, String equipo, String titulacion) {
        super(nombre, apellidos, dni, direccion);
        this.equipo = equipo;
        this.titulacion = titulacion;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getTitulacion() {
        return titulacion;
    }

    public void setTitulacion(String titulacion) {
        this.titulacion = titulacion;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + getNombre() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", equipo='" + equipo + '\'' +
                ", titulacion='" + titulacion + '\'' +
                '}';
    }
}

