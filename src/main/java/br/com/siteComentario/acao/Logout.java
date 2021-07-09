package br.com.siteComentario.acao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout implements Acao{

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession sessao = request.getSession();
		sessao.invalidate();
		
		return "redirect:LoginForm";
		
	}

}
