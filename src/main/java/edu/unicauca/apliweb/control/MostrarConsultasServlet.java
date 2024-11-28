/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.unicauca.apliweb.control;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mostrarConsultas")
public class MostrarConsultasServlet extends HttpServlet {
    private final static String PU = "edu.unicauca.apliweb_CRUD_JAVA_war_1.0PU";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();

        try {
            
             String consulta1 = "SELECT a.nombre AS Agricultor, p.temporada AS Temporada, COUNT(p.codigo) AS TotalProductos " +
                   "FROM tbl_agricultor a " +
                   "JOIN tbl_agricultor_producto ap ON a.id_agricultor = ap.id_agricultor " +
                   "JOIN tbl_producto p ON ap.codigo = p.codigo " +
                   "GROUP BY a.nombre, p.temporada " +
                   "ORDER BY a.nombre, TotalProductos DESC";

            Query query1 = em.createNativeQuery(consulta1);
            List<Object[]> resultadoConsulta1 = query1.getResultList();
            request.setAttribute("resultadoConsulta1", resultadoConsulta1);

            
            String consulta2 = "SELECT a.nombre AS Agricultor, p.temporada AS Temporada, COUNT(p.codigo) AS TotalProductos " +
                   "FROM tbl_agricultor a " +
                   "JOIN tbl_agricultor_producto ap ON a.id_agricultor = ap.id_agricultor " +
                   "JOIN tbl_producto p ON ap.codigo = p.codigo " +
                   "GROUP BY a.nombre, p.temporada " +
                   "HAVING COUNT(p.codigo) > 5 " +
                   "ORDER BY TotalProductos DESC";

            Query query2 = em.createNativeQuery(consulta2);
            List<Object[]> resultadoConsulta2 = query2.getResultList();
            request.setAttribute("resultadoConsulta2", resultadoConsulta2);

            String consulta3 = "SELECT a.nombre AS Agricultor, p.nombre AS Producto, p.temporada AS Temporada, " +
                   "c.nombre AS Cliente, c.direccion AS DireccionCliente " +
                   "FROM tbl_cliente c " +
                   "JOIN tbl_agricultor_producto ap ON c.cedula = ap.id_agricultor " + // Ajustar si la relación es diferente
                   "JOIN tbl_producto p ON ap.codigo = p.codigo " +
                   "JOIN tbl_agricultor a ON ap.id_agricultor = a.id_agricultor " +
                   "WHERE p.temporada IS NOT NULL " +
                   "ORDER BY Temporada, a.nombre";

            Query query3 = em.createNativeQuery(consulta3);
            List<Object[]> resultadoConsulta3 = query3.getResultList();
            request.setAttribute("resultadoConsulta3", resultadoConsulta3);

        } finally {
            em.close();
        }

        request.getRequestDispatcher("/MostrarConsultas.jsp").forward(request, response);
    }
}
