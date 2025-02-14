/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Controladores;

import Persistencia.Modelo.Piezas;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Controlador JPA para la entidad Piezas.
 * Esta clase proporciona métodos para realizar operaciones CRUD y consultas
 * sobre la tabla de piezas en la base de datos.
 * Implementa Serializable para permitir la serialización de sus instancias.
 *
 * @author PelayoPS
 */
public class PiezasJpaController implements Serializable {

    /**
     * Constructor que inicializa el controlador con una factoría de EntityManager.
     *
     * @param emf EntityManagerFactory utilizado para crear EntityManagers
     */
    public PiezasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    /**
     * Obtiene un EntityManager nuevo.
     *
     * @return EntityManager nuevo para realizar operaciones de persistencia
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Obtiene todas las piezas de la base de datos.
     *
     * @return Lista con todas las piezas encontradas
     */
    public List<Piezas> findPiezasEntities() {
        return findPiezasEntities(true, -1, -1);
    }

    /**
     * Obtiene un subconjunto de piezas con paginación.
     *
     * @param maxResults  número máximo de resultados a devolver
     * @param firstResult posición del primer resultado
     * @return Lista de piezas limitada según los parámetros especificados
     */
    public List<Piezas> findPiezasEntities(int maxResults, int firstResult) {
        return findPiezasEntities(false, maxResults, firstResult);
    }

    /**
     * Método privado que implementa la búsqueda de piezas con o sin paginación.
     *
     * @param all         si es true, devuelve todos los resultados; si es false,
     *                    aplica paginación
     * @param maxResults  número máximo de resultados cuando all es false
     * @param firstResult primer resultado cuando all es false
     * @return Lista de piezas según los criterios especificados
     */
    private List<Piezas> findPiezasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Piezas> cq = em.getCriteriaBuilder().createQuery(Piezas.class);
            Root<Piezas> root = cq.from(Piezas.class);
            cq.select(root);
            TypedQuery<Piezas> q = em.createQuery(cq.select(root));
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<Piezas> piezas = q.getResultList();
            return piezas;
        } finally {
            em.close();
        }
    }

    /**
     * Busca una pieza por su identificador.
     *
     * @param id identificador de la pieza a buscar
     * @return Pieza encontrada o null si no existe
     */
    public Piezas findPiezas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Piezas.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Cuenta el número total de piezas en la base de datos.
     *
     * @return número total de piezas
     */
    public int getPiezasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Piezas> rt = cq.from(Piezas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

}
