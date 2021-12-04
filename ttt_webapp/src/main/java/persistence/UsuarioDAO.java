package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoDeAtraccion;
import model.Usuario;
import model.nullobjects.NullUser;
import persistence.commons.ProveedorDeConeccion;

public class UsuarioDAO {

	public Usuario encontrarUsuarioPorNombre(String nombreUsuario) {
		Usuario usuario = NullUser.build();
		try {
			String sql = "SELECT usuario.id, usuario.nombre, usuario.cantidad_monedas, usuario.tiempo, tipo_atraccion.nombre, usuario.es_admin FROM usuario JOIN tipo_atraccion ON tipo_atraccion.id = usuario.tipo_atraccion_favorita WHERE usuario.nombre = ?";
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreUsuario.toLowerCase());
			ResultSet resultado = statement.executeQuery();
						
			if (resultado.next()) {
				usuario = instanciarUsuario(resultado);
			}						
		} catch (Exception e) {
			System.err.println("Error encontrando al usuario" + nombreUsuario);
		}
		return usuario;
	}
	
	private Usuario instanciarUsuario(ResultSet infoUsuario) throws SQLException {
		return new Usuario(infoUsuario.getInt(1), infoUsuario.getString(2), infoUsuario.getDouble(3), infoUsuario.getDouble(4),
				TipoDeAtraccion.valueOf(infoUsuario.getString(5)), infoUsuario.getBoolean(6));
	}
}
