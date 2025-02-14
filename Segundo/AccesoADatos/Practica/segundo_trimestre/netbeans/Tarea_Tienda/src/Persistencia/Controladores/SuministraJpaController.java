/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Controladores;

import Persistencia.Modelo.Suministra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Controlador JPA para la entidad Suministra.
 * Esta clase maneja las operaciones de persistencia para la entidad Suministra,
 * incluyendo búsqueda y conteo de registros.
 * 
 * @author PelayoPS
 */
public class SuministraJpaController implements Serializable {

    /**
     * Constructor que inicializa el controlador con una fábrica de EntityManager.
     * 
     * @param emf EntityManagerFactory a utilizar
     */
    public SuministraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    /**
     * Obtiene un EntityManager de la fábrica.
     * 
     * @return EntityManager nuevo
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Encuentra todas las entidades Suministra.
     * 
     * @return Lista de todas las entidades Suministra
     */
    public List<Suministra> findSuministraEntities() {
        return findSuministraEntities(true, -1, -1);
    }

    /**
     * Encuentra entidades Suministra con paginación.
     * 
     * @param maxResults  número máximo de resultados
     * @param firstResult posición del primer resultado
     * @return Lista de entidades Suministra paginada
     */
    public List<Suministra> findSuministraEntities(int maxResults, int firstResult) {
        return findSuministraEntities(false, maxResults, firstResult);
    }

    /**
     * Método privado para encontrar entidades Suministra con o sin paginación.
     * 
     * @param all         si es true, retorna todos los resultados
     * @param maxResults  número máximo de resultados cuando all es false
     * @param firstResult posición del primer resultado cuando all es false
     * @return Lista de entidades Suministra
     */
    private List<Suministra> findSuministraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Suministra> cq = em.getCriteriaBuilder().createQuery(Suministra.class);
            Root<Suministra> root = cq.from(Suministra.class);
            cq.select(root);
            TypedQuery<Suministra> q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Encuentra una entidad Suministra por su ID.
     * 
     * @param id ID de la entidad a buscar
     * @return Entidad Suministra encontrada o null si no existe
     */
    public Suministra findSuministra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suministra.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Obtiene el número total de entidades Suministra.
     * 
     * @return número total de entidades Suministra
     */
    public int getSuministraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Suministra> rt = cq.from(Suministra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

}
