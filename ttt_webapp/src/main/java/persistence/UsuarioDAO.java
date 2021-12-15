package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Producto;
import model.TipoDeAtraccion;
import model.Usuario;
import model.nullobjects.NullUser;
import persistence.commons.ProveedorDeConeccion;

public class UsuarioDAO {

	public Usuario encontrarUsuarioPorNombre(String nombreUsuario) {
		Usuario usuario = NullUser.build();
		try {
			String sql = "SELECT usuario.id, usuario.nombre, usuario.cantidad_monedas, usuario.tiempo, tipo_atraccion.nombre, usuario.es_admin, usuario.borrado FROM usuario JOIN tipo_atraccion ON tipo_atraccion.id = usuario.tipo_atraccion_favorita WHERE usuario.nombre = ?";
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombreUsuario.toLowerCase());
			ResultSet resultado = statement.executeQuery();
			if (resultado.next()) {
				usuario = instanciarUsuario(resultado);
			}
		} catch (Exception e) {
			System.err.println("No se ha encontrado al usuario " + nombreUsuario + " en BBDD (UsuarioDAO)");
		}
		return usuario;
	}

	public void actualizarUsuario(Usuario usuario) {
		String nombre = usuario.getNombre();
		String tiempo = Double.toString(usuario.getTiempoDisponible());
		String cantidad_monedas = Double.toString(usuario.getMonedasDeOro());

		AtraccionDAO aDAO = new AtraccionDAO();
		int indiceTipoDeAtraccion = aDAO.getTipoDeAtraccionId(usuario.getTipoAtraccionFavorita());
		try {
			String sql = "UPDATE usuario SET tiempo = ?, cantidad_monedas = ?, tipo_atraccion_favorita = ? WHERE usuario.nombre = ?;";
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, tiempo);
			statement.setString(2, cantidad_monedas);
			statement.setInt(3, indiceTipoDeAtraccion);
			statement.setString(4, nombre);
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println("No se pudo actualizar el usuario en BBDD.");
		}
	}

	private Usuario instanciarUsuario(ResultSet infoUsuario) throws SQLException {
		return new Usuario(infoUsuario.getInt(1), infoUsuario.getString(2), infoUsuario.getDouble(3),
				infoUsuario.getDouble(4), TipoDeAtraccion.valueOf(infoUsuario.getString(5)), infoUsuario.getBoolean(6),
				infoUsuario.getBoolean(7));
	}

	public void actualizarItinerario(Usuario usuario, Producto producto) {
		String usuarioId = Integer.toString(usuario.getId());
		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = null;
			if (producto.esPromocion()) {
				String query = "INSERT INTO itinerario(usuario_id, promocion_id) VALUES (?, ?);";
				statement = conn.prepareStatement(query);
				statement.setString(1, usuarioId);
				statement.setString(2, Integer.toString(producto.getId()));
			} else if (!producto.esPromocion()) {
				String query = "INSERT INTO itinerario(usuario_id, atraccion_id) VALUES (?, ?);";
				statement = conn.prepareStatement(query);
				statement.setString(1, usuarioId);
				statement.setString(2, Integer.toString(producto.getId()));
			}
			if (statement != null) {
				statement.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println("No se puedo guardar la compra del usuario");
		}
	}

	public ArrayList<Usuario> getAllNonDeleted() {
		String query = "SELECT usuario.id, usuario.nombre, usuario.cantidad_monedas, usuario.tiempo, tipo_atraccion.nombre, usuario.es_admin, usuario.borrado FROM usuario JOIN tipo_atraccion ON tipo_atraccion.id = usuario.tipo_atraccion_favorita";

		ArrayList<Usuario> todosLosUsuarios = new ArrayList<Usuario>();

		Connection conn;
		try {
			conn = ProveedorDeConeccion.getConeccion();

			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next() && !resultado.getBoolean(7)) {
				todosLosUsuarios.add(this.instanciarUsuario(resultado));
			}
		} catch (SQLException e) {
			System.err.println("No se pudieron obtener lso usuarios de la base de datos.");
		}

		return todosLosUsuarios;
	}

	public void eliminarUsuario(Usuario tmp_usuario) {
		String sql = "UPDATE usuario SET borrado = 1 WHERE usuario.id = ?;";

		try {
			Connection conn = ProveedorDeConeccion.getConeccion();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, tmp_usuario.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("No se pudo eliminar al usuario " + tmp_usuario.getNombre());
		}

	}

}
