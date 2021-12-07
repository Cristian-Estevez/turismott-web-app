package service;

import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.Usuario;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
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
		
		ArrayList<Producto> productosParaOfertar = new ArrayList<Producto>();
		
		productosParaOfertar = this.quitarYaComprados(productos, usuario);
		
		productosParaOfertar.sort(new ProductosPorPreferencia(usuario.getTipoAtraccionFavorita()));
		
		return productosParaOfertar;
	}

	private ArrayList<Producto> quitarYaComprados(ArrayList<Producto> productos, Usuario usuario) {
		ArrayList<Producto> productosParaOfertar = new ArrayList<Producto>();

		ArrayList<Producto> productosCopy = new ArrayList<Producto>(productos);

		for (Producto unProducto : productos) {
			if (usuario.yaCompro(unProducto)) {
				productosCopy.remove(unProducto);
			}
		}
		return productosCopy;		
	}
}