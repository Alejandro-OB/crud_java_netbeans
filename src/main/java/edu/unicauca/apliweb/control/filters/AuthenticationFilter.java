/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.unicauca.apliweb.control.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filtro para proteger las páginas y verificar la autenticación del usuario.
 */
@WebFilter("/*") // Aplica el filtro a todas las rutas
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Si no necesitas inicialización, deja este método vacío
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // Obtiene la sesión actual, si existe
        String loginURI = httpRequest.getContextPath() + "/login"; // Ruta del controlador de login
        String loginPage = httpRequest.getContextPath() + "/login.jsp"; // Página del formulario de login

        boolean isLoggedIn = (session != null && session.getAttribute("username") != null); // Verifica autenticación
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI); // Verifica si es una solicitud de login
        boolean isLoginPage = httpRequest.getRequestURI().equals(loginPage); // Verifica si es la página de login
        boolean isStaticResource = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/static/"); // Recursos estáticos

        if (isLoggedIn || isLoginRequest || isLoginPage || isStaticResource) {
            // Continúa con la solicitud si es una página permitida
            chain.doFilter(request, response);
        } else {
            // Redirige al formulario de login si no está autenticado
            httpResponse.sendRedirect(loginPage);
        }
    }

    @Override
    public void destroy() {
        // Si no necesitas limpiar recursos, deja este método vacío
    }
}
