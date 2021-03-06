<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
	<div class="container-fluid">
	
		<c:choose> 
			<c:when test="${!usuario.esAdmin()}">
				<a class="navbar-brand es-logo" href="/ttt_webapp/login">Tierra Media</a>
			</c:when>
			<c:otherwise>
				<a class="navbar-brand es-logo" href="#" >Tierra Media</a>
			</c:otherwise>
		</c:choose>
		
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			
				<c:if test="${!usuario.esAdmin()}">
					<li class="nav-item"><a href="/ttt_webapp/login" class="nav-link" href="#">Productos</a></li>					
					
					<c:choose>
						<c:when test="${usuario != null}">
							<li class="nav-item"><a href="/ttt_webapp/atracciones/mi-itinerario.do" class="nav-link">Mi itinerario</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item"><a href="/ttt_webapp/login.jsp" class="nav-link">Ingresar</a></li>
						</c:otherwise>
					</c:choose>					
									
					<li class="nav-item"><a class="nav-link" href="#contacto">Contacto</a></li>
				</c:if>
				<c:choose>
					<c:when test="${usuario != null}">
						<li class="nav-item"><a class="nav-link" href="/ttt_webapp/logout">Salir</a></li>
					</c:when>
				</c:choose>
								
			</ul>
		</div>
	</div>
</nav>