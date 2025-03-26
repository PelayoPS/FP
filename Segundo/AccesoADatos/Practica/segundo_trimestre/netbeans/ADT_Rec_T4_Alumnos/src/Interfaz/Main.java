
package Interfaz;

import Logica.Cliente;
import Logica.Comercial;
import Logica.Pedido;
import Logica.Ventas;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Main {
       public static void main(String[] args) {
           System.out.println("Prueba comercial \n**************************");
           pruebaComercialJpaController();
           System.out.println("\n\nPrueba cliente \n**************************");
           pruebaClienteJpaController();
           System.out.println("\n\nPrueba Pedido \n**************************");
           pruebaPedidoJpaController();
       }

    private static void pruebaComercialJpaController() {
        Ventas ventas=new Ventas();
        List<Object> lista=ventas.getComerciales();
        for(Object c:lista){
            System.out.println(c.toString());
                    
        }
        Comercial nuevo=new Comercial(100,"Monica","Alonso González ",0.150);
        ventas.nuevoComercial(nuevo);
        Comercial buscar=ventas.busarComercial(100);
        if(buscar==null){
            System.out.println("ERROR comercial Monica no encontrado ");
        }else{
            System.out.println("********************************************");
            System.out.println(buscar.toString());
            buscar.setComision(0.80);
            ventas.actualizarComercial(buscar);
            buscar=ventas.busarComercial(100);
            System.out.println("********************************************");
            System.out.println("Despues de actualizar comision ");
            System.out.println(buscar.toString());
            ventas.borrarComercial(100);
        }
    }

    private static void pruebaClienteJpaController() {
       Ventas ventas=new Ventas();
        List<Object> lista=ventas.getClientes();
        for(Object c:lista){
            System.out.println(c.toString());
                    
        }
        Cliente nuevo=new Cliente(100,"Monica","Alonso González ","Gijon",100);
        ventas.nuevoCliente(nuevo);
        Cliente buscar=ventas.buscarCliente(100);
        if(buscar==null){
            System.out.println("ERROR cliente Monica no encontrado ");
        }else{
            System.out.println("********************************************");
            System.out.println(buscar.toString());
            buscar.setCategoria(200);
            ventas.actualizarCliente(buscar);
            buscar=ventas.buscarCliente(100);
            System.out.println("********************************************");
            System.out.println("Despues de actualizar comision ");
            System.out.println(buscar.toString());
            ventas.borrarCliente(100);
        }
    }

    private static void pruebaPedidoJpaController() {
       Ventas ventas=new Ventas();
       List<Object> lista=ventas.getPedidos();
       for(Object p:lista){
           System.out.println(p.toString());
       }
       Pedido nuevo=new Pedido(1, 1, 100.0,"2025-03-20");
       ventas.nuevoPedido(nuevo);
       System.out.println("**************************************************");
       System.out.println("Despues de insertar el nuevo pedido ");
       lista=ventas.getPedidos();
       for(Object p:lista){
           System.out.println(p.toString());
       }
    }
       
       
}
