/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Interfaz;

import Logica.Tienda;
import Persistencia.Modelo.Piezas;
import Persistencia.Modelo.Proveedores;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase principal que demuestra las funcionalidades de la aplicación de gestión
 * de tienda.
 * Esta clase contiene el punto de entrada de la aplicación y realiza diferentes
 * consultas
 * sobre piezas y proveedores para mostrar la información almacenada en la base
 * de datos.
 *
 * La clase utiliza JPA (Java Persistence API) para la conexión con la base de
 * datos
 * y muestra los resultados de diferentes operaciones como:
 * - Listado de todas las piezas
 * - Listado de proveedores ordenados por nombre
 * - Conteo de proveedores por pieza
 * - Conteo de piezas por proveedor
 * - Precios máximos por pieza
 *
 * @author PelayoPS
 */
public class Main {

    /**
     * Método principal que ejecuta las diferentes consultas y muestra los
     * resultados por consola.
     * 
     * El método realiza las siguientes operaciones:
     * 1. Inicializa la conexión con la base de datos mediante JPA
     * 2. Crea una instancia de la clase Tienda para gestionar las operaciones
     * 3. Obtiene y muestra todas las piezas disponibles
     * 4. Lista los proveedores ordenados por nombre de forma descendente
     * 5. Muestra el número de proveedores que suministran cada pieza
     * 6. Muestra el número de piezas que suministra cada proveedor
     * 7. Muestra el precio más alto para cada pieza
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tarea_TiendaPU");
        Tienda tienda = new Tienda(emf);

        List<Piezas> piezas = tienda.obtenerTodasLasPiezas();
        System.out.println("\n--- Todas las piezas ---");
        for (Piezas pieza : piezas) {
            System.out.println(pieza.getCodigo() + ": " + pieza.getNombre());
        }

        System.out.println("\n--- Proveedores por nombre en orden descendente ---");
        List<Proveedores> proveedores = tienda.listarProveedoresOrdenadosPorNombreDesc();
        for (Proveedores proveedor : proveedores) {
            System.out.println(proveedor.getId() + ": " + proveedor.getNombre());
        }

        System.out.println("\n--- Proveedores que suministran cada pieza ---");
        for (Map.Entry<Integer, Integer> entry : tienda.contarProveedoresPorPieza()) {
            System.out.println("Pieza " + entry.getKey() + " tiene " + entry.getValue() + " proveedores.");
        }

        System.out.println("\n--- Piezas que suministra cada proveedor ---");
        for (Map.Entry<Integer, Integer> entry : tienda.contarPiezasPorProveedor()) {
            System.out.println("Proveedor " + entry.getKey() + " suministra " + entry.getValue() + " piezas.");
        }

        System.out.println("\n--- Precio mas alto por cada pieza ---");
        for (Map.Entry<Integer, Double> entry : tienda.obtenerPrecioMasAltoPorPieza()) {
            System.out.println("Pieza " + entry.getKey() + " tiene un precio maximo de " + entry.getValue());
        }
    }

}
