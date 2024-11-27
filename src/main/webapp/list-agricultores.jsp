<%-- 
    Document   : list-agricultor
    Created on : 22/11/2024, 3:23:00 p. m.
    Author     : ortega
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Lista de Agricultores</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZwl1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f8f9fa;
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
        .nav-link, .btn-logout, .btn-add, .btn-primary, .btn-danger {
            transition: all 0.3s ease;
            text-decoration: none;
        }
        .nav-link {
            color: #ffffff;
            font-weight: 500;
            margin-right: 15px;
        }
        .nav-link:hover {
            color: #b2dfdb;
        }
        .btn-logout {
            background-color: transparent;
            border: none;
            color: #ffffff;
            font-weight: 500;
            padding: 0.4rem 0.8rem;
            border-radius: 5px;
        }
        .btn-logout:hover {
            background-color: rgba(255, 255, 255, 0.1);
            color: #ffffff;
        }
        .navbar-nav {
            align-items: center;
            display: flex;
            margin-left: auto;
            justify-content: flex-end;
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
        .table-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin: 0 auto;
            max-width: 100%;
        }
        .table-responsive {
            width: 100%;
        }
        .table {
            margin: 0 auto;
            width: 100%;
        }
        .table thead {
            background-color: #007b5e;
            color: #ffffff;
        }
        .table-hover tbody tr:hover {
            background-color: #e9f7ef;
        }
        .btn-primary {
            font-size: 0.85rem;
            padding: 0.4rem 0.8rem;
            border-radius: 5px;
            border: 1px solid #dee2e6;
            margin-right: 5px;
            margin-bottom: 10px;
            display: inline-block;
            background-color: #28a745;
            color: #ffffff;
        }
        .btn-primary:hover {
            background-color: #218838;
            color: #ffffff;
        }
        .btn-danger {
            font-size: 0.85rem;
            padding: 0.4rem 0.8rem;
            border-radius: 5px;
            border: 1px solid #dee2e6;
            margin-right: 5px;
            margin-bottom: 10px;
            display: inline-block;
            background-color: #dc3545;
            color: #ffffff;
        }
        .btn-danger:hover {
            background-color: #c82333;
            color: #ffffff;
        }
        td, th {
            text-align: center;
            vertical-align: middle;
        }
        .btn-add {
            font-size: 1rem;
            padding: 0.8rem 1.5rem;
            border-radius: 30px;
            background-color: #007b5e;
            color: #ffffff;
            font-weight: 600;
            box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
        }
        .btn-add:hover {
            background-color: #006b53;
            color: #ffffff;
            text-decoration: none;
        }
        h3 {
            text-align: center;
            font-weight: 600;
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
                        <a href="<%=request.getContextPath()%>/mostrarConsultas" class="nav-link">Ver Consultas</a> <!-- Botón nuevo para Ver Consultas -->
                    </li>
                    <li class="nav-item">
                        <a href="<%=request.getContextPath()%>/logout" class="btn btn-logout">Cerrar sesión</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>


<div class="container mt-5">
    <!-- Bloque para mostrar mensajes de éxito o error sin la "x" -->
    <c:if test="${not empty message}">
        <div class="alert ${message.contains('Error') ? 'alert-danger' : 'alert-success'} alert-dismissible fade show" role="alert">
            <i class="${message.contains('Error') ? 'fas fa-times-circle' : 'fas fa-check-circle'}"></i>
            <c:out value="${message}" />
        </div>
    </c:if>

    <!-- Título y botón para agregar nuevo agricultor -->
    <div class="d-flex justify-content-center align-items-center mb-4">
        <h3 class="text-dark">Lista de Agricultores</h3>
    </div>
    <div class="text-center mb-4">
        <a href="<%=request.getContextPath()%>/new" class="btn btn-add">Nuevo Agricultor</a>
    </div>

    <!-- Tabla con la lista de agricultores -->
    <div class="table-container">
        <div class="table-responsive">
            <table class="table table-hover table-bordered">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Teléfono</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="agricultor" items="${listAgricultores}">
                        <tr>
                            <td><c:out value="${agricultor.idAgricultor}" /></td>
                            <td><c:out value="${agricultor.nombre}" /></td>
                            <td><c:out value="${agricultor.telefono}" /></td>
                            <td>
                                <a href="edit?id=<c:out value='${agricultor.idAgricultor}' />" class="btn btn-primary btn-sm">Editar</a>
                                <a href="delete?id=<c:out value='${agricultor.idAgricultor}' />" class="btn btn-danger btn-sm">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- JavaScript de Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGaHfIlFJIl1gHkkKwXrm9O8iR" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIyFf3VS3XhFp7ik3iDJwljV0G17H0o0Pb26U4A" crossorigin="anonymous"></script>
</body>
</html>
