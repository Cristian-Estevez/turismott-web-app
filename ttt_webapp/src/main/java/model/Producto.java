package model;

import java.util.Objects;

public abstract class Producto {
	
	private int numeroId;
	private String nombre;
	private TipoDeAtraccion tipoDeAtraccion;
	private String descripcion;
	private String urlImagen;
	private double costo;
	
	public Producto(int numeroId, String nombre, 
			TipoDeAtraccion tipoDeAtraccion, 
			String descripcion, String urlImagen) {
		this.numeroId = numeroId;
		this.nombre = nombre;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;
		
	}
	
	public double getCosto() { return costo; }
	
	public int getId() { return numeroId; }
	
	public TipoDeAtraccion getTipoDeAtraccion() { return tipoDeAtraccion; }
	
	public String getNombre() { return nombre; }
	
	public abstract boolean esPromocion();
	
	protected abstract void ocuparPlaza();
	
	public abstract int getLugaresDisponibles();
	
	public abstract boolean incluye(Producto producto);
	
	public abstract double getTiempoDeDuracion();
	
	public String getUrlImagen() { return urlImagen; }

	public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }
	
	public boolean tieneLugar() { return getLugaresDisponibles() > 0; }

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, nombre, numeroId, tipoDeAtraccion, urlImagen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(nombre, other.nombre)
				&& numeroId == other.numeroId && tipoDeAtraccion == other.tipoDeAtraccion
				&& Objects.equals(urlImagen, other.urlImagen);
	}

}
