package src;

import java.util.Objects;

public class Referencia {
    private int piso;
    private char letra;
    private boolean correcta;

    /**
     * Constructor de la clase
     * 
     * @param int  : piso del apartamento
     * @param char : letra del apartamento
     */
    public Referencia(int piso, char letra) {
        correcta = true;
        verificacionPiso(piso);
        verificacionLetra(letra);
        this.piso = piso;
        this.letra = letra;
    }

    /**
     * Devuelve el piso del apartamento
     * 
     * @return int : piso del apartamento
     */
    public int getPiso() {
        return piso;
    }

    /**
     * Devuelve la letra del apartamento
     * 
     * @return char : letra del apartamento
     */
    public char getLetra() {
        return letra;
    }

    /**
     * Devuelve si la referencia es correcta
     * 
     * @return boolean : true si es correcta, false en caso contrario
     */
    public boolean isCorrecta() {
        return correcta;
    }

    /**
     * Devuelve la referencia del apartamento en formato PisoX-LetraX
     */
    @Override
    public String toString() {
        return "Piso" + piso + "-Letra" + letra;
    }

    /**
     * Verifica si el piso es correcto
     * 
     * @param int : piso del apartamento
     */
    private void verificacionPiso(int piso) {
        if (piso < 1 || piso > 5) {
            correcta = false;
        }
    }

    /**
     * Verifica si la letra es correcta
     * 
     * @param char : letra del apartamento
     */
    private void verificacionLetra(char letra) {
        if (letra < 'A' || letra > 'F') {
            correcta = false;
        }
    }

    /**
     * hashCode Objects.hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(letra, piso);
        
    }

    /**
     * equals comparando hash
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Referencia other = (Referencia) obj;
        return other.hashCode() == this.hashCode();
    }

}
