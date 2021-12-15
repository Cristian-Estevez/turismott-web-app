package service;

import java.util.ArrayList;

import model.TipoDeAtraccion;
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

	public void actualizarUsuario(String nombre, double monedasDeOro, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionFavorita) {
		Usuario usuarioAEditar = this.getUsuarioPorNombre(nombre);
		
		usuarioAEditar.setMonedasDeOro(monedasDeOro);
		usuarioAEditar.setTiempoDisponible(tiempoDisponible);
		usuarioAEditar.setTipoAtraccionFavorita(tipoDeAtraccionFavorita);
		
		UsuarioDAO uDAO = new UsuarioDAO();
		uDAO.actualizarUsuario(usuarioAEditar);
	}
}
