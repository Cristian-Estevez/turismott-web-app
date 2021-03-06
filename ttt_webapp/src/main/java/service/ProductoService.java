package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.TipoDeAtraccion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import persistence.commons.ProveedorDeConeccion;
import utils.ProductosPorPreferencia;

public class ProductoService {
	
	private AtraccionDAO aDAO = new AtraccionDAO();
	private PromocionDAO pDAO = new PromocionDAO();
	
	public ArrayList<Producto> listarProductosPorPreferencia(Usuario usuario){
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		productos.addAll(promociones);
		productos.addAll(atracciones);		
		
		this.cargarItinerario(usuario, atracciones, promociones);
		
		ArrayList<Producto> productosSinBorrar = this.quitarBorrados(productos);
		
		ArrayList<Producto> productosParaOfertar = this.quitarYaComprados(productosSinBorrar, usuario);
	
		productosParaOfertar.sort(new ProductosPorPreferencia(usuario.getTipoAtraccionFavorita()));
		
		return productosParaOfertar;
	}

	private ArrayList<Producto> quitarBorrados(ArrayList<Producto> productos) {
		ArrayList<Producto> todosLosProductos = productos;
		ArrayList<Producto> productosSinBorrar = new ArrayList<Producto>(productos);
		
		for (Producto unProducto : todosLosProductos) {
			if (unProducto.borrado()) {
				productosSinBorrar.remove(unProducto);
			}
		}
		
		return productosSinBorrar;
	}

	public ArrayList<Producto> getAll() {
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		productos.addAll(promociones);
		productos.addAll(atracciones);		
		
		return productos;
	}

	public ArrayList<Producto> obtenerItinerario(Usuario usuario) {
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		return this.crearItinerario(usuario, atracciones, promociones);
	}
	
	private ArrayList<Producto> crearItinerario(Usuario usuario, ArrayList<Atraccion> atracciones, ArrayList<Promocion> promociones) {
		String query = "SELECT itinerario.usuario_id, itinerario.atraccion_id, itinerario.promocion_id FROM itinerario WHERE itinerario.usuario_id = ?";
		ArrayList<Producto> itinerarioDeUsuario = new ArrayList<Producto>(); 
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, Integer.toString(usuario.getId()));
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				int id = resultado.getInt(2);
				
				// el resultado es una Promoci??n
				if (resultado.wasNull()) {
					id = resultado.getInt(3);
					for (Promocion unaPromocion : promociones) {
						if (id == unaPromocion.getId()) {
							itinerarioDeUsuario.add(unaPromocion);
						}
					}
				// el serultado es una Atraccion
				} else {
					for (Atraccion unaAtraccion : atracciones) {
						if (id == unaAtraccion.getId()) {
							itinerarioDeUsuario.add(unaAtraccion);
						}
					}
				}
				
			}
			
		} catch (Exception e) {
			System.err.println("Error cargando el itinerario el usuario " + usuario.getNombre() );
		}
		
		return itinerarioDeUsuario;
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
				
				// el resultado es una Promoci??n
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
	
	public ArrayList<Promocion> getAllPromociones() {
		ArrayList<Producto> productos = new ArrayList<Producto>(); 
		productos = this.getAll();
		ArrayList<Promocion> todasLasPromociones = new ArrayList<Promocion>();
		for (Producto unProducto : productos) {
			if (unProducto.esPromocion()) {
				todasLasPromociones.add((Promocion) unProducto);
			}
		}
		return todasLasPromociones;
	}

	public ArrayList<Atraccion> obtenerAtraccionesDeLaPromocion(String nombre) {
		ArrayList<Atraccion> atraccionesDeEstaPromocion = new ArrayList<Atraccion>();
		ArrayList<Promocion> todasLasPromociones = this.getAllPromociones();

		for (Promocion unaPromocion : todasLasPromociones) {
			if (unaPromocion.getNombre().equalsIgnoreCase(nombre)) {
				atraccionesDeEstaPromocion = unaPromocion.getAtraccionesIncluidas();
			}
		}
		return atraccionesDeEstaPromocion;
	}
	
	public Atraccion crearAtraccion(String nombre, double costo, double tiempoDeDuracion,
			int cupo, TipoDeAtraccion tipoDeAtraccion, String descripcion, String urlImagen) {
		boolean borradoDefault = false;
		Atraccion atraccion = new Atraccion(-1, nombre, costo, tiempoDeDuracion, cupo, 
				tipoDeAtraccion, descripcion, urlImagen, borradoDefault);
		if (atraccion.esValida()) {
			aDAO.crearAtraccion(atraccion);
		}		
		return atraccion;
	}

	public void eliminarAtraccion(String nombreAtraccion) {
		aDAO.eliminarAtraccion(nombreAtraccion);
		pDAO.eliminarEnCascada(nombreAtraccion);
		
	}

	public ArrayList<Producto> getAllNonDeleted() {
		ArrayList<Producto> todosLosProductos = this.getAll(); 
		ArrayList<Producto> productosNoBorrados = this.getAll();
		
		for (Producto unProducto : todosLosProductos) {
			if (unProducto.borrado()) {
				productosNoBorrados.remove(unProducto);
			}
		}
			
		return productosNoBorrados;
	}

	public void actualizarAtraccion(int idProducto, String nombre, double costo, double duracion, int cupo,
			TipoDeAtraccion tipoDeAtraccion, String descripcion, String urlImagen) {
		Atraccion atraccionAEditar = this.obtenerProductoPorId(idProducto);
		
		atraccionAEditar.setNombre(nombre);
		atraccionAEditar.setCosto(costo);
		atraccionAEditar.setTiempoDeDuracion(duracion);
		atraccionAEditar.setCupo(cupo);
		atraccionAEditar.setTipoDeAtraccion(tipoDeAtraccion);
		atraccionAEditar.setDescripcion(descripcion);
		atraccionAEditar.setUrlImagen(urlImagen);
		
		aDAO.actualizarAtraccion(atraccionAEditar);
		
	}

	public Atraccion obtenerProductoPorId(int idProducto) {
		ArrayList<Producto> productos = this.getAll();

		Producto productoAObtener = null;
		
		for (Producto unProducto : productos) {
			if(unProducto.getId() == idProducto) {
				productoAObtener = unProducto;
			}
		}
		
		return (Atraccion) productoAObtener;
	}

}