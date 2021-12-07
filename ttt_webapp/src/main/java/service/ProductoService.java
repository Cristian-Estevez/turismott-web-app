package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.ProveedorDeConeccion;
import utils.ProductosPorPreferencia;

public class ProductoService {
	
	public ArrayList<Producto> listarProductosPorPreferencia(Usuario usuario){
		AtraccionDAO aDAO = new AtraccionDAO();
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		PromocionDAO pDAO = new PromocionDAO();
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		productos.addAll(atracciones);
		productos.addAll(promociones);
		
		this.cargarItinerario(usuario, atracciones, promociones);
		
		ArrayList<Producto> productosParaOfertar = new ArrayList<Producto>();
		
		productosParaOfertar = this.quitarYaComprados(productos, usuario);
		
		productosParaOfertar.sort(new ProductosPorPreferencia(usuario.getTipoAtraccionFavorita()));
		
		return productosParaOfertar;
	}

	private ArrayList<Producto> getAll() {
		AtraccionDAO aDAO = new AtraccionDAO();
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		PromocionDAO pDAO = new PromocionDAO();
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		productos.addAll(atracciones);
		productos.addAll(promociones);
		
		return productos;
	}

	private void cargarItinerario(Usuario usuario, ArrayList<Atraccion> atracciones, ArrayList<Promocion> promociones) {
		String query = "SELECT itinerario.usuario_id, itinerario.atraccion_id, itinerario.promocion_id FROM itinerario WHERE itinerario.usuario_id = ?";
		
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, Integer.toString(usuario.getId()));
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				int id = resultado.getInt(2);
				
				// el resultado es una Promoción
				if (resultado.wasNull()) {
					id = resultado.getInt(3);
					for (Promocion unaPromocion : promociones) {
						if (id == unaPromocion.getId()) {
							usuario.actualizarItinerario(unaPromocion);
						}
					}
				// el serultado es una Atraccion
				} else {
					for (Atraccion unaAtraccion : atracciones) {
						if (id == unaAtraccion.getId()) {
							usuario.actualizarItinerario(unaAtraccion);
						}
					}
				}
				usuario.actualizarCantidadDeProductosCompradaPreviamente();
			}
			
		} catch (Exception e) {
			System.err.println("Error cargando el itinerario el usuario " + usuario.getNombre() );
		}
	}

	private ArrayList<Producto> quitarYaComprados(ArrayList<Producto> productos, Usuario usuario) {

		ArrayList<Producto> productosCopy = new ArrayList<Producto>(productos);

		for (Producto unProducto : productos) {
			if (usuario.yaCompro(unProducto)) {
				productosCopy.remove(unProducto);
			}
		}
		return productosCopy;		
	}

	public Producto obtenerProductoPorNombre(String nombreProducto) {
		ArrayList<Producto> productos = this.getAll();

		Producto productoAObtener = null;
		
		for (Producto unProducto : productos) {
			if(unProducto.getNombre().equalsIgnoreCase(nombreProducto)) {
				productoAObtener = unProducto;
			}
		}
		
		return productoAObtener;
	}
}