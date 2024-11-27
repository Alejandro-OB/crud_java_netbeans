<%-- 
    Document   : MostrarConsultas
    Created on : 26/11/2024, 6:55:30 p. m.
    Author     : ortega
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mostrar Consultas</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZwl1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
        }
        h1, h2 {
            text-align: center;
            font-weight: 600;
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
        header {
            margin-bottom: 20px;
        }
        .table-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin: 0 auto;
            max-width: 100%;
            margin-bottom: 30px;
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
        td, th {
            text-align: center;
            vertical-align: middle;
        }
        
    </style>
</head>
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
<body>
    

    <div class="container">
        <div class="table-container">
            <h1>Consultas de Agricultores</h1>
            
            <h2>Agricultores con Cantidad de Productos</h2>
            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>Agricultor</th>
                            <th>Total de Productos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${agricultoresConCantidadProductos}">
                            <tr>
                                <td><c:out value="${row[0]}"/></td>
                                <td><c:out value="${row[1]}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="table-container">
            <h2>Agricultores con Más de 3 Productos Registrados</h2>
            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>Agricultor</th>
                            <th>Total de Productos</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${agricultoresConMasProductos}">
                            <tr>
                                <td><c:out value="${row[0]}"/></td>
                                <td><c:out value="${row[1]}"/></td>
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
