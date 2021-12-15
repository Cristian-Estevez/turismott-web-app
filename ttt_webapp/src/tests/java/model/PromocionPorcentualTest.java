package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromocionPorcentualTest {

	Atraccion minasDeTirith, abismoDeHelm, laComarca, lothlorien, delicatessen, cataratasDeChocolate;

	List<Atraccion> listaDeAtraccionesA, listaDeAtraccionesB;
	PromocionPorcentual promoPaisajes, promoDegustacion;

	@Before
	public void setup() {
		
		minasDeTirith = new Atraccion(2, "Minas de Tirith", 5, 2.5, 25, TipoDeAtraccion.PAISAJE, null, null, false);
		abismoDeHelm = new Atraccion(5, "Abismo de Helm", 5, 2, 15, TipoDeAtraccion.PAISAJE, null, null, false);
		lothlorien = new Atraccion(6, "Lothlórien", 35, 1, 30, TipoDeAtraccion.DEGUSTACION, null, null, false);
		laComarca = new Atraccion(3, "La Comarca", 3, 6.5, 150, TipoDeAtraccion.DEGUSTACION, null, null, false);
		delicatessen = new Atraccion(0, "Una con poco cupo", 3, 1, 2, TipoDeAtraccion.DEGUSTACION, null, null, false);
		cataratasDeChocolate = new Atraccion(0, "Cataratas de Chocolate", 13, 3, 10, TipoDeAtraccion.DEGUSTACION, null, null, false);



		listaDeAtraccionesA = new ArrayList<Atraccion>();
		listaDeAtraccionesA.add(abismoDeHelm);
		listaDeAtraccionesA.add(minasDeTirith);
		
		listaDeAtraccionesB = new ArrayList<Atraccion>();
		listaDeAtraccionesB.add(lothlorien);
		listaDeAtraccionesB.add(laComarca);
		listaDeAtraccionesB.add(delicatessen);
		listaDeAtraccionesB.add(cataratasDeChocolate);
		
//		tipo Porcentual: Minas Tirith + Abismo de Helm 20% off = $ 8
		promoPaisajes = new PromocionPorcentual(1,"Promo Paisajes", TipoDeAtraccion.PAISAJE, 20,
				null, null, listaDeAtraccionesA, false);
		
		promoDegustacion = new PromocionPorcentual(2,"Promo Degustacion", TipoDeAtraccion.DEGUSTACION, 50, null, null, listaDeAtraccionesB, false);
	}
	
	@Test
	public void seAsignaElCostoConDescuentoCorrectamente() {
		double costoEsperado = 8;
		assertEquals(costoEsperado, promoPaisajes.getCosto(), 0);
	}
	
	@Test
	public void seAsignaElCostoConDescuentoCorrectamentePruebaDos() {
		double porcentajeAplicado = 0.5;
		double costoEsperado = (lothlorien.getCosto() + laComarca.getCosto() + delicatessen.getCosto() +
				cataratasDeChocolate.getCosto()) * porcentajeAplicado;
		assertEquals(costoEsperado, promoDegustacion.getCosto(), 0);	
	}
}
