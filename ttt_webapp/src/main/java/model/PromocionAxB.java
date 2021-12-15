package model;

import java.util.List;
import java.util.ListIterator;

public class PromocionAxB extends Promocion {

	/**
	 * Esta no recibe costo como parámetro, debe calcularlo de acuerdo a las atracciones que recibe,
	 *  la ultima de la lista no se paga
	 * @param numeroId
	 * @param nombre
	 */
	public PromocionAxB(int numeroId, String nombre, TipoDeAtraccion tipoDeAtraccion,
			String descripcion, String urlImagen, List<Atraccion> atraccionesIncluidas, boolean borrado) {
		super(numeroId, nombre, tipoDeAtraccion, descripcion, urlImagen, atraccionesIncluidas, borrado);
		this.setCosto();
	}
	
	/**
	 * Suma todas las atracciones de la promo menos la última 
	 * TODO asegurarse de que la gratuita esté última en el listado 
	 */
	private void setCosto() {
		ListIterator<Atraccion> iter = this.getAtraccionesIncluidas()
				.listIterator(this.getAtraccionesIncluidas().size() - 1);
		
		while (iter.hasPrevious()) {
			costo += iter.previous().getCosto();
		}
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
