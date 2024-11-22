<%-- 
    Document   : login
    Created on : 22/11/2024, 4:53:18 p. m.
    Author     : ortega
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <input type="text" name="username" required><br>
        <label>Contraseña:</label>
        <input type="password" name="password" required><br>
        <button type="submit">Ingresar</button>
    </form>
</body>
</html>
