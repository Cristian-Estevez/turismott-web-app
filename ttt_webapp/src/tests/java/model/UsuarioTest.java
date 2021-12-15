package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	Usuario gandalf, userAdineradoYconMuchoTiempo;
	Atraccion mordor, moria, bosqueNegro;
	List<Producto> productosEsperados;
	
	@Before
	public void setup() {
		gandalf = new Usuario(1, "gandalf", 100, 5, TipoDeAtraccion.PAISAJE, false);
		userAdineradoYconMuchoTiempo = new Usuario(2, "Ricky Fort", 1000, 5000, TipoDeAtraccion.PAISAJE, false);
		
		mordor = new Atraccion(1, "Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA, "Un lugar para aventurarse", "urlDeUnaFoto", false);
		moria = new Atraccion(1, "Moria", 10, 2, 6,  TipoDeAtraccion.AVENTURA, "Aventuras en moria", "urlImg", false);
		bosqueNegro = new Atraccion(8, "Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA, "Aventuras en Bosque Negro", "urlImg", false);
		
		productosEsperados = new ArrayList<Producto>();
		
	}
	
	@Test
	public void compraProductoYSeGuarda() {
		gandalf.comprarProducto(mordor);
		productosEsperados.add(mordor);
		assertEquals(productosEsperados, gandalf.getProductosComprados());
	}
	
	@Test
	public void descuentaMonedasDeOroCorrectamente() {
		double monedasEsperadas = gandalf.getMonedasDeOro() - mordor.getCosto();
		gandalf.comprarProducto(mordor);
		assertEquals(monedasEsperadas, gandalf.getMonedasDeOro(), 0);
	}

	@Test
	public void devuelveAtraccionFavoritaCorrectamente() {
		TipoDeAtraccion atraccionEsperada = TipoDeAtraccion.PAISAJE;
		assertEquals(atraccionEsperada, gandalf.getTipoAtraccionFavorita());
	}
	
	@Test
	public void descuentaCorrectamenteElTiempoDisponibleAlComprar() {
		double tiempoEsperado = gandalf.getTiempoDisponible() - mordor.getTiempoDeDuracion();
		gandalf.comprarProducto(mordor);
		assertEquals(tiempoEsperado, gandalf.getTiempoDisponible(), 0);
	}
	
	@Test
	public void corroborarSiYaComproAtraccion() {
		userAdineradoYconMuchoTiempo.comprarProducto(moria);
		userAdineradoYconMuchoTiempo.comprarProducto(mordor);
		assertTrue(userAdineradoYconMuchoTiempo.yaCompro(moria));
		assertTrue(userAdineradoYconMuchoTiempo.yaCompro(moria));
		assertFalse(userAdineradoYconMuchoTiempo.yaCompro(bosqueNegro));
		
	}
}
