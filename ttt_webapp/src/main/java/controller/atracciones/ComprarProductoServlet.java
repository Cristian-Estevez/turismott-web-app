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
import service.ComprarProductoService;
import service.ProductoService;
import service.UsuarioService;

@WebServlet("/views/atracciones/comprar.do")
public class ComprarProductoServlet extends HttpServlet {

	private static final long serialVersionUID = -4672823548806279662L;
	private ComprarProductoService comprarProductoService;
	private UsuarioService usuarioService;
	private ProductoService productoService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.comprarProductoService = new ComprarProductoService();
		this.usuarioService = new UsuarioService();
		this.productoService = new ProductoService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		String nombreProducto = req.getParameter("nombreProducto");

		comprarProductoService.comprar(usuario, nombreProducto);

		Usuario usuario2 = usuarioService.getUsuarioPorNombre(usuario.getNombre());
		ArrayList<Producto> productos = productoService.listarProductosPorPreferencia(usuario);
		req.getSession().setAttribute("productos", productos);
		req.getSession().setAttribute("usuario", usuario2);
		req.getSession().setAttribute("flash", "Gracias por su compra.");
		
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/views/atracciones/atraccion-list.jsp");
		dispatcher.forward(req, resp);
	}
}
