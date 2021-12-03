package model;

import java.util.Objects;

public abstract class Producto {
	
	private int numeroId;
	private String nombre;
	private double costo;
	private double tiempoDeDuracion;
	private TipoDeAtraccion tipoDeAtraccion;
	private String descripcion;
	private String urlImagen;
	
	public Producto(int numeroId, String nombre, double costo, 
			double tiempoDeDuracion, TipoDeAtraccion tipoDeAtraccion, 
			String descripcion, String urlImagen) {
		this.numeroId = numeroId;
		this.nombre = nombre;
		this.costo = costo;
		this.tiempoDeDuracion = tiempoDeDuracion;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;
		
	}
	
	public int getId() { return numeroId; }
	
	public double getCosto() { return costo; }
	
	public void setCosto(double costo) { this.costo = costo; }
	
	public double getTiempoDeDuracion() { return tiempoDeDuracion; }
	
	public TipoDeAtraccion getTipoDeAtraccion() { return tipoDeAtraccion; }
	
	public String getNombre() { return nombre; }
	
	public abstract boolean esPromocion();
	
	protected abstract void ocuparPlaza();
	
	public abstract int getLugaresDisponibles();
	
	public abstract boolean incluye(Producto producto);

	@Override
	public int hashCode() {
		return Objects.hash(costo, descripcion, nombre, numeroId, tiempoDeDuracion, tipoDeAtraccion, urlImagen);
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
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(nombre, other.nombre)
				&& numeroId == other.numeroId
				&& Double.doubleToLongBits(tiempoDeDuracion) == Double.doubleToLongBits(other.tiempoDeDuracion)
				&& tipoDeAtraccion == other.tipoDeAtraccion && Objects.equals(urlImagen, other.urlImagen);
	}	
}
