/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.unicauca.apliweb.control;

import edu.unicauca.apliweb.crud_java.persistence.entities.TblAgricultor;
import edu.unicauca.apliweb.crud_java.persistence.jpa.TblAgricultorJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ortega
 */

@WebServlet("/")
public class ServletCrudJava extends HttpServlet {
    private TblAgricultorJpaController agricultorJpa;
    private final static String PU = "edu.unicauca.apliweb_CRUD_JAVA_war_1.0PU";

    @Override
    public void init() throws ServletException {
        super.init();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        agricultorJpa = new TblAgricultorJpaController(emf);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertAgricultor(request, response);
                    break;
                case "/delete":
                    deleteAgricultor(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateAgricultor(request, response);
                    break;
                default:
                    listAgricultores(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAgricultores(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<TblAgricultor> listAgricultores = agricultorJpa.findTblAgricultorEntities();
        request.setAttribute("listAgricultores", listAgricultores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-agricultores.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("agricultor-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TblAgricultor existingAgricultor = agricultorJpa.findTblAgricultor(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("agricultor-form.jsp");
        request.setAttribute("agricultor", existingAgricultor);
        dispatcher.forward(request, response);
    }

    private void insertAgricultor(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        String nombre = request.getParameter("nombre").trim();
        String telefono = request.getParameter("telefono").trim();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            
            request.setAttribute("message", "Error: Todos los campos son obligatorios.");
            listAgricultores(request, response);
            return;
        }

        try {
            TblAgricultor agricultor = new TblAgricultor();
            agricultor.setNombre(nombre);
            agricultor.setTelefono(telefono);

            agricultorJpa.create(agricultor);

            
            request.setAttribute("message", "Agricultor agregado exitosamente.");
        } catch (Exception e) {
            
            request.setAttribute("message", "Error al agregar el agricultor: " + e.getMessage());
        }
        listAgricultores(request, response);
    }


    private void updateAgricultor(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre").trim();
        String telefono = request.getParameter("telefono").trim();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            
            request.setAttribute("message", "Error: Todos los campos son obligatorios.");
            listAgricultores(request, response);
            return;
        }

        try {
            TblAgricultor agricultor = agricultorJpa.findTblAgricultor(id);
            agricultor.setNombre(nombre);
            agricultor.setTelefono(telefono);

            agricultorJpa.edit(agricultor);

           
            request.setAttribute("message", "Agricultor actualizado exitosamente.");
        } catch (Exception e) {
            
            request.setAttribute("message", "Error al actualizar el agricultor: " + e.getMessage());
        }
        listAgricultores(request, response);
    }


    private void deleteAgricultor(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            agricultorJpa.destroy(id); 
            request.setAttribute("message", "Agricultor eliminado exitosamente.");
        } catch (Exception e) {
            
            request.setAttribute("message", "Error al eliminar el agricultor: " + e.getMessage());
        }
        listAgricultores(request, response);
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Descripción del ServletAppAgricultores";
    }
}