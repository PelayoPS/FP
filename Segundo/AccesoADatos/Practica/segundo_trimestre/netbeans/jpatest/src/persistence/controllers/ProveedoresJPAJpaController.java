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
import persistence.entities.ProveedoresJPA;

/**
 *
 * @author pelayo
 */
public class ProveedoresJPAJpaController implements Serializable {

    public ProveedoresJPAJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProveedoresJPA proveedoresJPA) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proveedoresJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedoresJPA(proveedoresJPA.getId()) != null) {
                throw new PreexistingEntityException("ProveedoresJPA " + proveedoresJPA + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProveedoresJPA proveedoresJPA) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proveedoresJPA = em.merge(proveedoresJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedoresJPA.getId();
                if (findProveedoresJPA(id) == null) {
                    throw new NonexistentEntityException("The proveedoresJPA with id " + id + " no longer exists.");
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
            ProveedoresJPA proveedoresJPA;
            try {
                proveedoresJPA = em.getReference(ProveedoresJPA.class, id);
                proveedoresJPA.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedoresJPA with id " + id + " no longer exists.", enfe);
            }
            em.remove(proveedoresJPA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProveedoresJPA> findProveedoresJPAEntities() {
        return findProveedoresJPAEntities(true, -1, -1);
    }

    public List<ProveedoresJPA> findProveedoresJPAEntities(int maxResults, int firstResult) {
        return findProveedoresJPAEntities(false, maxResults, firstResult);
    }

    private List<ProveedoresJPA> findProveedoresJPAEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProveedoresJPA.class));
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

    public ProveedoresJPA findProveedoresJPA(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProveedoresJPA.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresJPACount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProveedoresJPA> rt = cq.from(ProveedoresJPA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
