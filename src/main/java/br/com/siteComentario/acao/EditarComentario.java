package br.com.siteComentario.acao;

import java.time.LocalDateTime;

import br.com.siteComentario.dao.ComentarioDAO;
import br.com.siteComentario.modelo.Comentario;
import br.com.siteComentario.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditarComentario implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		String comentarioEditado = request.getParameter("comentario");
		
		ComentarioDAO comentarioDAO = new ComentarioDAO(usuario);
		Comentario comentario = comentarioDAO.recuperar(id);
		comentario.setConteudo(comentarioEditado);
		comentario.setData_edicao(LocalDateTime.now().toString());
		comentarioDAO.alterar(comentario);
		
		return "redirect:PaginaInicial";
	}

}
