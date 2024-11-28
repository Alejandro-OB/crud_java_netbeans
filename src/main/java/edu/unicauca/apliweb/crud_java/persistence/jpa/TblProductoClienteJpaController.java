/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.jpa;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblProductoCliente;
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
public class TblProductoClienteJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public TblProductoClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblProductoCliente tblProductoCliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblProductoCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblProductoCliente tblProductoCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblProductoCliente = em.merge(tblProductoCliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblProductoCliente.getCodigoPc();
                if (findTblProductoCliente(id) == null) {
                    throw new NonexistentEntityException("The TblProductoCliente with id " + id + " no longer exists.");
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
            TblProductoCliente tblProductoCliente;
            try {
                tblProductoCliente = em.getReference(TblProductoCliente.class, id);
                tblProductoCliente.getCodigoPc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TblProductoCliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblProductoCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblProductoCliente> findTblProductoClienteEntities() {
        return findTblProductoClienteEntities(true, -1, -1);
    }

    public List<TblProductoCliente> findTblProductoClienteEntities(int maxResults, int firstResult) {
        return findTblProductoClienteEntities(false, maxResults, firstResult);
    }

    private List<TblProductoCliente> findTblProductoClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblProductoCliente.class));
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

    public TblProductoCliente findTblProductoCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblProductoCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblProductoClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblProductoCliente> rt = cq.from(TblProductoCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
