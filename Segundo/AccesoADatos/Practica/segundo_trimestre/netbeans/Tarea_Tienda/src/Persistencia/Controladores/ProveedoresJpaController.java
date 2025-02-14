/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Controladores;

import Persistencia.Modelo.Proveedores;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Controlador JPA para la entidad Proveedores.
 * Esta clase proporciona métodos para realizar operaciones CRUD y consultas
 * sobre la tabla de proveedores en la base de datos.
 * Implementa Serializable para permitir la serialización de sus instancias.
 *
 * @author PelayoPS
 */
public class ProveedoresJpaController implements Serializable {

    /**
     * Constructor que inicializa el controlador con una factoría de EntityManager.
     *
     * @param emf EntityManagerFactory utilizado para crear EntityManagers
     */
    public ProveedoresJpaController(EntityManagerFactory emf) {
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
     * Obtiene todos los proveedores de la base de datos.
     *
     * @return Lista con todos los proveedores encontrados
     */
    public List<Proveedores> findProveedoresEntities() {
        return findProveedoresEntities(true, -1, -1);
    }

    /**
     * Obtiene un subconjunto de proveedores con paginación.
     *
     * @param maxResults  número máximo de resultados a devolver
     * @param firstResult posición del primer resultado
     * @return Lista de proveedores limitada según los parámetros especificados
     */
    public List<Proveedores> findProveedoresEntities(int maxResults, int firstResult) {
        return findProveedoresEntities(false, maxResults, firstResult);
    }

    /**
     * Método privado que implementa la búsqueda de proveedores con o sin
     * paginación.
     *
     * @param all         si es true, devuelve todos los resultados; si es false,
     *                    aplica paginación
     * @param maxResults  número máximo de resultados cuando all es false
     * @param firstResult primer resultado cuando all es false
     * @return Lista de proveedores según los criterios especificados
     */
    private List<Proveedores> findProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Proveedores> cq = em.getCriteriaBuilder().createQuery(Proveedores.class);
            Root<Proveedores> root = cq.from(Proveedores.class);
            cq.select(root);
            TypedQuery<Proveedores> q = em.createQuery(cq);
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
     * Busca un proveedor por su identificador.
     *
     * @param id identificador del proveedor a buscar
     * @return Proveedor encontrado o null si no existe
     */
    public Proveedores findProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedores.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Cuenta el número total de proveedores en la base de datos.
     *
     * @return número total de proveedores
     */
    public int getProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
            Root<Proveedores> rt = cq.from(Proveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            TypedQuery<Long> q = em.createQuery(cq);
            return q.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

}
