/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Persistencia.Controladores.PiezasJpaController;
import Persistencia.Controladores.ProveedoresJpaController;
import Persistencia.Controladores.SuministraJpaController;
import Persistencia.Modelo.Piezas;
import Persistencia.Modelo.Proveedores;
import Persistencia.Modelo.Suministra;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;

/**
 * Clase que gestiona la lógica de negocio de la tienda.
 * Proporciona métodos para realizar operaciones sobre piezas, proveedores y
 * suministros.
 * Utiliza JPA para la persistencia de datos mediante controladores específicos
 * para cada entidad.
 *
 * @author PelayoPS
 */
public class Tienda {
    private PiezasJpaController piezasController;
    private ProveedoresJpaController proveedoresController;
    private SuministraJpaController suministraController;

    /**
     * Constructor de la clase Tienda.
     * Inicializa los controladores JPA necesarios para la gestión de entidades.
     *
     * @param emf EntityManagerFactory necesario para la creación de los
     *            controladores
     */
    public Tienda(EntityManagerFactory emf) {
        this.piezasController = new PiezasJpaController(emf);
        this.proveedoresController = new ProveedoresJpaController(emf);
        this.suministraController = new SuministraJpaController(emf);
    }

    /**
     * Obtiene todas las piezas almacenadas en la base de datos.
     *
     * @return Lista de todas las piezas disponibles
     */
    public List<Piezas> obtenerTodasLasPiezas() {
        return piezasController.findPiezasEntities();
    }

    /**
     * Lista todos los proveedores ordenados por nombre de forma descendente.
     *
     * @return Lista de proveedores ordenada por nombre en orden descendente
     */
    public List<Proveedores> listarProveedoresOrdenadosPorNombreDesc() {
        List<Proveedores> proveedores = proveedoresController.findProveedoresEntities();
        proveedores.sort((a, b) -> b.getNombre().compareTo(a.getNombre())); // Orden descendente
        return proveedores;
    }

    /**
     * Cuenta el número de proveedores que suministran cada pieza.
     * Devuelve un mapa donde la clave es el código de la pieza y el valor
     * es el número de proveedores que la suministran.
     *
     * @return Lista de entradas con el código de pieza y su número de proveedores
     */
    public List<Map.Entry<Integer, Integer>> contarProveedoresPorPieza() {
        List<Suministra> suministros = suministraController.findSuministraEntities();
        Map<Integer, Integer> contadorProveedores = new HashMap<>();

        suministros.stream().filter(suministro -> suministro.getCodigoPieza() != null).forEachOrdered(suministro -> {
            contadorProveedores.put(suministro.getCodigoPieza(),
                    contadorProveedores.getOrDefault(suministro.getCodigoPieza(), 0) + 1);
        });
        return new ArrayList<>(contadorProveedores.entrySet());
    }

    /**
     * Cuenta el número de piezas que suministra cada proveedor.
     * Devuelve un mapa donde la clave es el ID del proveedor y el valor
     * es el número de piezas diferentes que suministra.
     *
     * @return Lista de entradas con el ID del proveedor y su número de piezas
     */
    public List<Map.Entry<Integer, Integer>> contarPiezasPorProveedor() {
        List<Suministra> suministros = suministraController.findSuministraEntities();
        Map<Integer, Integer> contadorPiezas = new HashMap<>();

        suministros.stream().filter(suministro -> suministro.getIdProveedor() != null).forEachOrdered(suministro -> {
            contadorPiezas.put(suministro.getIdProveedor(),
                    contadorPiezas.getOrDefault(suministro.getIdProveedor(), 0) + 1);
        });
        return new ArrayList<>(contadorPiezas.entrySet());
    }

    /**
     * Obtiene el precio más alto por cada pieza suministrada.
     * Devuelve un mapa donde la clave es el código de la pieza y el valor
     * es el precio más alto encontrado para esa pieza entre todos los proveedores.
     *
     * @return Lista de entradas con el código de pieza y su precio más alto
     */
    public List<Map.Entry<Integer, Double>> obtenerPrecioMasAltoPorPieza() {
        List<Suministra> suministros = suministraController.findSuministraEntities();
        Map<Integer, Double> precioMaximo = new HashMap<>();

        suministros.stream().filter(suministro -> suministro.getCodigoPieza() != null && suministro.getPrecio() != null)
                .forEachOrdered(suministro -> {
                    precioMaximo.put(suministro.getCodigoPieza(),
                            Math.max(precioMaximo.getOrDefault(suministro.getCodigoPieza(), -1.0),
                                    suministro.getPrecio()));
                });

        return new ArrayList<>(precioMaximo.entrySet());
    }
}
