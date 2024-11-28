/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.unicauca.apliweb.crud_java.persistence.jpa;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblProducto;
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
public class TblProductoJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public TblProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblProducto tblProducto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblProducto tblProducto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblProducto = em.merge(tblProducto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblProducto.getCodigo();
                if (findTblProducto(id) == null) {
                    throw new NonexistentEntityException("The TblProducto with id " + id + " no longer exists.");
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
            TblProducto tblProducto;
            try {
                tblProducto = em.getReference(TblProducto.class, id);
                tblProducto.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TblProducto with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblProducto> findTblProductoEntities() {
        return findTblProductoEntities(true, -1, -1);
    }

    public List<TblProducto> findTblProductoEntities(int maxResults, int firstResult) {
        return findTblProductoEntities(false, maxResults, firstResult);
    }

    private List<TblProducto> findTblProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblProducto.class));
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

    public TblProducto findTblProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblProducto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblProducto> rt = cq.from(TblProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}