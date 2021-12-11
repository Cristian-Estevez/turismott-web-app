package controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import persistence.AtraccionDAO;
import service.ProductoService;

@WebServlet("/admin/borrar-atraccion.ad")
public class BorrarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = -2719558292950485399L;
	private ProductoService productoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.productoService = new ProductoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombreAtraccion = req.getParameter("nombreAtraccion");
		
		productoService.eliminarAtraccion(nombreAtraccion);
		
		resp.sendRedirect("/ttt_webapp/login");
		
	}
}
