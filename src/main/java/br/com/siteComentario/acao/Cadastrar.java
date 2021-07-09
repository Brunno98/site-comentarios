package br.com.siteComentario.acao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Cadastrar implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		return "dispatcher:novoUsuarioForm.jsp";
		
	}

}
