package src;

public class Persona implements Humano {

    private String nombre;
    private String apellidos;
    private String dni;
    private Direccion direccion;

    public Persona(String nombre, String apellidos, String dni, Direccion direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getApellidos() {
        return apellidos;
    }

    @Override
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String getDni() {
        return dni;
    }

    @Override
    public void setDni(String dni) {
        this.dni = dni;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public void identificarse() {
        System.out.println("Hola, soy " + nombre + " " + apellidos);
    }

}

