/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.jpa;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultorProducto;
import edu.unicauca.apliweb.crud_java.persistence.jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ortega
 */
public class TblAgricultorProductoJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public TblAgricultorProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblAgricultorProducto tblAgricultorProducto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblAgricultorProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblAgricultorProducto tblAgricultorProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblAgricultorProducto = em.merge(tblAgricultorProducto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblAgricultorProducto.getCodigoAp();
                if (findTblAgricultorProducto(id) == null) {
                    throw new NonexistentEntityException("The TblAgricultorProducto with id " + id + " no longer exists.");
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
            TblAgricultorProducto tblAgricultorProducto;
            try {
                tblAgricultorProducto = em.getReference(TblAgricultorProducto.class, id);
                tblAgricultorProducto.getCodigoAp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TblAgricultorProducto with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblAgricultorProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblAgricultorProducto> findTblAgricultorProductoEntities() {
        return findTblAgricultorProductoEntities(true, -1, -1);
    }

    public List<TblAgricultorProducto> findTblAgricultorProductoEntities(int maxResults, int firstResult) {
        return findTblAgricultorProductoEntities(false, maxResults, firstResult);
    }

    private List<TblAgricultorProducto> findTblAgricultorProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblAgricultorProducto.class));
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

    public TblAgricultorProducto findTblAgricultorProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblAgricultorProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblAgricultorProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblAgricultorProducto> rt = cq.from(TblAgricultorProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
