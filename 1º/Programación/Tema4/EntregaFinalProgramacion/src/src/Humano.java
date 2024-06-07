package src;

public interface Humano {


    String getNombre();

    void setNombre(String nombre);

    String getApellidos();

    void setApellidos(String apellidos);

    String getDni();

    void setDni(String dni);

    void setDireccion(Direccion direccion);

    Direccion getDireccion();

}
