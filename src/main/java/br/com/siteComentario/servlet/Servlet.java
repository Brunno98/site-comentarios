package br.com.siteComentario.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import br.com.siteComentario.acao.Acao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getServletPath().replaceFirst("/", "");
		
		Class c;
		String resp;
		try {
			c = Class.forName("br.com.siteComentario.acao." + acao);
			Acao classeAcao = (Acao) c.getDeclaredConstructor().newInstance();
			resp = classeAcao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
		
		String[] metodo_recurso = resp.split(":");
		
		if (metodo_recurso[0].equals("dispatcher")) {
			request.getRequestDispatcher("/WEB-INF/views/" + metodo_recurso[1]).forward(request, response);			
		} else {
			response.sendRedirect(metodo_recurso[1]);
		}
	}

}
