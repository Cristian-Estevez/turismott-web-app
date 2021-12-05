package persistence;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import model.Atraccion;
import model.Promocion;

public class PromocionDAOTest {

	@Test
	public void creaPromocionesCorrectamenteDesdeBBDD() {
		AtraccionDAO aDAO = new AtraccionDAO(); // ye está testeado
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		PromocionDAO pDAO = new PromocionDAO();
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		for (Promocion p : promociones) {
			assertNotNull(p);
		}
		
	}

}
