package br.com.siteComentario.acao;

import java.time.LocalDate;

import br.com.siteComentario.dao.ComentarioDAO;
import br.com.siteComentario.modelo.Comentario;
import br.com.siteComentario.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdicionarComentario implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		String comentario = request.getParameter("comentario");
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		ComentarioDAO comentarioDAO = new ComentarioDAO(usuario);
		
		Comentario coment = new Comentario();
		coment.setAutor(usuario);
		coment.setConteudo(comentario);
		coment.setData_criacao(LocalDate.now().toString());
		coment.setData_edicao(LocalDate.now().toString());
		
		comentarioDAO.inserir(coment);
		
		return "redirect:PaginaInicial";
	}

}
