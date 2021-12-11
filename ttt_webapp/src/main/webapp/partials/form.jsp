<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="nombre" class="col-form-label">Nombre:</label> 
	<input type="text" class="form-control" id="nombre" name="nombre" required value="${atraccion.nombre}">
</div>
<div class="mb-3">
	<label for="costo" class='col-form-label ${atraccion.errores.get("costo") != null ? "is-invalid" : "" }'>Costo:</label>
	<input class="form-control" type="number" id="costo" name="costo" required value="${atraccion.costo}"></input>
	<div class="invalid-feedback">
		<c:out value='${atraccion.errores.get("costo")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="duracion" class='col-form-label ${atraccion.errores.get("duracion") != null ? "is-invalid" : "" }'>Duración:</label>
	<input class="form-control" type="number" id="duracion" name="duracion"
		required value="${atraccion.tiempoDeDuracion}"></input>
	<div class="invalid-feedback">
		<c:out value='${atraccion.errores.get("duracion")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="cupo" class='col-form-label ${atraccion.errores.get("cupo") != null ? "is-invalid" : "" }'>Capacidad:</label>
	<input class="form-control" type="number" id="cupo" name="cupo"
		required value="${atraccion.cupo}"></input>
	<div class="invalid-feedback">
		<c:out value='${atraccion.errores.get("cupo")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="tipo-de-atraccion" class='col-form-label ${atraccion.errores.get("tipoDeAtraccion") != null ? "is-invalid" : "" }'>Tipo De Atracción:</label>
		<select class="form-control" id="tipo-de-atraccion" name="tipoDeAtraccion">
			<c:forEach items="${tiposDeAtraccion}" var="unTipo">
				<option value="${unTipo}"><c:out value="${unTipo}"></c:out></option>
			</c:forEach>
		</select>
	<div class="invalid-feedback">
		<c:out value='${atraccion.errores.get("tipoDeAtraccion")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="descripcion" class='col-form-label ${atraccion.errores.get("descripcion") != null ? "is-invalid" : "" }'>Descripción:</label>
	<textarea class="form-control" id="descripcion" name="descripcion" required >${atraccion.descripcion}</textarea>
	<div class="invalid-feedback">
		<c:out value='${atraccion.errores.get("descripcion")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="url-imagen" class='col-form-label ${atraccion.errores.get("urlImagen") != null ? "is-invalid" : "" }'>URL de la Imágen:</label>
	<input class="form-control" type="text" id="url-imagen" name="urlImagen"
		required value="${atraccion.urlImagen}"></input>
	<div class="invalid-feedback">
		<c:out value='${atraccion.errores.get("urlImagen")}'></c:out>
	</div>
</div>
<div>
	<button type="submit" class="btn btn-success">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
