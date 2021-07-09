package br.com.siteComentario.acao;

import br.com.siteComentario.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Comentar implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		request.setAttribute("usuario", usuario);
		request.setAttribute("link", "AdicionarComentario");
		
		return "dispatcher:comentarioForm.jsp";
	}

	
	
}
