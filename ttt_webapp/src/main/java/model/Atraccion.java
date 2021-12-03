package model;

import java.util.Objects;

public class Atraccion extends Producto {

	private int cupo;
	private double tiempoDeDuracion;

	public Atraccion(int numeroId, String nombre, double costo, double tiempoDeDuracion,
			int cupo, TipoDeAtraccion tipoDeAtraccion, String descripcion, String urlImagen) {
		super(numeroId, nombre, costo, tipoDeAtraccion, descripcion, urlImagen);
		this.cupo = cupo;
		this.tiempoDeDuracion = tiempoDeDuracion; 
	}

	public double getTiempoDeDuracion() { return tiempoDeDuracion; }
	
	@Override
	public boolean esPromocion() { return false; }

	@Override
	protected void ocuparPlaza() { cupo--; }

	@Override
	public int getLugaresDisponibles() { return cupo; }

	@Override
	public boolean incluye(Producto producto) {
		if (producto.esPromocion()) {
			return producto.incluye(this);
		}
		return this.equals(producto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cupo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return cupo == other.cupo;
	}
	
}
