package service;

import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.TipoDeAtraccion;
import persistence.AtraccionDAO;
import persistence.PromocionDAO;

public class ProductoService {
	
	public ArrayList<Producto> listarProductosPorPreferencia(TipoDeAtraccion preferenciaUsuario){
		AtraccionDAO aDAO = new AtraccionDAO();
		ArrayList<Atraccion> atracciones = aDAO.getAll();
		
		PromocionDAO pDAO = new PromocionDAO();
		ArrayList<Promocion> promociones = pDAO.getAll(atracciones);
		// TODO instanciar promos y devolver el listado con todos los productos
		return new ArrayList<Producto>();
	}
}
