<%-- 
    Document   : login
    Created on : 22/11/2024, 4:53:18 p. m.
    Author     : ortega
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.Cookie"%>
<%
    // Recuperar la cookie de usuario
    String rememberedUser = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("rememberUser".equals(cookie.getName())) {
                rememberedUser = cookie.getValue();
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
</head>
<body>
    <h1>Iniciar Sesión</h1>
    
    <!-- Mostrar mensaje de error -->
    <c:if test="${not empty errorMessage}">
        <p style="color:red;"><c:out value="${errorMessage}" /></p>
    </c:if>
    
    <form action="login" method="post">
        <label>Usuario:</label>
        <input type="text" name="username" value="<%= rememberedUser %>" required><br>
        <label>Contraseña:</label>
        <input type="password" name="password" required><br>
        <label>
            <input type="checkbox" name="remember"> Recordarme
        </label><br>
        <button type="submit">Ingresar</button>
    </form>
</body>
</html>

