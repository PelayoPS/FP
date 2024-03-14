package src;

public class ProductoEnOferta extends Producto {
    private int cantidadMinima;
    private int porcentaje;

    public ProductoEnOferta(int cod, String descripcion, int existencias, double precio, int cantidadMinima, int porcentaje) {
        super(cod, descripcion, existencias, precio);
        this.cantidadMinima = cantidadMinima;
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcularPrecioFinal(int cantidad) {
        double precioFinal = super.calcularPrecioFinal(cantidad);
        if (cantidad >= cantidadMinima) {
            precioFinal -= precioFinal * porcentaje / 100.0;
        }
        return precioFinal;
    }

    @Override
    public String toString() {
        String str = super.toString() + " [cantidadMinima=" + cantidadMinima + ", porcentaje=" + porcentaje + "]";
        str = str.replace("Producto", "ProductoEnOferta");
        return str;
    }

}