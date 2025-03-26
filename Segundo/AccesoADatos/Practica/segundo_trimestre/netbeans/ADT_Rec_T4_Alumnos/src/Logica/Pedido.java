
package Logica;

/**
 *
 * @author Usuario
 */
public class Pedido {
    private int id;
    private int idCliente;
    private int idComercial;
    private double total;
    private String fecha;

    public Pedido(int id, int idCliente, int idComercial, double total, String fecha) {
        this.id=id;
        this.idCliente = idCliente;
        this.idComercial = idComercial;
        this.total = total;
        this.fecha = fecha;
    }
    
    public Pedido(int idCliente, int idComercial, double total, String fecha) {
     
        this.idCliente = idCliente;
        this.idComercial = idComercial;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdComercial() {
        return idComercial;
    }

    public double getTotal() {
        return total;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", idCliente=" + idCliente + ", idComercial=" + idComercial + ", total=" + total + ", fecha=" + fecha + '}';
    }
    
    
    
}
