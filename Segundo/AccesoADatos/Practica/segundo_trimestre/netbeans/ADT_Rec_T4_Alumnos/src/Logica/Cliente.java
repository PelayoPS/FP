/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author Usuario
 */
public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellidos;
    private String ciudad;
    private Integer categoria;

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    

    public Cliente(int idCliente, String nombre, String apellidos, String ciudad, Integer categoria) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellidos=" + apellidos + ", ciudad=" + ciudad + ", categoria=" + categoria + '}';
    }



   
    

}
