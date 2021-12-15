package model;

import java.util.List;

public class PromocionPorcentual extends Promocion {

	public PromocionPorcentual(int numeroId, String nombre, TipoDeAtraccion tipoDeAtraccion,
			double porcentajeDescuento,	String descripcion, String urlImagen, 
			List<Atraccion> atraccionesIncluidas, boolean borrado) {
		super(numeroId, nombre, tipoDeAtraccion, descripcion, urlImagen, atraccionesIncluidas, borrado);
		this.setCosto(porcentajeDescuento);		
	}
	
	private void setCosto(double porcentajeDescuento) {
		List<Atraccion> atracciones = getAtraccionesIncluidas();
		double costoReal = 0;
		for (Atraccion unaAtraccion : atracciones) {
			costoReal += unaAtraccion.getCosto();
		}
		double descuentoTotal = (porcentajeDescuento * costoReal) / 100;
		costo = costoReal - descuentoTotal;
	}
	
	@Override
	public double getCosto() { return costo; }

	@Override
	public int hashCode() {	return super.hashCode(); }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
}
