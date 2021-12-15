package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Atraccion;
import model.TipoDeAtraccion;
import persistence.commons.ProveedorDeConeccion;

public class AtraccionDAO {

	public ArrayList<Atraccion> getAll(){
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		
		try {
			String sql = "SELECT atraccion.id, atraccion.nombre, atraccion.costo, atraccion.duracion, atraccion.cupo, tipo_atraccion.nombre as TipoDeAtraccion, atraccion.descripcion, atraccion.url_imagen, atraccion.borrado FROM atraccion JOIN tipo_atraccion ON tipo_atraccion.id = atraccion.tipo;";
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet  resultado = statement.executeQuery();
			while (resultado.next()) {
				atracciones.add(this.instanciarAtraccion(resultado));
			}
		} catch (Exception e) {
			System.err.println("No se ha podido obtener las atracciones de la base de datos");
		}
		
		
		return atracciones;
	}
	
	private Atraccion instanciarAtraccion(ResultSet infoAtraccion) throws SQLException {
		return new Atraccion(infoAtraccion.getInt(1), infoAtraccion.getString(2), infoAtraccion.getDouble(3), 
				infoAtraccion.getDouble(4), infoAtraccion.getInt(5), TipoDeAtraccion.valueOf(infoAtraccion.getString(6)), 
				infoAtraccion.getString(7), infoAtraccion.getString(8), infoAtraccion.getBoolean(9));
	}
	
	/**
	 * Guarda atracción en Base de Datos
	 * @param atraccion
	 */
	public void crearAtraccion(Atraccion atraccion) {
		String query = "INSERT INTO atraccion (nombre, costo, duracion, cupo, tipo, descripcion, url_imagen) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		int tipoDeAtraccionId = getTipoDeAtraccionId(atraccion.getTipoDeAtraccion());
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempoDeDuracion());
			statement.setInt(4, atraccion.getCupo());
			statement.setInt(5, tipoDeAtraccionId);
			statement.setString(6, atraccion.getDescripcion());
			statement.setString(7, atraccion.getUrlImagen());
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println("No se pudo guardar la atracción en la base de datos");
		}		
	}
	
	private int getTipoDeAtraccionId(TipoDeAtraccion tipoDeAtraccion) {
		String query = "SELECT tipo_atraccion.id FROM tipo_atraccion WHERE tipo_atraccion.nombre = ?;";
		int index = -1;
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tipoDeAtraccion.toString());
			ResultSet  resultado = statement.executeQuery();
			if (resultado.next()) {
				index = resultado.getInt(1);
			}
		} catch (Exception e) {
			System.err.println("No se pudo obtener el id de el tipo de atraccion" + tipoDeAtraccion.toString());
		}
		
		return index;
	}
	
	public void update(Atraccion atraccion) {
		String query = "UPDATE atraccion SET cupo = ? WHERE atraccion.id = ?";
		String cupo = Integer.toString(atraccion.getLugaresDisponibles());
		String idAtraccion = Integer.toString(atraccion.getId());
				
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, cupo);
			statement.setString(2, idAtraccion);
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println("Error al actualizar atraccion en BBDD.");
		}
	}

	public void eliminarAtraccion(String nombreAtraccion) {
		String query = "UPDATE atraccion SET borrado = 1 WHERE atraccion.nombre = ?;";
		
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, nombreAtraccion);
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println("no se pudo eliminar la atraccion " + nombreAtraccion);
		}
		
		// TODO generar modo CASCADE para eliminacion de promocion que contiene la atracción o hacerlo desde el service mejor
	}

	public Atraccion obtenerAtraccionPorNombre(String nombreAtraccion) throws SQLException {
		String sql = "SELECT atraccion.id, atraccion.nombre, atraccion.costo, atraccion.duracion, atraccion.cupo, tipo_atraccion.nombre as TipoDeAtraccion, atraccion.descripcion, atraccion.url_imagen, atraccion.borrado FROM atraccion JOIN tipo_atraccion ON tipo_atraccion.id = atraccion.tipo WHERE atraccion.nombre = ?;";
		ResultSet resultado = null;
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreAtraccion);
			resultado = statement.executeQuery();
		} catch (Exception e) {
			System.err.println("No se pudo obtener la atraccion " + nombreAtraccion);
		}
		
		return this.instanciarAtraccion(resultado);
	}
	
}
