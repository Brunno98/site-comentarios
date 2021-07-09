package br.com.siteComentario.acao;

import br.com.siteComentario.dao.UsuarioDAO;
import br.com.siteComentario.modelo.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastrarUsuario implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) {
		
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setNome(nome);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		if (usuarioDAO.buscar(usuario.getEmail()) == null ) {
			usuarioDAO.inserir(usuario);
			return "redirect:LoginForm";
		} else {
			request.setAttribute("erro", "Usuario já cadastrado, por favor insira outro e-mail.");
			return "dispatcher:novoUsuarioForm.jsp";
		}
	}

}
