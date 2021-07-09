package br.com.siteComentario.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class AutenticacaoFilter
 */
@WebFilter("/*")
public class AutenticacaoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest HttpRequest = (HttpServletRequest) request;
		HttpServletResponse HttpResponse = (HttpServletResponse) response;
		
		HttpSession sessao = HttpRequest.getSession();
		boolean usuarioLogado = sessao.getAttribute("usuario") != null;
		boolean recursoNaoProtegido = (HttpRequest.getServletPath().equals("/Login") || HttpRequest.getServletPath().equals("/LoginForm") ||
				HttpRequest.getServletPath().equals("/Cadastrar") || HttpRequest.getServletPath().equals("/CadastrarUsuario"));
		
		if(recursoNaoProtegido || usuarioLogado) {
			chain.doFilter(request, response);
		} else {
			HttpResponse.sendRedirect("LoginForm");
		}
		
	}

    

}
