package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AtraccionTest {

	Usuario eowyn, gandalf, sam, galadriel;
	Atraccion moria, minasDeTirith, laComarca, mordor, 
			  abismoDeHelm, lothlorien, erebor, bosqueNegro,
			  unaConPocoCupo;
	
	@Before
	public void setup() {
		eowyn = new Usuario(1,"Eowyn", 10, 8, TipoDeAtraccion.AVENTURA, false, false);
		gandalf = new Usuario(2,"Gandalf", 100, 5, TipoDeAtraccion.PAISAJE, false, false);
		sam = new Usuario(4,"Sam", 36, 8, TipoDeAtraccion.DEGUSTACION, false, false);
		galadriel = new Usuario(5,"Galadriel", 120, 4, TipoDeAtraccion.PAISAJE, false, false);
		
		moria = new Atraccion(1, "Moria", 10, 2, 6,  TipoDeAtraccion.AVENTURA, null, null, false);
		minasDeTirith = new Atraccion(2, "Minas de Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE, null, null, false);
		laComarca = new Atraccion(3, "La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION, null, null, false);
		mordor = new Atraccion(4, "Mordor", 25, 3, 4, TipoDeAtraccion.AVENTURA, null, null, false);
		abismoDeHelm = new Atraccion(5, "Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE, null, null, false);
		lothlorien = new Atraccion(6, "Lothlórien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION, null, null, false);
		erebor = new Atraccion(7, "Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE, null, null, false);
		bosqueNegro = new Atraccion(8, "Bosque Negro", 3, 4, 12, TipoDeAtraccion.AVENTURA, null, null, false);
		unaConPocoCupo = new Atraccion(0, "Una con poco cupo", 3, 1, 2, TipoDeAtraccion.DEGUSTACION, null, null, false);
	}
	
	@Test
	public void plazasDisponiblesDisminuyenAlComprarAtraccion() {
		int cupoDisponibleEsperado1 = moria.getLugaresDisponibles() - 1;
		int cupoDisponibleEsperado2 = moria.getLugaresDisponibles() - 3;
		eowyn.comprarProducto(moria);
		assertEquals(cupoDisponibleEsperado1, moria.getLugaresDisponibles());
		
		gandalf.comprarProducto(moria);
		sam.comprarProducto(moria);
		assertEquals(cupoDisponibleEsperado2, moria.getLugaresDisponibles());
	}

	@Test 
	public void unaVezOcupadosTodosLosCuposNoSigueDescontando() {
		int cupoEsperado1 = 1;
		int cupoEsperado2 = 0;
		eowyn.comprarProducto(unaConPocoCupo);
		assertEquals(cupoEsperado1, unaConPocoCupo.getLugaresDisponibles());
		gandalf.comprarProducto(unaConPocoCupo);
		assertEquals(cupoEsperado2, unaConPocoCupo.getLugaresDisponibles());
		sam.comprarProducto(unaConPocoCupo);
		assertEquals(cupoEsperado2, unaConPocoCupo.getLugaresDisponibles());
	}
}
