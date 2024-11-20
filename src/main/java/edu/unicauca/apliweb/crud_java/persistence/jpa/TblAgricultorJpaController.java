/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.jpa;

import java.io.Serializable;
import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultor;
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
public class TblAgricultorJpaController implements Serializable {
    
    private EntityManagerFactory emf = null;
     

    public TblAgricultorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblAgricultor tblAgricultor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tblAgricultor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(TblAgricultor tblAgricultor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tblAgricultor = em.merge(tblAgricultor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblAgricultor.getIdAgricultor();
                if (findTblAgricultor(id) == null) {
                    throw new NonexistentEntityException("The TblAgricultor with id " + id + " no longer exists.");
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
            TblAgricultor tblAgricultor;
            try {
                tblAgricultor = em.getReference(TblAgricultor.class, id);
                tblAgricultor.getIdAgricultor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TblAgricultor with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblAgricultor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<TblAgricultor> findTblAgricultorEntities() {
        return findTblAgricultorEntities(true, -1, -1);
    }

    public List<TblAgricultor> findTblAgricultorEntities(int maxResults, int firstResult) {
        return findTblAgricultorEntities(false, maxResults, firstResult);
    }

    private List<TblAgricultor> findTblAgricultorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblAgricultor.class));
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

    public TblAgricultor findTblAgricultor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblAgricultor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblAgricultorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblAgricultor> rt = cq.from(TblAgricultor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    
}
