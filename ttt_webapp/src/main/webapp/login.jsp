<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="partials/head.jsp"></jsp:include>


<link rel="stylesheet" href="assets/css/carousel.css">
<link rel="stylesheet" href="assets/css/login-form.css">
<link rel="stylesheet" href="assets/css/main-general.css">

</head>
<body>

	<main>
		<div class="container-fluid text-center">
			<h1 id="titulo" class="es-logo">Turismo Tierra Media</h1>
		</div>

		<div class="container-fluid">
			<div class="text-center">
				<h5 id="subtitulo" class="es-logo">
					<p>Te damos la bienvenida a tu próxima aventura</p>
					</h4>
			</div>
		</div>


		<div id="carousel" class="carousel slide py-4" data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carousel"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carousel"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carousel"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img id="imagen-carousel"
						src="https://static.wikia.nocookie.net/eldragonverde/images/6/6a/Mordor.png/revision/latest/scale-to-width-down/683?cb=20120129215409&path-prefix=es"
						class="d-block" alt="Mordor">
				</div>
				<div class="carousel-item">
					<img id="imagen-carousel"
						src="https://upload.wikimedia.org/wikipedia/en/3/3f/Dwarrodelf.jpg"
						class="d-block    " alt="Moria">
				</div>
				<div class="carousel-item">
					<img id="imagen-carousel"
						src="https://static.wikia.nocookie.net/middle-earth-roleplay/images/c/cf/Lorien.jpg/revision/latest/scale-to-width-down/1000?cb=20161005001852"
						class="d-block " alt="Lothlorien">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carousel" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>

			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carousel" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></i></span>

			</button>
		</div>

		<div id="contenedor-form" class="container my-5 text-center p-5">
			<form class="row m-2 pb-2">
				<div class="col">
					<select class="form-control px-5 border border-dark"
						id="menu-nombres">
						<option selected>Quien eres....? (click para elegir
							usuario)</option>
						<option value="1">Gandalf</option>
						<option value="2">Sam</option>
						<option value="3">Frodo</option>
					</select>
				</div>
				<div class="col">
					<button type="submit"
						class="form-control btn btn-secondary border border-dark">Ingresar</button>
				</div>

			</form>

			<form class="row m-2 pt-2" action="">
				<div class="col">
					<button type="submit"
						class="form-control btn btn-secondary border border-dark">Ingresar
						sin Identificarse</button>
				</div>
			</form>
		</div>


	</main>
	
	<footer>
		<jsp:include page="partials/footer.jsp"></jsp:include>
	</footer>
</body>
</html>