
package Persistencia.Controladores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import Persistencia.Entidades.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ClienteJpaController implements IDAO {

    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Cliente)) {
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ADT_Rec_T4_AlumnosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(obj);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object> listAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ADT_Rec_T4_AlumnosPU");
        EntityManager em = emf.createEntityManager();
        List<Cliente> clientes = null;
        try {
            clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
        } finally {
            em.close();
        }
        // from persistencia.Entidades.Cliente to Logica.Cliente
        List<Object> clientesLogica = new ArrayList<>();
        for (Cliente cliente : clientes) {
            // Cliente(int idCliente, String nombre, String apellidos, String ciudad,
            // Integer categoria)
            clientesLogica.add(new Logica.Cliente(cliente.getId(), cliente.getNombre(),
                    cliente.getApellido1() + " " + cliente.getApellido2(), cliente.getCiudad(),
                    cliente.getCategoria()));
        }
        return clientesLogica;
    }

    @Override
    public boolean update(Object obj) {
        if (!(obj instanceof Cliente)) {
            return false;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ADT_Rec_T4_AlumnosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(obj);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Logica.Cliente search(Object Key) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ADT_Rec_T4_AlumnosPU");
        EntityManager em = emf.createEntityManager();
        Cliente cliente = null;
        try {
            cliente = em.find(Cliente.class, Key);
        } finally {
            em.close();
        }
        Logica.Cliente clienteLogica = null;
        if (cliente != null) {
            clienteLogica = new Logica.Cliente(cliente.getId(), cliente.getNombre(),
                    cliente.getApellido1() + " " + cliente.getApellido2(), cliente.getCiudad(),
                    cliente.getCategoria());
        }
        return clienteLogica;
    }

}
