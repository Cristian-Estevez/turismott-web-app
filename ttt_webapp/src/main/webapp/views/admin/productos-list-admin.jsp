<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/partials/head.jsp"></jsp:include>	
	<link rel="stylesheet" href="/ttt_webapp/assets/css/detalle-imagen.css">
	<link rel="stylesheet" href="/ttt_webapp/assets/css/detalle-form.css">
	<link rel="stylesheet" href="/ttt_webapp/assets/css/tabla-atracciones.css">
</head>
<body>
	<header>
        <jsp:include page="/partials/nav-bar.jsp"></jsp:include>
		<jsp:include page="/partials/botonera-admin.jsp"></jsp:include>
    </header>
	<main>
		<div class="container-fluid text-center">
			<h2 class="es-logo my-5">Estos son todos los productos del parque</h2>
		</div>
		<div class="container text-center">
            <div class="table-responsive">
                <table class="table table-dark table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Imagen</th>
                            <th scope="col">Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${ todosLosProductos }" var="producto">
	                        <tr>
	                            <td><c:out value="${ producto.nombre }"></c:out></td>
	                            <td style="text-transform: capitalize;"><c:out value="${ producto.tipoDeAtraccion }"></c:out></td>
	                            <td><c:out value="${ producto.costo }"></c:out></td>
	                            <td><img class="align-self-center" id="imagen-lista"
	                                    src="<c:out value="${ producto.urlImagen }"></c:out>"
	                                    class="d-block" alt="Imagen de <c:out value="${ producto.nombre }"></c:out>"/>
	                            </td>
	                            <td>
		                            <a href="/ttt_webapp/atracciones/detalle-atraccion?nombreProducto=${ producto.nombre }" class="btn btn-success rounded" role="button">Ver</a>
	                            </td>	                            
	                        </tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </div>            
        </div>		
	</main>
</body>
</html>