package service;

import model.Usuario;
import model.nullobjects.NullUser;
import persistence.UsuarioDAO;

public class LoginService {

	public Usuario login(String nombreUsuario) {
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.encontrarUsuarioPorNombre(nombreUsuario);
		
		if (usuario.isNull()) {
			usuario = NullUser.build();
		}
		return usuario;
	}
}
