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
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="https://www.unicauca.edu.co" class="navbar-brand">Aplicación Ejemplo Apliweb</a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista Agricultores</a></li>
        </ul>
    </nav>
</header>

<div class="container col-md-5">
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

            <fieldset class="form-group">
                <label>Nombre</label>
                <input type="text" name="nombre" id="nombre" class="form-control" required 
                       value="<c:out value='${agricultor.nombre}' />" />
            </fieldset>

            <fieldset class="form-group">
                <label>Teléfono</label>
                <input type="text" name="telefono" id="telefono" class="form-control" required
                       value="<c:out value='${agricultor.telefono}' />" />
            </fieldset>

            <button type="submit" class="btn btn-success">Guardar</button>
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

</body>
</html>
