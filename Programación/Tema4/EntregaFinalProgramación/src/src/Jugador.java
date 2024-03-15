package src;

public class Jugador extends Persona {

    private String equipo;
    private int numero;

    public Jugador(String nombre, String apellidos, String dni, Direccion direccion, String equipo, int numero) {
        super(nombre, apellidos, dni, direccion);
        this.equipo = equipo;
        this.numero = numero;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + getNombre() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", dni='" + getDni() + '\'' +
                ", equipo='" + equipo + '\'' +
                ", numero=" + numero +
                '}';
    }
}

