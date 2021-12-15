<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="nombre" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="nombre" name="nombre"
		required value="${tmp_usuario.nombre}">
</div>
<div class="mb-3">
	<label for="monedasDeOro"
		class='col-form-label ${tmp_usuario.errores.get("monedasDeOro") != null ? "is-invalid" : "" }'>Monedas:</label>
	<input class="form-control" type="number" id="monedasDeOro" name="monedasDeOro"
		required value="${tmp_usuario.monedasDeOro}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_usuario.errores.get("monedasDeOro")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="tiempoDisponible"
		class='col-form-label ${tmp_usuario.errores.get("tiempoDisponible") != null ? "is-invalid" : "" }'>Tiempo:</label>
	<input class="form-control" type="number" id="tiempoDisponible" name="tiempoDisponible"
		required value="${tmp_usuario.tiempoDisponible}"></input>
	<div class="invalid-feedback">
		<c:out value='${tmp_usuario.errores.get("tiempoDisponible")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="tipo-de-atraccion" class='col-form-label ${tmp_usuario.errores.get("tipoDeAtraccion") != null ? "is-invalid" : "" }'>Tipo De Atracción:</label>
		<select class="form-control" id="tipo-de-atraccion" name="tipoDeAtraccion">
			<c:forEach items="${tiposDeAtraccion}" var="unTipo">
				<option value="${unTipo}"><c:out value="${unTipo}"></c:out></option>
			</c:forEach>
		</select>
	<div class="invalid-feedback">
		<c:out value='${tmp_usuario.errores.get("tipoDeAtraccion")}'></c:out>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>