package controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import service.UsuarioService;

@WebServlet("/admin/editar-usuario.ad")
public class EditarUsuario extends HttpServlet {
	
	private UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario tmp_usuario = usuarioService.getUsuarioPorNombre(req.getParameter("usuarioCliente"));
		
		System.out.println(tmp_usuario.getNombre() + " made it");
	}
}
