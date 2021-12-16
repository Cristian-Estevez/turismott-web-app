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
import model.Atraccion;
import model.Producto;
import model.TipoDeAtraccion;
import service.ProductoService;

@WebServlet("/admin/editar-atraccion.ad")
public class EditarAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = -715060477326531352L;
	private ProductoService productoService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.productoService = new ProductoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TipoDeAtraccion> tiposDeAtraccion = Arrays.asList(TipoDeAtraccion.values());

		req.getSession().setAttribute("tiposDeAtraccion", tiposDeAtraccion);
		
		Atraccion tmp_atraccion = (Atraccion) productoService.obtenerProductoPorId(Integer.parseInt(req.getParameter("idProducto")));
		req.setAttribute("atraccion", tmp_atraccion);
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/admin/editar-atraccion.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int idProducto = Integer.parseInt(req.getParameter("idProducto"));
		String nombre = req.getParameter("nombre");		
		double costo = Double.parseDouble(req.getParameter("costo"));
		double duracion = Double.parseDouble(req.getParameter("duracion"));
		int cupo = Integer.parseInt(req.getParameter("cupo"));
		TipoDeAtraccion tipoDeAtraccion = TipoDeAtraccion.valueOf(req.getParameter("tipoDeAtraccion"));
		String descripcion = req.getParameter("descripcion");
		String urlImagen = req.getParameter("urlImagen");
		
		productoService.actualizarAtraccion(idProducto, nombre, costo, duracion, cupo, tipoDeAtraccion, descripcion, urlImagen);
		
		req.setAttribute("flash", "El usuario " + nombre + " se actualizó correctamente.");
		ArrayList<Producto> productos = productoService.getAllNonDeleted();
		req.getSession().setAttribute("todosLosProductos", productos);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/admin/productos-list-admin.jsp");
		dispatcher.forward(req, resp);
//		resp.sendRedirect("views/admin/productos-list-admin.jsp");
	}
}
