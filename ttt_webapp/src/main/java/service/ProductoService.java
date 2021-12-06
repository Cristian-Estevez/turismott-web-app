package service;

import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.TipoDeAtraccion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;
import utils.ProductosPorPreferencia;

public class ProductoService {
	
	public ArrayList<Producto> listarProductosPorPreferencia(TipoDeAtraccion preferenciaUsuario){
		AtraccionDAO aDAO = new AtraccionDAO();
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		PromocionDAO pDAO = new PromocionDAO();
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		productos.addAll(atracciones);
		productos.addAll(promociones);
		productos.sort(new ProductosPorPreferencia(preferenciaUsuario));
		return productos;
	}
}
