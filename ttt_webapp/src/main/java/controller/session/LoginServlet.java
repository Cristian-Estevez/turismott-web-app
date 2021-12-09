package controller.session;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import model.Usuario;
import service.LoginService;
import service.ProductoService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -8992760235223363330L;
	private LoginService loginService;
	private ProductoService productoService;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
		productoService = new ProductoService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombreUsuario = req.getParameter("nombreUsuario");
		
		Usuario usuario = loginService.login(nombreUsuario);
		
		
		if (!usuario.isNull()) {
			req.getSession().setAttribute("usuario", usuario);
			
			if (!usuario.esAdmin()) {
				ArrayList<Producto> productos = productoService.listarProductosPorPreferencia(usuario);
				req.getSession().setAttribute("productos", productos);			
				resp.sendRedirect("views/atracciones/atraccion-list.jsp");
			} else {
				ArrayList<Producto> productos = productoService.getAll();
				req.getSession().setAttribute("productos", productos);
				resp.sendRedirect("views/admin/productos-list-admin.jsp");
			}
			
			
		} else {
			req.setAttribute("flash", "Nombre de usuario incorrecto");
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/login.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ArrayList<Producto> productos = productoService.getAll();
		
		req.getSession().setAttribute("productos", productos);
		resp.sendRedirect("views/atracciones/atraccion-list.jsp");
	}

}
