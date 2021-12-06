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
			String sql = "SELECT atraccion.id, atraccion.nombre, atraccion.costo, atraccion.duracion, atraccion.cupo, tipo_atraccion.nombre as TipoDeAtraccion, atraccion.descripcion, atraccion.url_imagen FROM atraccion JOIN tipo_atraccion ON tipo_atraccion.id = atraccion.tipo;";
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
				infoAtraccion.getString(7), infoAtraccion.getString(8));
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
	
}
