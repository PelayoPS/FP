/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Comercial {
 
    private int idComercial;
    private String nombre;
    private String apellidos;
    private double comision;

    public int getIdComercial() {
        return idComercial;
    }

    
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    
    public Comercial(int idComercial, String nombre, String apellidos, double comision) {
        this.idComercial = idComercial;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.comision = comision;
    }

    @Override
    public String toString() {
        String formato = String.format("%.3f", comision);
        
        return "Comercial{" + "idComercial=" + idComercial + ", nombre=" + nombre + ", apellidos=" + apellidos + ", comision=" + formato + '}';
    }


    
    
}
