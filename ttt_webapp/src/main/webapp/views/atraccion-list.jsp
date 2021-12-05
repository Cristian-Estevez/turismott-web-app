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
        </div>

        <div class="container text-center">
            <div class="table-responsive">
                <table class="table table-dark table-striped table-hover">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Atraccion</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Imagen</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row"><a href="#" class="rowlink text-decoration-none text-light">1</a></th>
                            <td>Mordor</td>
                            <td>Aventura</td>
                            <td>56</td>
                            <td><img class="align-self-center" id="imagen-lista"
                                    src="https://static.wikia.nocookie.net/eldragonverde/images/6/6a/Mordor.png/revision/latest/scale-to-width-down/683?cb=20120129215409&path-prefix=es"
                                    class="d-block" alt="Mordor">
                            </td>
                        </tr>
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