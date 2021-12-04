package persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import model.Usuario;

public class UsuarioDAOTest {


	@Test
	public void recuperaUsuarioDeBBDD() {
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario gandalf = uDAO.encontrarUsuarioPorNombre("gandalf");
		assertNotNull(gandalf);
	}
}
