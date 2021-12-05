package persistence;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

import model.Atraccion;

public class AtraccionDAOTest {

	@Test
	public void cargaAtraccionesDeBBDD() {
		AtraccionDAO aDAO = new AtraccionDAO();
		ArrayList<Atraccion> atracciones = aDAO.getAll();
//		for (Atraccion att : atracciones) {
//			System.out.println(att.getNombre() + " " + att.getTipoDeAtraccion());
//		}
		assertNotNull(atracciones);
	}

}
