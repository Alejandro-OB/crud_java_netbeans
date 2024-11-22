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
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="https://www.unicauca.edu.co" class="navbar-brand">Aplicación Ejemplo Apliweb</a>
        </div>
        <div class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista Agricultores</a></li>
        </div>
    </nav>
</header>

<div class="container mt-4">
    <!-- Bloque para mostrar mensajes de éxito o error -->
    <c:if test="${not empty message}">
        <div class="alert ${message.contains('Error') ? 'alert-danger' : 'alert-success'}">
            <c:out value="${message}" />
        </div>
    </c:if>

    <!-- Título y botón para agregar nuevo agricultor -->
    <h3 class="text-center">Lista de Agricultores</h3>
    <div class="text-left mb-3">
        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Nuevo Agricultor</a>
    </div>

    <!-- Tabla con la lista de agricultores -->
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="agricultor" items="${listAgricultores}">
                <tr>
                    <td><c:out value="${agricultor.idAgricultor}" /></td>
                    <td><c:out value="${agricultor.nombre}" /></td>
                    <td><c:out value="${agricultor.telefono}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${agricultor.idAgricultor}' />">Editar</a>
                        &nbsp;|&nbsp;
                        <a href="delete?id=<c:out value='${agricultor.idAgricultor}' />">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

