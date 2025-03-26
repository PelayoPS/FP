package Persistencia.Controladores;

import java.util.ArrayList;
import java.util.List;

import Persistencia.Entidades.Comercial;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ComercialJpaController implements IDAO {

    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Comercial)) {
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
        List<Comercial> comerciales = null;
        try {
            comerciales = em.createQuery("SELECT c FROM Comercial c", Comercial.class).getResultList();
        } finally {
            em.close();
        }
        // from persistencia.Entidades.Comercial to Logica.Comercial
        List<Object> comercialesLogica = new ArrayList<>();
        for (Comercial comercial : comerciales) {
            comercialesLogica.add(new Logica.Comercial(comercial.getId(), comercial.getNombre(),
                    comercial.getApellido1() + " " + comercial.getApellido2(), comercial.getComision()));
        }

        return comercialesLogica;
    }

    @Override
    public boolean update(Object obj) {
        if (!(obj instanceof Comercial)) {
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
    public Logica.Comercial search(Object Key) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ADT_Rec_T4_AlumnosPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Comercial comercial = null;
        try {
            transaction.begin();
            comercial = em.find(Comercial.class, Key);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }
        Logica.Comercial comercialLogica = null;
        if (comercial != null) {
            comercialLogica = new Logica.Comercial(comercial.getId(), comercial.getNombre(),
                    comercial.getApellido1() + " " + comercial.getApellido2(), comercial.getComision());
        }
        return comercialLogica;
    }

}
