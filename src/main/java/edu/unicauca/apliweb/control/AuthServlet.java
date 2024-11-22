/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.unicauca.apliweb.control;

import edu.unicauca.apliweb.crud_java.persistence.entities.Usuarios;
import edu.unicauca.apliweb.crud_java.persistence.jpa.UsuariosJpaController;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/login", "/logout"})
public class AuthServlet extends HttpServlet {

    private UsuariosJpaController usuariosJpa;

    @Override
    public void init() throws ServletException {
        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        usuariosJpa = new UsuariosJpaController(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuarios usuario = usuariosJpa.validarUsuario(username, password); // Valida credenciales en la BD

        if (usuario != null) {
            // Credenciales correctas: crear sesión y redirigir
            HttpSession session = request.getSession();
            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            response.sendRedirect("ServletCrudJava"); // Redirige al CRUD
        } else {
            // Credenciales incorrectas: enviar mensaje de error al JSP
            request.setAttribute("errorMessage", "Usuario o contraseña incorrectos. Intente nuevamente.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response); // Asegúrate de usar forward, no redirect
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/logout".equals(action)) {
            handleLogout(request, response);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Usuarios usuario = usuariosJpa.validarUsuario(username, password);

        if (usuario != null) {
            // Usuario autenticado: almacenar información en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            response.sendRedirect("ServletCrudJava"); // Redirige a la página protegida
        } else {
            // Credenciales inválidas
            request.setAttribute("message", "Usuario o contraseña incorrectos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalida la sesión
        }
        response.sendRedirect("login.jsp"); // Redirige al formulario de login
    }
}
