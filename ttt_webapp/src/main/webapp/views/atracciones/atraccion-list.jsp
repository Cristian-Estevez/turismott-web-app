<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

	<jsp:include page="/partials/head.jsp"></jsp:include>	
    
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
            <h2 id="titulo" class="es-logo my-5">Nuestros Productos</h2>
            
            <c:choose>
            	<c:when test="${ usuario != null }">
		            <p id="membrete" class="p-4 bg-light">
		                Hola <span style="text-transform:capitalize;"><c:out value="${usuario.nombre}"></c:out></span>.
		                 Hemos seleccionado estos productos de acuerdo con tus preferencias. <br>
		                Esperamos que te gusten...
		            </p>
	            </c:when>
	            <c:otherwise>
	            	<p id="membrete" class="p-4 bg-light">
		                Bienvenido. Esperamos que te interesen nuestros productos. Si es así no dudes en 
		                contactarnos.
		            </p>
	            </c:otherwise>
	        </c:choose>
	            
            <c:if test="${flash != null}">
				<div class="alert alert-danger">
					<p>
						<c:out value="${flash}" />						
					</p>
				</div>
			</c:if>
        </div>

        <div class="container text-center">
            <%-- <div class="table-responsive">
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
                    	<c:forEach items="${ productos }" var="producto">
	                        <tr>
	                            <td><c:out value="${ producto.nombre }"></c:out></td>
	                            <td style="text-transform: capitalize;"><c:out value="${ producto.tipoDeAtraccion }"></c:out></td>
	                            <td><c:out value="${ producto.costo }"></c:out></td>
	                            <td><img class="align-self-center" id="imagen-lista"
	                                    src="<c:out value="${ producto.urlImagen }"></c:out>"
	                                    class="d-block" alt="Imagen de <c:out value="${ producto.nombre }"></c:out>"/>
	                            </td>
	                            <td>
		                            <a href="detalle-atraccion?nombreProducto=${ producto.nombre }" class="btn btn-success rounded" role="button">Ver</a>
	                            </td>	                            
	                        </tr>
	                    </c:forEach>
                    </tbody>
                </table>
            </div> --%>
			<div class="row">
				<c:forEach items="${ productos }" var="producto">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="#"><img class="card-img-top " height="300"
								src="${ producto.urlImagen }" alt=""></a>
							<div class="card-body">
								<h4 class="card-title">
									<a class="text-decoration-none" href="detalle-atraccion?nombreProducto=${ producto.nombre }">${ producto.nombre }</a>
								</h4>
								<h5>${ producto.costo }</h5>
								<a href="/ttt_webapp/atracciones/detalle-atraccion?nombreProducto=${ producto.nombre }"
									class="btn btn-success rounded" role="button">Ver</a>
							</div>
							<div class="card-footer">
								<small class="text-muted">${ producto.tipoDeAtraccion }</small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
    </main>

    <footer>
        <jsp:include page="/partials/footer.jsp"></jsp:include>
    </footer>
</body>

</html>