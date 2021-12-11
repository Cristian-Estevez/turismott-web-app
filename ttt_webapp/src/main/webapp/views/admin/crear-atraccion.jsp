<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/partials/head.jsp"></jsp:include>
	<link rel="stylesheet" href="assets/css/login-form.css">
</head>
<body>
	<header>
        <jsp:include page="/partials/nav-bar.jsp"></jsp:include>
		<jsp:include page="/partials/botonera-admin.jsp"></jsp:include>
    </header>
    
    <main>
    	<div class="container-fluid text-center">
    		<h2 id="titulo" class="es-logo my-5">Crear / Editar Atracción</h2>
    	
    		<div id="contenedor-form" class="container my-5 text-center p-5">
		    	<form class="row m-2 pb-2" action="/ttt_webapp/admin/crear-atraccion.ad" method="post">
					<jsp:include page="/partials/form.jsp"></jsp:include>
				</form>
			</div>
		</div>
    </main>
    
    <footer>
        <jsp:include page="/partials/footer.jsp"></jsp:include>
    </footer>
</body>
</html>