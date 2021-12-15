package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Atraccion;
import model.Producto;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;
import model.TipoDeAtraccion;
import persistence.commons.ProveedorDeConeccion;

public class PromocionDAO {

	public ArrayList<Promocion> getAll(ArrayList<Atraccion> atracciones){
		
		String queryPromociones = "SELECT tipo_promocion.nombre AS tipo_de_promocion, promocion.id AS id, promocion.nombre, promocion.descuento_costo, tipo_atraccion.nombre AS tipo_de_atraccion, atraccion.nombre AS atraccion_bonificada, promocion.descripcion, promocion.url_imagen, promocion.borrado FROM promocion JOIN tipo_promocion ON tipo_promocion.id = promocion.tipo_promocion JOIN tipo_atraccion ON tipo_atraccion.id = promocion.tipo_atraccion LEFT JOIN atraccion ON atraccion.id = promocion.atraccion_id;";
		String queryAtraccionesIncluidas = "SELECT atraccion_promocion.atraccion_id FROM atraccion_promocion WHERE atraccion_promocion.promocion_id = ?;";
		
		ArrayList<Promocion> promociones = new ArrayList<Promocion>();
		
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statementPromociones = conn.prepareStatement(queryPromociones);
			ResultSet resultadoPromociones = statementPromociones.executeQuery();			
			
			while (resultadoPromociones.next()) {
				ArrayList<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();
				String promoActualId = resultadoPromociones.getString(2);
				PreparedStatement statementAtraccionesIncluidas = conn.prepareStatement(queryAtraccionesIncluidas);
				statementAtraccionesIncluidas.setString(1, promoActualId);
				ResultSet resultadoAtracciones = statementAtraccionesIncluidas.executeQuery();
				
				while(resultadoAtracciones.next()) {
					for (Atraccion unaAtraccion : atracciones) {
						if (unaAtraccion.getId() == resultadoAtracciones.getInt(1)) {
							
							// la atraccion gratuita en caso de AxB va al final del Array
							if (unaAtraccion.getId() == resultadoPromociones.getInt(6)) {
								atraccionesIncluidas.add(unaAtraccion);
							} else {
								atraccionesIncluidas.add(0, unaAtraccion);
							}							
						}
					}
				}
				
				String tipoDePromocion = resultadoPromociones.getString(1);
			
				if (tipoDePromocion.equalsIgnoreCase("absoluta")) {
					promociones.add(this.instanciarPromocionAbsoluta(resultadoPromociones, atraccionesIncluidas));
				}
				if (tipoDePromocion.equalsIgnoreCase("axb")) {
					promociones.add(this.instanciarPromocionAxB(resultadoPromociones, atraccionesIncluidas));
				}
				if (tipoDePromocion.equalsIgnoreCase("porcentual")) {
					promociones.add(this.instanciarPromocionPorcentual(resultadoPromociones, atraccionesIncluidas));
				}
			}
			
		}catch (Exception e) {
			System.err.println("Error al cargar las promociones de BBDD");
		}
		
		return promociones;
	}
	
	private Promocion instanciarPromocionPorcentual(ResultSet infoPromocion, ArrayList<Atraccion> atraccionesIncluidas) throws SQLException {
		return new PromocionPorcentual(infoPromocion.getInt(2), infoPromocion.getString(3), TipoDeAtraccion.valueOf(infoPromocion.getString(5)),
				infoPromocion.getDouble(4), infoPromocion.getString(7), infoPromocion.getString(8), atraccionesIncluidas, infoPromocion.getBoolean(9));
	}

	private Promocion instanciarPromocionAxB(ResultSet infoPromocion, ArrayList<Atraccion> atraccionesIncluidas) throws SQLException {
		return new PromocionAxB(infoPromocion.getInt(2), infoPromocion.getString(3), 
				TipoDeAtraccion.valueOf(infoPromocion.getString(5)), infoPromocion.getString(7), infoPromocion.getString(8), atraccionesIncluidas, infoPromocion.getBoolean(9));
	}

	private Promocion instanciarPromocionAbsoluta(ResultSet infoPromocion, ArrayList<Atraccion> atraccionesIncluidas) throws SQLException {
		return new PromocionAbsoluta(infoPromocion.getInt(2), infoPromocion.getString(3), infoPromocion.getDouble(4), 
				TipoDeAtraccion.valueOf(infoPromocion.getString(5)), infoPromocion.getString(7), infoPromocion.getString(8), atraccionesIncluidas, infoPromocion.getBoolean(9));
	}
	
	public void update(Producto producto) {
		AtraccionDAO aDAO = new AtraccionDAO();
		ArrayList<Atraccion> atraccionesIncluidas = ((Promocion) producto).getAtraccionesIncluidas();
		
		for (Atraccion unaAtraccion : atraccionesIncluidas) {
			aDAO.update(unaAtraccion);
		}
	}

	public void eliminarEnCascada(String nombreAtraccion) {
		AtraccionDAO aDAO = new AtraccionDAO();
		Atraccion atraccionBorrada = null;
		try {
			atraccionBorrada = aDAO.obtenerAtraccionPorNombre(nombreAtraccion);
		} catch (SQLException e) {
			System.err.println("No se pudo obtener la atraccion " + nombreAtraccion);
		}
		
		String queryEncontrarPromoQueContiene = "SELECT atraccion_promocion.promocion_id from atraccion_promocion WHERE atraccion_promocion.atraccion_id = ?";
		
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(queryEncontrarPromoQueContiene);
			statement.setInt(1, atraccionBorrada.getId());
			ResultSet resultado = statement.executeQuery();
			while (resultado.next()) {
				this.eliminarPromocion(resultado.getInt(1));
			}
		} catch (Exception e) {
			System.err.println("No se pudo actualizar las promociones que contienen la Aracción " + nombreAtraccion);
		}
		
	}
	
	public void eliminarPromocion(int promoId) throws SQLException {
		String sql = "UPDATE promocion SET borrado = 1 WHERE promocion.id = ?;";
		
		Connection conn = ProveedorDeConeccion.getConeccion();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, promoId);
		statement.executeUpdate();
	}
	
}
