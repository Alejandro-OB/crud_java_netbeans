<%-- 
    Document   : login
    Created on : 22/11/2024, 4:53:18 p.�m.
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
    <title>Iniciar Sesi�n</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZwl1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            max-width: 400px;
            width: 100%;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            font-weight: 600;
            margin-bottom: 20px;
        }
        label {
            font-weight: 500;
        }
        .form-control {
            border-radius: 5px;
            margin-bottom: 15px;
        }
        .btn-login {
            width: 100%;
            padding: 0.6rem;
            border-radius: 5px;
            background-color: #007b5e;
            color: #ffffff;
            font-weight: 600;
            transition: all 0.3s ease;
        }
        .btn-login:hover {
            background-color: #006b53;
            color: #ffffff;
        }
        .remember-me {
            margin-bottom: 15px;
            display: flex;
            align-items: center;
        }
        .form-check-input {
            margin-right: 10px;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h1>Iniciar Sesi�n</h1>
    
    <!-- Mostrar mensaje de error -->
    <c:if test="${not empty errorMessage}">
        <p class="error-message"><c:out value="${errorMessage}" /></p>
    </c:if>
    
    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Usuario:</label>
            <input type="text" id="username" name="username" class="form-control" value="<%= rememberedUser %>" required>
        </div>
        <div class="form-group">
            <label for="password">Contrase�a:</label>
            <input type="password" id="password" name="password" class="form-control" required>
        </div>
        <div class="form-group form-check remember-me">
            <input type="checkbox" class="form-check-input" id="remember" name="remember">
            <label class="form-check-label" for="remember">Recordarme</label>
        </div>
        <button type="submit" class="btn btn-login">Ingresar</button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGaHfIlFJIl1gHkkKwXrm9O8iR" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIyFf3VS3XhFp7ik3iDJwljV0G17H0o0Pb26U4A" crossorigin="anonymous"></script>
</body>
</html>
