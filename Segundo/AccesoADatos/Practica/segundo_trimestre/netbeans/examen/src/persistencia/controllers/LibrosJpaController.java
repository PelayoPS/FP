/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.controllers.exceptions.NonexistentEntityException;
import persistencia.controllers.exceptions.PreexistingEntityException;
import persistencia.modelo.Alumno;
import persistencia.modelo.Libros;

/**
 *
 * @author pelay
 */
public class LibrosJpaController implements IDAO<Libros> {

    public LibrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libros libros) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(libros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLibros(libros.getNRegistro()) != null) {
                throw new PreexistingEntityException("Libros " + libros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libros libros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            libros = em.merge(libros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = libros.getNRegistro();
                if (findLibros(id) == null) {
                    throw new NonexistentEntityException("The libros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libros> findLibrosEntities() {
        return findLibrosEntities(true, -1, -1);
    }

    public List<Libros> findLibrosEntities(int maxResults, int firstResult) {
        return findLibrosEntities(false, maxResults, firstResult);
    }

    private List<Libros> findLibrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libros.class));
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

    public Libros findLibros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libros.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean add(Libros o) {
        try {
            create(o);
            return true;
        } catch (PreexistingEntityException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Libros search(Libros o) {
        return findLibros(o.getNRegistro());
    }

    @Override
    public List<Libros> listAll() {
        return findLibrosEntities();
    }

}
