package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TipoDeAtraccion;
import model.Usuario;
import service.UsuarioService;

@WebServlet("/admin/crear-usuario.ad")
public class CrearUsuario extends HttpServlet {

	private static final long serialVersionUID = 112783182512424247L;
	private UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TipoDeAtraccion> tiposDeAtraccion = Arrays.asList(TipoDeAtraccion.values());
		req.getSession().setAttribute("tiposDeAtraccion", tiposDeAtraccion);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/crear-usuario.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		double monedasDeOro = Double.parseDouble(req.getParameter("monedasDeOro"));
		double tiempoDisponible = Double.parseDouble(req.getParameter("tiempoDisponible"));
		TipoDeAtraccion tipoDeAtraccionFavorita = TipoDeAtraccion.valueOf(req.getParameter("tipoDeAtraccion"));
				
		usuarioService.crearUsuario(nombre, monedasDeOro, tiempoDisponible, tipoDeAtraccionFavorita);
		
		req.setAttribute("flash", "El usuario " + nombre + " se creó correctamente.");
		ArrayList<Usuario> usuariosNoEliminados = usuarioService.getAllNonDeleted(); 
		req.getSession().setAttribute("todosLosUsuarios", usuariosNoEliminados);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/usuarios-list-admin.jsp");
		dispatcher.forward(req, resp);
	}
}
