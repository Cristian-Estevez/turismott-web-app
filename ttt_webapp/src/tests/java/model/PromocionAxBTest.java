package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionAxBTest {

	Atraccion 	minasDeTirith, abismoDeHelm, erebor, laComarca, 
	lothlorien, delicatessen, cataratasDeChocolate;

	List<Atraccion> listaDeAtraccionesA, listaDeAtraccionesB;
	PromocionAxB packPaisajes, packDegustacion;
	
	@Before
	public void setup() {
		
		minasDeTirith = new Atraccion(2, "Minas de Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE, null, null, false);
		abismoDeHelm = new Atraccion(5, "Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE, null, null, false);
		erebor = new Atraccion(7, "Erebor", 12, 3, 32, TipoDeAtraccion.PAISAJE, null, null, false);
		
		lothlorien = new Atraccion(6, "Lothlórien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION, null, null, false);
		laComarca = new Atraccion(3, "La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION, null, null, false);
		delicatessen = new Atraccion(0, "Una con poco cupo", 3, 1, 2, TipoDeAtraccion.DEGUSTACION, null, null, false);
		cataratasDeChocolate = new Atraccion(0, "Cataratas de Chocolate", 13, 3, 10, TipoDeAtraccion.DEGUSTACION, null, null, false);

		
		listaDeAtraccionesA = new ArrayList<Atraccion>();
		listaDeAtraccionesA.add(minasDeTirith);
		listaDeAtraccionesA.add(erebor);
		listaDeAtraccionesA.add(abismoDeHelm);
		
		listaDeAtraccionesB = new ArrayList<Atraccion>();
		listaDeAtraccionesB.add(lothlorien);
		listaDeAtraccionesB.add(laComarca);
		listaDeAtraccionesB.add(delicatessen);
		listaDeAtraccionesB.add(cataratasDeChocolate);
		
		packDegustacion = new PromocionAxB(4,"Pack Degustacion", TipoDeAtraccion.DEGUSTACION, null, null, listaDeAtraccionesB, false);
		
		packPaisajes = new PromocionAxB(5,"Pack Paisajes", TipoDeAtraccion.PAISAJE, null, null, listaDeAtraccionesA, false);
	}
	
	@Test
	public void alConstruirseCalculaElValorCorrectamente() {
		double valorEsperado = minasDeTirith.getCosto() + erebor.getCosto();
		assertEquals(valorEsperado, packPaisajes.getCosto(), 0);
	}
	
	@Test 
	public void alConstruirseCalculaElValorCorrectamentePruebaDos() {
		double valorSinDescuentoAxB = 54;
		double costoDeLaUltimaAtraccion = 13;
		assertEquals((valorSinDescuentoAxB - costoDeLaUltimaAtraccion), packDegustacion.getCosto(), 0);
	}
}
