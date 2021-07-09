package br.com.siteComentario.acao;

import java.util.List;

import br.com.siteComentario.dao.ComentarioDAO;
import br.com.siteComentario.modelo.Comentario;
import br.com.siteComentario.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PaginaInicial implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		ComentarioDAO comentarioDAO = new ComentarioDAO(usuario);
		List<Comentario> comentarios = comentarioDAO.listar();
		
		request.setAttribute("usuario", usuario);
		request.setAttribute("comentarios", comentarios);
		
		return "dispatcher:paginaInicial.jsp";
		
	}

}
