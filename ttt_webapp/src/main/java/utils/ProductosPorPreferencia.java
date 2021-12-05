package utils;

import java.util.Comparator;

import model.Producto;
import model.TipoDeAtraccion;

public class ProductosPorPreferencia implements Comparator<Producto> {

	private TipoDeAtraccion tipoFavorito;

	public ProductosPorPreferencia(TipoDeAtraccion tipoDeAtraccionPreferido) {
		this.tipoFavorito = tipoDeAtraccionPreferido;

	}

	@Override
	public int compare(Producto o1, Producto o2) {
		if (o1.getTipoDeAtraccion() == this.tipoFavorito && o2.getTipoDeAtraccion() == this.tipoFavorito) {
			// si las dos son del mismo tipo preferido, compara para ver cual es promocion
			return comparaAmbosFavoritosONingunoFavorito(o1, o2);

		} else if (o1.getTipoDeAtraccion() != this.tipoFavorito && o2.getTipoDeAtraccion() != this.tipoFavorito) {
			return comparaAmbosFavoritosONingunoFavorito(o1, o2);
		} else {
			// una es preferida y la otra no
			if (o1.getTipoDeAtraccion() == this.tipoFavorito)
				return -1;
			return 1;
		}
	}

	private int comparaAmbosFavoritosONingunoFavorito(Producto o1, Producto o2) {
		if (o1.esPromocion() && o2.esPromocion()) {
			// si las dos son promociones, compara por el costo
			return comparaPromociones(o1, o2);
		} else if (!o1.esPromocion() && !o2.esPromocion()) {
			return comparaPromociones(o1, o2);
		} else {
			return -Boolean.compare(o1.esPromocion(), o2.esPromocion());
		}
	}

	private int comparaPromociones(Producto o1, Producto o2) {
		if (Double.compare(o1.getCosto(), o2.getCosto()) == 0) {
			// si el costo es el mismo, compara por el tiempo
			return comparaPorTiempo(o1, o2);
		} else {
			return comparaPorCosto(o1, o2);
		}
	}

	private int comparaPorTiempo(Producto o1, Producto o2) {
		return -Double.compare(o1.getTiempoDeDuracion(), o2.getTiempoDeDuracion());
	}

	private int comparaPorCosto(Producto o1, Producto o2) {
		return -Double.compare(o1.getCosto(), o2.getCosto());
	}
}