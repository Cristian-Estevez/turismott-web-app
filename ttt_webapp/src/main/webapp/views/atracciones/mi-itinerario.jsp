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
            <h2 id="titulo" class="es-logo my-5">Hola <span style="text-transform:capitalize;"><c:out value="${usuario.nombre}"></c:out></span>.</h2>
            
            <c:choose>
            	<c:when test="${ itinerario != null }">
		            <p id="membrete" class="p-4 bg-light">		                
		                Estos son los productos que compraste hasta el momento.
		            </p>
	            </c:when>
	            <c:otherwise>
	            	<p id="membrete" class="p-4 bg-light">
		                Aquí veras los productos de tu itinerario una vez que los hayas comprado
		            </p>
	            </c:otherwise>
	        </c:choose>	            
        </div>

        <div class="container text-center">
            <div class="table-responsive">
            	<c:if test="${itinerario != null}">									
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
	                    	<c:forEach items="${ itinerario }" var="producto">
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
                </c:if>
            </div>
        </div>
        <div id="contenedor-form" class="container my-5 text-center p-5">			
			<form class="row m-2 pt-2" >
				<div class="col">				
					<button type="submit" onclick="window.history.go(-1); return false;"
						class="form-control btn btn-success border border-dark">Volver</button>
				</div>
			</form>
		</div>
    </main>

    <footer>
        <jsp:include page="/partials/footer.jsp"></jsp:include>
    </footer>
</body>

</html>