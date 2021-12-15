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

@WebServlet("/admin/lista-usuarios.ad")
public class ListadoUsuariosServlet extends HttpServlet {

	private static final long serialVersionUID = -1889394696133677980L;
	private UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ArrayList<Usuario> usuariosNoEliminados = usuarioService.getAllNonDeleted(); 
		req.getSession().setAttribute("todosLosUsuarios", usuariosNoEliminados);
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/admin/usuarios-list-admin.jsp");
		dispatcher.forward(req, resp);
		
	}
}
