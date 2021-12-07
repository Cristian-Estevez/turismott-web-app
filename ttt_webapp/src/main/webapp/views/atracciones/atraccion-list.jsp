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
		<jsp:include page="/partials/barra-estado-usuario.jsp"></jsp:include>

    </header>

    <main>
        <div class="container-fluid text-center">
            <h2 id="titulo" class="es-logo my-5">Nuestros Productos</h2>
            <p id="membrete" class="p-4 bg-light">
                Hemos seleccionado estos productos de acuerdo con tus preferencias. <br>
                Esperamos que te gusten...
            </p>
            <c:if test="${flash != null}">
				<div class="alert alert-danger">
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
                            <th scope="col">Atraccion</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Imagen</th>
                            <th scope="col">Acción</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${ productos }" var="producto">
	                        <tr>
	                            <td><a href="#" class="rowlink text-decoration-none text-light"><c:out value="${ producto.nombre }"></a></c:out></td>
	                            <td style="text-transform: capitalize;"><c:out value="${ producto.tipoDeAtraccion }"></c:out></td>
	                            <td><c:out value="${ producto.costo }"></c:out></td>
	                            <td><img class="align-self-center" id="imagen-lista"
	                                    src="<c:out value="${ producto.urlImagen }"></c:out>"
	                                    class="d-block" alt="Imagen de <c:out value="${ producto.nombre }"></c:out>"/>
	                            </td>
	                            <td>
	                            <c:choose>
									<c:when
										test="${usuario.puedeComprar(producto) && usuario.puedeAsistir(producto) && producto.tieneLugar()}">
										<a href="comprar.do?nombreProducto=${producto.nombre}&nombreUsuario=${usuario.nombre}"
											class="btn btn-success rounded" role="button">Comprar</a>
									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-secondary rounded disabled"
											role="button">Comprar</a>
									</c:otherwise>
								</c:choose>
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