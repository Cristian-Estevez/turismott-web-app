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
		<c:choose>
			<c:when test="${!usuario.esAdmin()}">
				<jsp:include page="/partials/barra-estado-usuario.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="/partials/botonera-admin.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
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
		
		<div class="container text-center">
			<p class=" es-logo mt-4 p-4 rounded-3  bg-color-1 txt-color-5">
				<c:out value="${producto.descripcion}"></c:out>
			</p>
		</div>
		
		<div class="container-fluid text-center">
			<h2 class="es-logo my-5"> Esta promo contiene las siguientes atracciones: </h2>
		</div>

    <div class="container text-center">   
            <div class="row">
				<c:forEach items="${ atraccionesDeEstaPromocion }" var="producto">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top " height="300"
								src="${ producto.urlImagen }" alt=""></a>
							<div class="card-body bg-color-1 ">
								<h4 class="card-title ">
									<a class="text-decoration-none txt-color-5 " href="/ttt_webapp/atracciones/detalle-atraccion?nombreProducto=${ producto.nombre }">${ producto.nombre }</a>
								</h4>
								<h5 class=" txt-color-5 ">${ producto.costo }</h5>
								<a href="/ttt_webapp/atracciones/detalle-atraccion?nombreProducto=${ producto.nombre }"
									class="btn btn-success rounded" role="button">Ver</a>
							</div>
							<div class="card-footer bg-color-2">
								<small class="txt-color-4">${ producto.tipoDeAtraccion }</small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
        </div>
		
		
		<div id="contenedor-form" class="container my-5 text-center p-5">			
			<form class="row m-2 pb-2">
				<c:choose>
					<c:when
						test="${usuario.puedeComprar(producto) && usuario.puedeAsistir(producto) && producto.tieneLugar() && !usuario.yaCompro(producto)}">
						<a href="/ttt_webapp/atracciones/comprar.do?nombreProducto=${producto.nombre}&nombreUsuario=${usuario.nombre}"
							class="btn btn-success rounded" role="button">Comprar</a>
					</c:when>
					<c:otherwise>
						<a href="#" class="btn btn-secondary rounded disabled" role="button">Comprar</a>
					</c:otherwise>
				</c:choose>				
			</form>
			<form class="row m-2 pt-2" >
				<div class="col">				
					<button type="submit" onclick="window.history.go(-1); return false;"
						class="form-control btn btn-success border border-dark">Volver</button>
				</div>
			</form>
		</div>
	</main>
</body>
</html>