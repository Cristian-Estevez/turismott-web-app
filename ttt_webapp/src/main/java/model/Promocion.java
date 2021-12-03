package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Promocion extends Producto {

	private List<Atraccion> atraccionesIncluidas;
	private double tiempoDeDuracion;
	protected double costo;

	public Promocion(int numeroId, String nombre, TipoDeAtraccion tipoDeAtraccion, 
			String descripcion, String urlImagen, List<Atraccion> atraccionesIncluidas) {
		super(numeroId, nombre, tipoDeAtraccion, descripcion, urlImagen);
		this.atraccionesIncluidas = atraccionesIncluidas;
		this.setTiempoDeDuracion();
	}

	private void setTiempoDeDuracion() {
		for (Atraccion unaAtraccion : atraccionesIncluidas) {
			tiempoDeDuracion += unaAtraccion.getTiempoDeDuracion();
		}
	}

	public double getTiempoDeDuracion() {
		return tiempoDeDuracion;
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	protected void ocuparPlaza() {
		if (this.getLugaresDisponibles() > 0) {
			for (Atraccion unaAtraccion : atraccionesIncluidas) {
				unaAtraccion.ocuparPlaza();
			}
		}
	}

	@Override
	public int getLugaresDisponibles() {
		int lugaresDisponibles = 0;
		int contador = 0;
		for (Atraccion unaAtraccion : atraccionesIncluidas) {
			if (contador == 0) {
				lugaresDisponibles = unaAtraccion.getLugaresDisponibles();
				contador++;
			}
			if (lugaresDisponibles > unaAtraccion.getLugaresDisponibles()) {
				lugaresDisponibles = unaAtraccion.getLugaresDisponibles();
			}
		}
		return lugaresDisponibles;
	}

	public ArrayList<Atraccion> getAtraccionesIncluidas() {
		return (ArrayList<Atraccion>) atraccionesIncluidas;
	}

	@Override
	public boolean incluye(Producto producto) {
		for (Producto unProducto : atraccionesIncluidas) {
			if (producto.incluye(unProducto)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(atraccionesIncluidas);
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
		Promocion other = (Promocion) obj;
		return Objects.equals(atraccionesIncluidas, other.atraccionesIncluidas);
	}

}
