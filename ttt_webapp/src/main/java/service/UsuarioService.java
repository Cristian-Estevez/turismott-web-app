package service;

import java.util.ArrayList;

import model.Producto;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;

public class UsuarioService {

	public Usuario getUsuarioPorNombre(String nombre) {
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.encontrarUsuarioPorNombre(nombre);
		return usuario;
	}
}
