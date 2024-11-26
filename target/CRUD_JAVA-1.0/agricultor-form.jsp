<%-- 
    Document   : agricultor-form
    Created on : 22/11/2024, 3:30:49 p. m.
    Author     : ortega
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Formulario Agricultor</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZwl1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f0f2f5;
        }
        header {
            margin-bottom: 20px;
        }
        .navbar {
            background-color: #004d40;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
        .navbar-brand {
            font-weight: bold;
            color: #ffffff;
            text-decoration: none;
        }
        .navbar-brand:hover {
            color: #a7ffeb;
        }
        .navbar .container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .nav-link, .btn-logout {
            color: #ffffff;
            font-weight: 500;
            margin-right: 15px;
            transition: all 0.3s ease;
            text-decoration: none;
        }
        .nav-link:hover, .btn-logout:hover {
            color: #b2dfdb;
        }
        .btn-logout {
            background-color: transparent;
            border: none;
            font-weight: 500;
            padding: 0.4rem 0.8rem;
            border-radius: 5px;
        }
        .navbar-nav {
            display: flex;
            align-items: center;
            list-style: none;
        }
        .nav-item {
            margin-left: 20px;
        }
        .navbar-toggler {
            border: none;
            background-color: #004d40;
        }
        .navbar-toggler-icon {
            background-image: url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 30 30'%3E%3Cpath stroke='rgba%28255, 255, 255, 0.75%29' stroke-width='2' d='M4 7h22M4 15h22M4 23h22'/%3E%3C/svg%3E");
        }
        .form-container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .form-group {
            margin-bottom: 2.5rem;
        }
        .form-group label {
            font-weight: 600;
            margin-bottom: 0.5rem;
            display: block;
            color: #333;
            font-size: 1.2rem;
        }
        .form-control {
            height: calc(2.8rem + 2px);
            font-size: 1.1rem;
            border-radius: 15px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border: 1px solid #ced4da;
            transition: all 0.3s ease;
        }
        .form-control:focus {
            border-color: #28a745;
            box-shadow: 0 0 12px rgba(40, 167, 69, 0.5);
        }
        .btn-save {
            background-color: #004d40;
            color: #ffffff;
            font-weight: 700;
            border-radius: 50px;
            padding: 0.9rem 2rem;
            box-shadow: 0 10px 20px rgba(0, 77, 64, 0.4);
            border: none;
            transition: all 0.3s ease;
            font-size: 1.1rem;
        }
        .btn-save:hover {
            background-color: #00352e;
            transform: translateY(-3px);
            box-shadow: 0 12px 25px rgba(0, 77, 64, 0.3);
        }
        .alert {
            max-width: 600px;
            margin: 20px auto;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            font-size: 1rem;
            font-weight: 500;
        }
        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .alert-danger {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md">
        <div class="container d-flex justify-content-between align-items-center">
            <a href="https://www.unicauca.edu.co" class="navbar-brand">Aplicación Ejemplo Apliweb</a>
            <div class="navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/list" class="nav-link">Lista Agricultores</a>
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/logout" class="btn btn-logout">Cerrar sesión</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<c:if test="${not empty message}">
    <div class="alert ${message.contains('Error') ? 'alert-danger' : 'alert-success'} alert-dismissible fade show" role="alert">
        <c:out value="${message}" />
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>

<div class="form-container">
    <div class="card">
        <div class="card-body">
            <c:choose>
                <c:when test="${agricultor != null}">
                    <form action="update" method="post" onsubmit="return validateForm()">
                        <input type="hidden" name="id" value="<c:out value='${agricultor.idAgricultor}' />" />
                </c:when>
                <c:otherwise>
                    <form action="insert" method="post" onsubmit="return validateForm()">
                </c:otherwise>
            </c:choose>

            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" name="nombre" id="nombre" class="form-control" required 
                       value="<c:out value='${agricultor.nombre}' />" />
            </div>

            <div class="form-group">
                <label for="telefono">Teléfono</label>
                <input type="text" name="telefono" id="telefono" class="form-control" required
                       value="<c:out value='${agricultor.telefono}' />" />
            </div>

            <button type="submit" class="btn btn-save">Guardar</button>
            </form>
        </div>
    </div>
</div>

<script>
    function validateForm() {
        let nombre = document.getElementById("nombre").value.trim();
        let telefono = document.getElementById("telefono").value.trim();

        if (nombre === "") {
            alert("El campo 'Nombre' es obligatorio.");
            return false;
        }
        if (telefono === "") {
            alert("El campo 'Teléfono' es obligatorio.");
            return false;
        }
        return true;
    }
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGaHfIlFJIl1gHkkKwXrm9O8iR" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIyFf3VS3XhFp7ik3iDJwljV0G17H0o0Pb26U4A" crossorigin="anonymous"></script>
</body>
</html>
