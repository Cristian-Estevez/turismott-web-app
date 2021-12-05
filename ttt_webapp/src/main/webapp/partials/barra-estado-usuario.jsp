<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="ps-2 bg-secondary text-light row p-2 text-center">
	<div class="col-12 col-sm-4" style="text-transform: capitalize;">Bievenido <c:out value="${ usuario.nombre }"></c:out></div>
	<div class="col-12 col-sm-4">Monedas de oro: <c:out value="${ usuario.monedasDeOro }"></c:out></div>
	<div class="col-12 col-sm-4">Tiempo disponible: <c:out value="${ usuario.tiempoDisponible }"></c:out> hs.</div>
</div>