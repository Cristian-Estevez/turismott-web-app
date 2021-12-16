package controller.session;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 4224193167109372559L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("usuario");
		req.getSession().removeAttribute("itinerario");
		req.getSession().removeAttribute("productos");
		req.getSession().removeAttribute("todosLosProductos");
		req.setAttribute("despedida", "¡Hasta pronto! Gracias por su visita.");
		
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(req, resp);
	}
}
