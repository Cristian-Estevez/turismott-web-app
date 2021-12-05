package model;

import java.util.List;

public class PromocionAbsoluta  extends Promocion{

	public PromocionAbsoluta(int numeroId, String nombre, double costo, 
			TipoDeAtraccion tipoDeAtraccion, String descripcion, 
			String urlImagen, List<Atraccion> atraccionesIncluidas) {
		super(numeroId, nombre, tipoDeAtraccion, descripcion, urlImagen, atraccionesIncluidas);
		this.setCosto(costo);
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	@Override
	public double getCosto() { return costo; }
}
