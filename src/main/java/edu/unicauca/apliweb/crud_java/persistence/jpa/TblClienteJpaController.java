/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.jpa;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblCliente;
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
public class TblClienteJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public TblClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblCliente tblCliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblCliente tblCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblCliente = em.merge(tblCliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblCliente.getCedula();
                if (findTblCliente(id) == null) {
                    throw new NonexistentEntityException("The TblCliente with id " + id + " no longer exists.");
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
            TblCliente tblCliente;
            try {
                tblCliente = em.getReference(TblCliente.class, id);
                tblCliente.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TblCliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblCliente> findTblClienteEntities() {
        return findTblClienteEntities(true, -1, -1);
    }

    public List<TblCliente> findTblClienteEntities(int maxResults, int firstResult) {
        return findTblClienteEntities(false, maxResults, firstResult);
    }

    private List<TblCliente> findTblClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblCliente.class));
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

    public TblCliente findTblCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblCliente> rt = cq.from(TblCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
