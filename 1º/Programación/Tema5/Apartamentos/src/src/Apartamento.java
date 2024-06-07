package src;

public class Apartamento {

    private Referencia referencia;
    private int capacidadMax;
    private double precio;
    private int contadorDeReservas;

    /**
     * Constructor de la clase
     * 
     * @param int    : piso del apartamento
     * @param char   : letra del apartamento
     * @param int    : capacidad máxima del apartamento
     * @param double : precio del apartamento
     */
    public Apartamento(int piso, char letra, int capacidadMax, double precio) {
        this.referencia = new Referencia(piso, letra);
        this.capacidadMax = capacidadMax;
        this.precio = precio;
        this.contadorDeReservas = 0;
    }

    /**
     * Devuelve la referencia del apartamento
     * 
     * @return Referencia : referencia del apartamento
     */
    public Referencia getReferencia() {
        return referencia;
    }

    /**
     * Devuelve la capacidad máxima del apartamento
     * 
     * @return int : capacidad máxima del apartamento
     */
    public int getCapacidadMax() {
        return capacidadMax;
    }

    /**
     * Devuelve el precio del apartamento
     * 
     * @return double : precio del apartamento
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve el contador de reservas del apartamento
     * 
     * @return int : contador de reservas del apartamento
     */
    public int getContadorDeReservas() {
        return contadorDeReservas;
    }

    /**
     * Incrementa el contador de reservas
     */
    public void incrementarContadorDeReservas() {
        contadorDeReservas++;
    }

    /**
     * toString
     * formato: Apartamento [ref=PisoX-LetraY, capacidadMax=X, precio=x,
     * contadorRerserva=X]
     */
    @Override
    public String toString() {
        return "Apartamento [ref=" + referencia + ", capacidadMax=" + capacidadMax + ", precio=" + precio
                + ", contadorRerserva=" + contadorDeReservas + "]";
    }

    /**
     * equals mediante la referencia por hashCode
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Apartamento other = (Apartamento) obj;
        if (referencia == null) {
            if (other.referencia != null)
                return false;
        } else if (!referencia.equals(other.referencia))
            return false;
        return true;
    }


}
