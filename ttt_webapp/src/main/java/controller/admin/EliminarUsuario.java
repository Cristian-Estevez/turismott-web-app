package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import service.UsuarioService;

@WebServlet("/admin/eliminar-usuario.ad")
public class EliminarUsuario extends HttpServlet {
	
	private static final long serialVersionUID = 8104362224594569419L;
	private UsuarioService usuarioService;

	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario tmp_usuario = usuarioService.getUsuarioPorNombre(req.getParameter("usuarioCliente"));
		req.setAttribute("tmp_usuario", tmp_usuario);
		
		usuarioService.eliminarUsuario(tmp_usuario);
		
		ArrayList<Usuario> usuariosNoEliminados = usuarioService.getAllNonDeleted(); 
		req.getSession().setAttribute("todosLosUsuarios", usuariosNoEliminados);
		req.setAttribute("flash", "Se eliminó correctamente al usuario " + tmp_usuario.getNombre() + ".");
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/admin/usuarios-list-admin.jsp");
		dispatcher.forward(req, resp);
	}
}
