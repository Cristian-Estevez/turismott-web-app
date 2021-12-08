<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
	<div class="container-fluid">
		<a class="navbar-brand es-logo" href="atraccion-list.jsp">Tierra Media</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="#">Productos</a></li>
				
				<c:choose>
					<c:when test="${usuario != null}">
						<li class="nav-item"><a class="nav-link">Mi itinerario</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a href="/ttt_webapp/login.jsp" class="nav-link">Ingresar</a></li>
					</c:otherwise>
				</c:choose>
								
				<li class="nav-item"><a class="nav-link" href="#contacto">Contacto</a></li>
				<c:choose>
					<c:when test="${usuario != null}">
						<li class="nav-item"><a class="nav-link" href="/ttt_webapp/logout">Salir</a></li>
					</c:when>
				</c:choose>
								
			</ul>
		</div>
	</div>
</nav>