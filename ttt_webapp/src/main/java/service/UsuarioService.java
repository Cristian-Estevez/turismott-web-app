package service;

import java.util.ArrayList;

import model.TipoDeAtraccion;
import model.Usuario;
import persistence.UsuarioDAO;

public class UsuarioService {
	
	private UsuarioDAO uDAO = new UsuarioDAO();

	public Usuario getUsuarioPorNombre(String nombre) {
		
		Usuario usuario = uDAO.encontrarUsuarioPorNombre(nombre);
		return usuario;
	}

	public ArrayList<Usuario> getAllNonDeleted() {
		return uDAO.getAllNonDeleted();
	}

	public void actualizarUsuario(String nombre, double monedasDeOro, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionFavorita) {
		Usuario usuarioAEditar = this.getUsuarioPorNombre(nombre);
		
		usuarioAEditar.setMonedasDeOro(monedasDeOro);
		usuarioAEditar.setTiempoDisponible(tiempoDisponible);
		usuarioAEditar.setTipoAtraccionFavorita(tipoDeAtraccionFavorita);
		
		uDAO.actualizarUsuario(usuarioAEditar);
	}

	public void eliminarUsuario(Usuario tmp_usuario) {
		uDAO.eliminarUsuario(tmp_usuario);
	}

	public void crearUsuario(String nombre, double monedasDeOro, double tiempoDisponible,
			TipoDeAtraccion tipoDeAtraccionFavorita) {		
		uDAO.crearUsuario(nombre, monedasDeOro, tiempoDisponible, tipoDeAtraccionFavorita);		
	}
}
