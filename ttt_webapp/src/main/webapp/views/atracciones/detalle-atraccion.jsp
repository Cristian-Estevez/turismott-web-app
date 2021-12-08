<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/partials/head.jsp"></jsp:include>
	<link rel="stylesheet" href="/ttt_webapp/assets/css/detalle-imagen.css">
	<link rel="stylesheet" href="/ttt_webapp/assets/css/detalle-form.css">
</head>
<body>
	<header>
        <jsp:include page="/partials/nav-bar.jsp"></jsp:include>
		<jsp:include page="/partials/barra-estado-usuario.jsp"></jsp:include>
    </header>
    <main>

		<div class="container-fluid text-center">
			<h2 id="titulo" class="es-logo my-5"><c:out value="${producto.nombre}"></c:out></h2>
		</div>
		
		<div id="contenedor-imagen" class="contenedor-imagen slide py-4" data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img id="imagen-detalle"
						src="<c:out value="${ producto.urlImagen }"></c:out>"
						class="d-block" alt="Mordor">
				</div>				
			</div>			
		</div>
		
		<div class="container-fluid text-center">
			<p>
				<c:out value="${producto.descripcion}"></c:out>
			</p>
		</div>
		
		<div id="contenedor-form" class="container my-5 text-center p-5">			
			<form class="row m-2 pb-2">
				<c:choose>
					<c:when
						test="${usuario.puedeComprar(producto) && usuario.puedeAsistir(producto) && producto.tieneLugar()}">
						<a href="comprar.do?nombreProducto=${producto.nombre}&nombreUsuario=${usuario.nombre}"
							class="btn btn-success rounded" role="button">Comprar</a>
					</c:when>
					<c:otherwise>
						<a href="#" class="btn btn-secondary rounded disabled" role="button">Comprar</a>
					</c:otherwise>
				</c:choose>				
			</form>
			<form class="row m-2 pt-2" action="atraccion-list.jsp">
				<div class="col">				
					<button type="submit"
						class="form-control btn btn-success border border-dark">Volver</button>
				</div>
			</form>
		</div>
		
		
		
	</main>
</body>
</html>