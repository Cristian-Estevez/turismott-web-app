package controller.atracciones;

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
import service.ProductoService;

@WebServlet("/views/atracciones/mi-itinerario")
public class MiItinerarioServlet extends HttpServlet {

	private static final long serialVersionUID = 2333043905021501654L;
	private ProductoService productoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.productoService = new ProductoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		ArrayList<Producto> productosComprados = productoService.obtenerItinerario(usuario);
		
		if (!productosComprados.isEmpty()) {
			req.getSession().setAttribute("itinerario", productosComprados);
			req.getSession().setAttribute("usuario", usuario);
			resp.sendRedirect("mi-itinerario.jsp");
		} else {
			resp.sendRedirect("mi-itinerario.jsp");
		}
		
	}
}
