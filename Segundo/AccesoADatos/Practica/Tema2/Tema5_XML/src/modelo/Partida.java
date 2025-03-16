package modelo;

public class Partida {
    private String equipo;
    private String oponente;
    private String resultado;

    public Partida() {
    }

    public Partida(String equipo, String oponente, String resultado) {
        this.equipo = equipo;
        this.oponente = oponente;
        this.resultado = resultado;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getOponente() {
        return oponente;
    }

    public void setOponente(String oponente) {
        this.oponente = oponente;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Partida [equipo=" + equipo + ", oponente=" + oponente + ", resultado=" + resultado + "]";
    }
}