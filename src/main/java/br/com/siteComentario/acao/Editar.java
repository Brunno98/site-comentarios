package br.com.siteComentario.acao;

import br.com.siteComentario.dao.ComentarioDAO;
import br.com.siteComentario.modelo.Comentario;
import br.com.siteComentario.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Editar implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		ComentarioDAO comentarioDAO = new ComentarioDAO(usuario);
		Comentario comentario = comentarioDAO.recuperar(id); 
		
		request.setAttribute("id", comentario.getId());
		request.setAttribute("conteudo", comentario.getConteudo());
		request.setAttribute("link", "EditarComentario");
		
		return "dispatcher:comentarioForm.jsp";
	}

}
