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
import persistence.entities.SuministraJPA;

/**
 *
 * @author pelayo
 */
public class SuministraJPAJpaController implements Serializable {

    public SuministraJPAJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SuministraJPA suministraJPA) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(suministraJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSuministraJPA(suministraJPA.getCodigoPieza()) != null) {
                throw new PreexistingEntityException("SuministraJPA " + suministraJPA + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SuministraJPA suministraJPA) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            suministraJPA = em.merge(suministraJPA);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = suministraJPA.getCodigoPieza();
                if (findSuministraJPA(id) == null) {
                    throw new NonexistentEntityException("The suministraJPA with id " + id + " no longer exists.");
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
            SuministraJPA suministraJPA;
            try {
                suministraJPA = em.getReference(SuministraJPA.class, id);
                suministraJPA.getCodigoPieza();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suministraJPA with id " + id + " no longer exists.", enfe);
            }
            em.remove(suministraJPA);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SuministraJPA> findSuministraJPAEntities() {
        return findSuministraJPAEntities(true, -1, -1);
    }

    public List<SuministraJPA> findSuministraJPAEntities(int maxResults, int firstResult) {
        return findSuministraJPAEntities(false, maxResults, firstResult);
    }

    private List<SuministraJPA> findSuministraJPAEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SuministraJPA.class));
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

    public SuministraJPA findSuministraJPA(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SuministraJPA.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuministraJPACount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SuministraJPA> rt = cq.from(SuministraJPA.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
