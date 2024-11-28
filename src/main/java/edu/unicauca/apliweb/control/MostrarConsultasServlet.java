/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.unicauca.apliweb.control;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultor;
import edu.unicauca.apliweb.crud_java.persistence.jpa.TblAgricultorJpaController;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/mostrarConsultas")
public class MostrarConsultasServlet extends HttpServlet {
    private TblAgricultorJpaController agricultorJpa;
    private final static String PU = "edu.unicauca.apliweb_CRUD_JAVA_war_1.0PU";

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        agricultorJpa = new TblAgricultorJpaController(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();

        // Obtener todos los agricultores con la cantidad de productos que cada uno tiene registrados
        String consulta1 = "SELECT a.nombre, COUNT(p) AS totalProductos FROM TblAgricultor a " +
                   "LEFT JOIN a.tblAgricultorProductoCollection ap " +
                   "LEFT JOIN ap.codigo p " +
                   "GROUP BY a.nombre";


        Query query1 = em.createQuery(consulta1);
        List<Object[]> agricultoresConCantidadProductos = query1.getResultList();
        request.setAttribute("agricultoresConCantidadProductos", agricultoresConCantidadProductos);

        // Obtener los agricultores que tienen más de 3 productos registrados y ordenarlos por cantidad de productos
        String consulta2 = "SELECT a.nombre, COUNT(p) AS totalProductos FROM TblAgricultor a " +
                   "LEFT JOIN a.tblAgricultorProductoCollection ap " +
                   "LEFT JOIN ap.codigo p " +
                   "GROUP BY a.nombre " +
                   "HAVING COUNT(p) > 3 " +
                   "ORDER BY totalProductos DESC";


        Query query2 = em.createQuery(consulta2);
        List<Object[]> agricultoresConMasProductos = query2.getResultList();
        request.setAttribute("agricultoresConMasProductos", agricultoresConMasProductos);

        em.close();

        request.getRequestDispatcher("/MostrarConsultas.jsp").forward(request, response);
        
    }
}