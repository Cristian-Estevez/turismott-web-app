package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionAbsolutaTest {

	Usuario eowyn, gandalf, sam, galadriel, userAdineradoYConMuchoTiempo;

	Atraccion moria, minasDeTirith, laComarca, mordor, abismoDeHelm, lothlorien, erebor, bosqueNegro;

	List<Atraccion> listaDeAtraccionesA, listaDeAtraccionesB;
	PromocionAbsoluta packDegustacion;

	@Before
	public void setup() {
		eowyn = new Usuario(1,"Eowyn", 10, 8, TipoDeAtraccion.AVENTURA, false);
		gandalf = new Usuario(2,"Gandalf", 100, 5, TipoDeAtraccion.PAISAJE, false);
		sam = new Usuario(3,"Sam", 36, 8, TipoDeAtraccion.DEGUSTACION, false);
		galadriel = new Usuario(4,"Galadriel", 120, 4, TipoDeAtraccion.PAISAJE, false);
		userAdineradoYConMuchoTiempo = new Usuario(0,"Adinerado y con tiempo", 500, 24, TipoDeAtraccion.AVENTURA, false);
		
		laComarca = new Atraccion(3, "La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION, null, null, false);
		lothlorien = new Atraccion(6, "Lothlórien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION, null, null, false);
		
		listaDeAtraccionesA = new ArrayList<Atraccion>();
		listaDeAtraccionesA.add(laComarca);
		listaDeAtraccionesA.add(lothlorien);
		
		packDegustacion = new PromocionAbsoluta(6,"Pack Degustación", 36, TipoDeAtraccion.DEGUSTACION, "Una Promo de Degustacion", "urlImagen", listaDeAtraccionesA, false);
	}
	
	@Test
	public void devuelveTiempoDeDuracion() {
		double tiempoEsperado = laComarca.getTiempoDeDuracion() + lothlorien.getTiempoDeDuracion();
		assertEquals(tiempoEsperado, packDegustacion.getTiempoDeDuracion(), 0);
	}
	
	@Test
	public void devuelveLugaresDisponiblesCorrectamente() {
		int lugaresEsperados = 30;
		assertEquals(lugaresEsperados, packDegustacion.getLugaresDisponibles());
	}
	
	@Test
	public void reduceCuposDisponiblesAlComprar() {
		int lugaresDespuesDeUnaCompra = packDegustacion.getLugaresDisponibles() - 1;
		userAdineradoYConMuchoTiempo.comprarProducto(packDegustacion);
		assertEquals(lugaresDespuesDeUnaCompra, packDegustacion.getLugaresDisponibles());
	}
	
	@Test
	public void lugaresDisponiblesReducenAlComprarPromoYAtraccionPorSeparado() {
		int lugaresDespuesDeDosCompras = packDegustacion.getLugaresDisponibles() - 2;
		userAdineradoYConMuchoTiempo.comprarProducto(packDegustacion);
		sam.comprarProducto(lothlorien);
		assertEquals(lugaresDespuesDeDosCompras, packDegustacion.getLugaresDisponibles());
	}
}
