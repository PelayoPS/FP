
package Logica;

import Persistencia.Controladores.ClienteJpaController;
import Persistencia.Controladores.ComercialJpaController;
import Persistencia.Controladores.PedidoJpaController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Ventas {
    private List<Comercial> listaCoemrcial;
    private List<Cliente> listaClientes;
    
    public Ventas(){
        listaCoemrcial=new ArrayList<Comercial>();
        listaClientes=new ArrayList<Cliente>();
    }

    public List<Object> getComerciales() {
        ComercialJpaController c=new ComercialJpaController();
        return c.listAll();
    }

    public Comercial busarComercial(int idComercial) {
       ComercialJpaController c=new ComercialJpaController();
       return (Comercial) c.search(idComercial);
    }

    public void actualizarComercial(Comercial act) {
        ComercialJpaController c=new ComercialJpaController();
        boolean resul=c.update(act);
        if(resul==true){
            System.out.println("Actualizacion correcta ");
        }else{
            System.out.println("Error en la actualizacion ");
        }
    }

    public void nuevoComercial(Comercial nuevo) {
        ComercialJpaController c=new ComercialJpaController();
        boolean resul=c.add(nuevo);
        if(resul==true){
            System.out.println("Insertado correctamente correcta ");
        }else{
            System.out.println("Error en la insercion ");
        }
    }

    public void borrarComercial(int id) {
        ComercialJpaController c=new ComercialJpaController();
        //c.remove(id);
    }

    /**********************************************************************/
    public List<Object> getClientes() {
        ClienteJpaController c=new ClienteJpaController();        
        return c.listAll();
    }

    public Cliente buscarCliente(int idCliente) {
        ClienteJpaController c=new ClienteJpaController();    
       return (Cliente) c.search(idCliente);
    }

    public void actualizarCliente(Cliente act) {
         ClienteJpaController c=new ClienteJpaController();    
        boolean resul=c.update(act);
        if(resul==true){
            System.out.println("Actualizacion cliente correcta ");
        }else{
            System.out.println("Error en la actualizacion cliente ");
        }
    }

    public void nuevoCliente(Cliente nuevo) {
         ClienteJpaController c=new ClienteJpaController();    
        boolean resul=c.add(nuevo);
        if(resul==true){
            System.out.println("Insertado correctamente cliente ");
        }else{
            System.out.println("Error en la insercion cliente");
        }
    }

    public void borrarCliente(int id) {
        ClienteJpaController c=new ClienteJpaController();    
        //c.remove(id);
    }

    public List<Object> getPedidos() {
        PedidoJpaController controller=new PedidoJpaController();
        return controller.listAll();
    }

    public void nuevoPedido(Pedido nuevo) {
       PedidoJpaController controller=new PedidoJpaController();
       controller.add(nuevo);
    }
}
