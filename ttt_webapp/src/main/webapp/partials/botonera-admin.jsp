<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${usuario != null}">
		<div class="botonera-admin ps-2 bg-secondary text-light row p-2 text-center">
			<div class="col-12 col-sm-4"><a href="login" class="nav-link">Ver todos los productos</a></div>
			<div class="col-12 col-sm-4"><a href="" class="nav-link">Crear Atraccion</a></div>
			<div class="col-12 col-sm-4"><a href="" class="nav-link">Crear Promocion</a></div>
		</div>
	</c:when>
</c:choose>