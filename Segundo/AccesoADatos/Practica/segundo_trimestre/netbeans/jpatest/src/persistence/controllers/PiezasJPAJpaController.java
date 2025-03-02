/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistence.controllers.exceptions.NonexistentEntityException;
import persistence.controllers.exceptions.PreexistingEntityException;
import persistence.entities.PiezasJPA;

/**
 *
 * @author pelayo
 */
public class PiezasJPAJpaController implements Serializable {

    public PiezasJPAJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PiezasJPA piezasJPA) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(piezasJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPiezasJPA(piezasJPA.getCodigo()) != null) {
                throw new PreexistingEntityException("PiezasJPA " + piezasJPA + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PiezasJPA piezasJPA) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            piezasJPA = em.merge(piezasJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = piezasJPA.getCodigo();
                if (findPiezasJPA(id) == null) {
                    throw new NonexistentEntityException("The piezasJPA with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PiezasJPA piezasJPA;
            try {
                piezasJPA = em.getReference(PiezasJPA.class, id);
                piezasJPA.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The piezasJPA with id " + id + " no longer exists.", enfe);
            }
            em.remove(piezasJPA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PiezasJPA> findPiezasJPAEntities() {
        return findPiezasJPAEntities(true, -1, -1);
    }

    public List<PiezasJPA> findPiezasJPAEntities(int maxResults, int firstResult) {
        return findPiezasJPAEntities(false, maxResults, firstResult);
    }

    private List<PiezasJPA> findPiezasJPAEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PiezasJPA.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PiezasJPA findPiezasJPA(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PiezasJPA.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezasJPACount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PiezasJPA> rt = cq.from(PiezasJPA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
