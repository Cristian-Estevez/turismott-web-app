package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
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
		req.setAttribute("flash", nombreAtraccion + " fue eliminada correctamente.");
		ArrayList<Producto> productosDisponibles = productoService.getAllNonDeleted();
		req.getSession().setAttribute("todosLosProductos", productosDisponibles);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/admin/productos-list-admin.jsp");
		dispatcher.forward(req, resp);
	}
}
