package service;

import java.util.ArrayList;

import model.Usuario;
import persistence.UsuarioDAO;

public class UsuarioService {

	public Usuario getUsuarioPorNombre(String nombre) {
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.encontrarUsuarioPorNombre(nombre);
		return usuario;
	}

	public ArrayList<Usuario> getAllNonDeleted() {
		UsuarioDAO uDAO = new UsuarioDAO();
		return uDAO.getAllNonDeleted();
	}
}
