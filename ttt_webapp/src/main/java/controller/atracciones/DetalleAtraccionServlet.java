package controller.atracciones;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Producto;
import model.Usuario;
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
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		Producto productoAMostrar = productoService.obtenerProductoPorNombre(nombreProducto);

		if (productoAMostrar != null) {
			req.getSession().setAttribute("producto", productoAMostrar);
			req.getSession().setAttribute("usuario", usuario);
			if (productoAMostrar.esPromocion()) {
				
				// logica para mandar solo los productos que contiene la promo
				ArrayList<Atraccion> atraccionesDeEstaPromocion = productoService.obtenerAtraccionesDeLaPromocion(productoAMostrar.getNombre());
				req.getSession().setAttribute("atraccionesDeEstaPromocion", atraccionesDeEstaPromocion);
				resp.sendRedirect("detalle-promocion.jsp");
				
				
			} else if (!productoAMostrar.esPromocion()) {
				resp.sendRedirect("detalle-atraccion.jsp");
			}
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
			dispatcher.forward(req, resp);
		}

		// TODO hacer el view y ahí mostrar algo si es promocion y otra cosa si es
		// atraccion

	}
}
