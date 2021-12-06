package service;

import model.Usuario;
import persistence.UsuarioDAO;

public class UsuarioService {

	public Usuario getUsuarioPorNombre(String nombre) {
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.encontrarUsuarioPorNombre(nombre);
		return usuario;
	}
}
