package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

	private int usuarioId;
	private String nombre;
	private double monedasDeOro;
	private double tiempoDisponible;
	private TipoDeAtraccion tipoAtraccionFavorita;
	private List<Producto> productos = new ArrayList<Producto>();
	private boolean esAdmin;

	
	public Usuario(int usuarioId, String nombre, double monedasDeOro, double tiempoDisponible,
			TipoDeAtraccion tipoAtraccionFavorita, boolean esAdmin) {
		this.usuarioId = usuarioId;
		this.nombre = nombre;
		this.monedasDeOro = monedasDeOro;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionFavorita = tipoAtraccionFavorita;
	}
	
	
	public void comprarProducto(Producto producto) {
		descontarMonedasDeOro(producto.getCosto());
		descontarTiempoDisponible(producto.getTiempoDeDuracion());
		productos.add(producto);
		producto.ocuparPlaza();
	}

	private void descontarTiempoDisponible(double tiempoADescontar) {
		tiempoDisponible -= tiempoADescontar;
	}

	private void descontarMonedasDeOro(double monedasADescontar) {
		monedasDeOro -= monedasADescontar;
	}
	
	public boolean yaCompro(Producto otroProducto) {
		for (Producto unProducto : productos) {
			if (unProducto.incluye(otroProducto)) {
				return true;
			}
		}
		return false;
	}
	
	public double getTiempoDisponible() { return tiempoDisponible; }
	
	public String getNombre() { return nombre; }
	
	public int getId() { return usuarioId; }
	
	public double getMonedasDeOro() { return monedasDeOro; }
	
	public List<Producto> getProductosComprados() { return productos; }
	
	public TipoDeAtraccion getTipoAtraccionFavorita() { return tipoAtraccionFavorita; }
	
	public boolean getEsAdmin() { return esAdmin; }

	@Override
	public int hashCode() {
		return Objects.hash(esAdmin, monedasDeOro, nombre, productos, tiempoDisponible, tipoAtraccionFavorita,
				usuarioId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return esAdmin == other.esAdmin
				&& Double.doubleToLongBits(monedasDeOro) == Double.doubleToLongBits(other.monedasDeOro)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(productos, other.productos)
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible)
				&& tipoAtraccionFavorita == other.tipoAtraccionFavorita && usuarioId == other.usuarioId;
	}

}
