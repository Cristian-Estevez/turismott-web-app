package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import service.ProductoService;

@WebServlet("/views/atracciones/detalle-atraccion")
public class DetalleAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 5487492190189342772L;
	private ProductoService productoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.productoService = new ProductoService();
	
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombreProducto = req.getParameter("nombreProducto");
		
		Producto productoAMostrar = productoService.obtenerProductoPorNombre(nombreProducto);
		
		System.out.println(productoAMostrar.esPromocion());
		// TODO hacer el view y ahí mostrar algo si es promocion y otra cosa si es atraccion
		
	}
}
