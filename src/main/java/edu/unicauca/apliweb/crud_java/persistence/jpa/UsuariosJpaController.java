/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.crud_java.persistence.jpa;

/**
 *
 * @author ortega
 */



import edu.unicauca.apliweb.crud_java.persistence.entities.Usuarios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuariosJpaController {
    private EntityManagerFactory emf;

    // Constructor sin parámetros que inicializa EntityManagerFactory
    public UsuariosJpaController(EntityManagerFactory emf1) {
        this.emf = Persistence.createEntityManagerFactory("edu.unicauca.apliweb_CRUD_JAVA_war_1.0PU"); // Ajusta el nombre
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Usuarios validarUsuario(String username, String password) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Usuarios.findByUsername", Usuarios.class)
                    .setParameter("username", username);
            Usuarios usuario = (Usuarios) query.getSingleResult();

            // Verifica si la contraseña coincide
            if (usuario != null && usuario.getPassword().equals(password)) {
                return usuario; // Usuario autenticado
            }
            return null; // Contraseña incorrecta
        } catch (NoResultException e) {
            return null; // Usuario no encontrado
        } finally {
            em.close();
        }
    }
}
