package br.com.siteComentario.acao;

import br.com.siteComentario.dao.UsuarioDAO;
import br.com.siteComentario.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		if (usuarioDAO.validaUsuario(usuario)) {
			usuario = usuarioDAO.buscar(email);
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuario", usuario);
			return "redirect:PaginaInicial";
		} else {
			return "redirect:LoginForm";
		}
		
	}

}
