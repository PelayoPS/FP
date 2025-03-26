package Persistencia.Controladores;

import java.util.ArrayList;
import java.util.List;

import Persistencia.Entidades.Pedido;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PedidoJpaController implements IDAO {

    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Pedido)) {
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
        List<Pedido> pedidos = null;
        try {
            pedidos = em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
        } finally {
            em.close();
        }
        // from persistencia.Entidades.Pedido to Logica.Pedido
        List<Object> pedidosLogica = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            // Pedido(int id, int idCliente, int idComercial, double total, String fecha)
            pedidosLogica.add(new Logica.Pedido(pedido.getId(), pedido.getIdCliente().getId(),
                    pedido.getIdComercial().getId(), pedido.getTotal(), pedido.getFecha().toString()));
        }
        return pedidosLogica;
    }

    @Override
    public boolean update(Object obj) {
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
    public Logica.Pedido search(Object Key) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ADT_Rec_T4_AlumnosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Pedido pedido = null;
        try {
            transaction.begin();
            pedido = em.find(Pedido.class, Key);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }
        Logica.Pedido pedidoLogica = null;
        if (pedido != null) {
            pedidoLogica = new Logica.Pedido(pedido.getId(), pedido.getIdCliente().getId(),
                    pedido.getIdComercial().getId(), pedido.getTotal(), pedido.getFecha().toString());
        }
        return pedidoLogica;
    }

}
