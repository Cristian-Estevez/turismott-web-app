package model;

import java.util.HashMap;
import java.util.Objects;

public class Atraccion extends Producto {

	private int cupo;
	private double tiempoDeDuracion;
	private double costo;
	private HashMap<String, String> errores;

	public Atraccion(int numeroId, String nombre, double costo, double tiempoDeDuracion,
			int cupo, TipoDeAtraccion tipoDeAtraccion, String descripcion, String urlImagen, boolean borrado) {
		super(numeroId, nombre, tipoDeAtraccion, descripcion, urlImagen, borrado);
		this.cupo = cupo;
		this.tiempoDeDuracion = tiempoDeDuracion;
		this.setCosto(costo);
	}	
	
	public HashMap<String, String> getErrores() { return errores; }

	public double getTiempoDeDuracion() { return tiempoDeDuracion; }

	public double getCosto() { return costo; }
	
	public int getCupo() { return cupo; }
	
	public void setCosto(double costo) { this.costo = costo; }
	
	@Override
	public boolean esPromocion() { return false; }

	@Override
	protected void ocuparPlaza() { 
		if (this.getLugaresDisponibles() > 0) {
			cupo--;
		}
	}

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


	public boolean esValida() {
		validar();
		return errores.isEmpty();
	}


	private void validar() {
		errores = new HashMap<String, String>();
		
		if (costo < 0) { errores.put("costo", "No debe ser menor a 0"); }
		if (tiempoDeDuracion < 0) { errores.put("duracion", "No debe ser menor a 0"); }
		if (cupo < 0) { errores.put("cupo", "No debe ser menor a 0"); }
		
	}	
	
	public void setTiempoDeDuracion(double duracion) { this.tiempoDeDuracion = duracion; }

	public void setCupo(int cupo) { this.cupo = cupo; }

}
