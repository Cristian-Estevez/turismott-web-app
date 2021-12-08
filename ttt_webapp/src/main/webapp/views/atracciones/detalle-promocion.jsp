<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/partials/head.jsp"></jsp:include>	
</head>
<body>
	<header>
        <jsp:include page="/partials/nav-bar.jsp"></jsp:include>
		<jsp:include page="/partials/barra-estado-usuario.jsp"></jsp:include>
    </header>
	<main>


		
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
	</main>
</body>
</html>