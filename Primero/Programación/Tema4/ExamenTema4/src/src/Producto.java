package src;

public class Producto {
    private int cod;
    private String descripcion;
    private int existencias;
    private double precio;

    public Producto(int cod, String descripcion, int existencias, double precio) {
        this.cod = cod;
        this.descripcion = descripcion;
        this.existencias = existencias;
        this.precio = precio;
    }

    public double calcularPrecioFinal(int cantidad) {
        return this.precio * cantidad;
    }

    // Getters y setters para los atributos
    public int getCod() {
        return cod;
    }

    // toString
    @Override
    public String toString() {
        return "Producto [cod=" + cod + ", descripcion=" + descripcion + ", existencias=" + existencias + ", precio="
                + precio + "]";
    }

    public void descontarExistencias(int cantidad) {
        this.existencias -= cantidad;
    }

    public int getExistencias() {
        return existencias;
    }


}