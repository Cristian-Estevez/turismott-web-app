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
			<h2 class="es-logo my-5">Estos son todos los usuarios registrados en el sistema</h2>
		</div>
		<div class="container">
			<c:if test="${flash != null}">
				<div class="alert alert-success text-center">
					<p>
						<c:out value="${flash}" />						
					</p>
				</div>
			</c:if>
		</div>
		<div class="container text-center">
            <div class="table-responsive">
                <table class="table table-dark table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Monedas</th>
                            <th scope="col">Tiempo disponible</th>
                            <th scope="col">Tipo favorito</th>
                            <th scope="col">Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${ todosLosUsuarios }" var="usuarioCliente">
	                        <tr>
	                            <td style="text-transform: capitalize;"><c:out value="${ usuarioCliente.nombre }"></c:out></td>
	                            <td><c:out value="${ usuarioCliente.monedasDeOro }"></c:out></td>
	                            <td><c:out value="${ usuarioCliente.tiempoDisponible }"></c:out></td>
	                            <td><c:out value="${ usuarioCliente.tipoAtraccionFavorita }"></c:out></td>
	                            <td>
		                            <a href="/ttt_webapp/admin/editar-usuario.ad?usuarioCliente=${ usuarioCliente.nombre }" class="btn btn-success rounded" role="button">Editar</a>
		                            <a href="/ttt_webapp/admin/eliminar-usuario.ad?usuarioCliente=${ usuarioCliente.nombre }" class="btn btn-danger rounded" role="button">Eliminar</a>
	                            </td>	                            
	                        </tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </div>            
        </div>		
	</main>
	<footer>
        <jsp:include page="/partials/footer.jsp"></jsp:include>
    </footer>
</body>
</html>