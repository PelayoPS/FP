package src;

public class Venta {
    private static int CONT_VENTAS = 0;
    private int idVentas;
    private String nombreVendedor;
    private DetalleVenta[] detalles;
    private int numDetalles;
    private boolean abonada;
    private double dineroFinal;
    private double abonado;

    /**
     * Constructor de la clase Venta
     * 
     * @param String : nombre del vendedor
     */
    public Venta(String nombreVendedor) {
        this.idVentas = ++CONT_VENTAS;
        this.nombreVendedor = nombreVendedor;
        this.detalles = new DetalleVenta[10]; // Tamaño inicial del array
        this.numDetalles = 0;
    }

    /**
     * Añade un detalle a la venta
     * Si el array está lleno, se duplica su tamaño
     * 
     * @param DetalleVenta : detalle a añadir
     */
    public void añadirDetalle(DetalleVenta detalle) {
        if (numDetalles < detalles.length) {
            this.detalles[numDetalles] = detalle;
            numDetalles++;
        } else {
            DetalleVenta[] temp = new DetalleVenta[detalles.length * 2];
            for (int i = 0; i < detalles.length; i++) {
                temp[i] = detalles[i];
            }
            this.detalles = temp;
        }
    }

    /**
     * Calcula el importe total de la venta
     * 
     * @return double : importe total de la venta
     */
    public double calcularImporteTotal() {
        double importeTotal = 0;
        for (int i = 0; i < numDetalles; i++) {
            importeTotal += detalles[i].getProducto().calcularPrecioFinal(detalles[i].getCantidad());
        }
        return importeTotal;
    }

    public void abonar(double importeAbonado) {
        if (importeAbonado >= calcularImporteTotal()) {
            abonada = true;
            for (int i = 0; i < numDetalles; i++) {
                detalles[i].getProducto().descontarExistencias(detalles[i].getCantidad());
            }
            this.abonado = importeAbonado;
            this.dineroFinal = importeAbonado - calcularImporteTotal();
        }
    }

    /**
     * @return the idVentas
     */
    public int getIdVentas() {
        return idVentas;
    }

    @Override
    public String toString() {
        String str = "Venta [idVentas=" + idVentas + ", nombreVendedor=" + nombreVendedor + ", abonada=" + abonada + "]\n";
        for (int i = 0; i < numDetalles; i++) {
            str += detalles[i].toString() + "\n";
        }
        str += "Importe total: " + calcularImporteTotal() + "\n";
        str += "Importe recibido: " + (abonada ? this.abonado : "0.0") + "\n";
        str += "Devolución: " + (abonada ? this.dineroFinal : "0.0") + "\n";
        return str;
    }
}
