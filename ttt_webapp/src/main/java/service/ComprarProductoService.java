package service;

import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.UsuarioDAO;

public class ComprarProductoService {

	public void comprar(Usuario usuario, String nombreProducto) {

		AtraccionDAO aDAO = new AtraccionDAO();
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		PromocionDAO pDAO = new PromocionDAO();
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		Producto productoAComprar = null;
		
		for (Producto unProducto : promociones) {
			if (unProducto.getNombre().equalsIgnoreCase(nombreProducto)) {
				productoAComprar = unProducto;
			}
		}
		
		if (productoAComprar == null) {
			for (Producto unProducto : atracciones) {
				if (unProducto.getNombre().equalsIgnoreCase(nombreProducto)) {
					productoAComprar = unProducto;
				}
			}
		}
		
		if (productoAComprar != null) {
			usuario.comprarProducto(productoAComprar);
			this.actualizarUsuarioBBDD(usuario);
			this.actualizarProductoBBDD(productoAComprar, pDAO, aDAO);
			this.actualizarItinerarioBBDD(usuario, productoAComprar);
		}
	}
	
	private void actualizarItinerarioBBDD(Usuario usuario,Producto producto) {
		UsuarioDAO uDAO = new UsuarioDAO();
		uDAO.actualizarItinerario(usuario, producto);
		
	}

	private void actualizarProductoBBDD(Producto producto, PromocionDAO pDAO, AtraccionDAO aDAO) {
		if (producto.esPromocion()) {
			pDAO.update(producto);
		} else if (!producto.esPromocion()){
			aDAO.update((Atraccion)producto);
		}
	}

	private void actualizarUsuarioBBDD(Usuario usuario) {
		UsuarioDAO uDAO = new UsuarioDAO();		
		uDAO.actualizarUsuario(usuario);
	}
	
}
