/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.jpa;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultorCliente;
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
public class TblAgricultorClienteJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public TblAgricultorClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblAgricultorCliente tblAgricultorCliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblAgricultorCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblAgricultorCliente tblAgricultorCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblAgricultorCliente = em.merge(tblAgricultorCliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblAgricultorCliente.getCodigoAc();
                if (findTblAgricultorCliente(id) == null) {
                    throw new NonexistentEntityException("The TblAgricultorCliente with id " + id + " no longer exists.");
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
            TblAgricultorCliente tblAgricultorCliente;
            try {
                tblAgricultorCliente = em.getReference(TblAgricultorCliente.class, id);
                tblAgricultorCliente.getCodigoAc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TblAgricultorCliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblAgricultorCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblAgricultorCliente> findTblAgricultorClienteEntities() {
        return findTblAgricultorClienteEntities(true, -1, -1);
    }

    public List<TblAgricultorCliente> findTblAgricultorClienteEntities(int maxResults, int firstResult) {
        return findTblAgricultorClienteEntities(false, maxResults, firstResult);
    }

    private List<TblAgricultorCliente> findTblAgricultorClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblAgricultorCliente.class));
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

    public TblAgricultorCliente findTblAgricultorCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblAgricultorCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblAgricultorClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblAgricultorCliente> rt = cq.from(TblAgricultorCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
