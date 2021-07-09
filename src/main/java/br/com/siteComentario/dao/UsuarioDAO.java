package br.com.siteComentario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.siteComentario.connection.ConnectionFactory;
import br.com.siteComentario.modelo.Comentario;
import br.com.siteComentario.modelo.Usuario;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void inserir(Usuario usuario) {
		if (usuario.getEmail().isBlank() || usuario.getNome().isEmpty() || usuario.getSenha().isEmpty()) {
			throw new RuntimeException();
		}
		String sql = "INSERT INTO usuario (email, nome, senha) values (?, ?, ?)";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setString(1, usuario.getEmail());
			pstm.setString(2, usuario.getNome());
			pstm.setString(3, usuario.getSenha());

			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void editar(Usuario usuario) {
		String sql = "UPDATE usuario SET email = ?, nome = ?, senha = ? WHERE id = ?";

		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setString(1, usuario.getEmail());
			pstm.setString(2, usuario.getNome());
			pstm.setString(3, usuario.getSenha());
			pstm.setInt(4, usuario.getId());

			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		String sql = "DELETE usuario WHERE id = ?";
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setInt(1, id);

			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> listar() {
		String sql = "SELECT id, email, nome FROM usuario";
		List<Usuario> usuarios = new ArrayList<>();
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.execute();
			try (ResultSet result = pstm.getResultSet()) {
				while (result.next()) {
					Integer id = result.getInt(1);
					String email = result.getString(2);
					String nome = result.getString(3);
					Usuario usuario = new Usuario();
					usuario.setEmail(email);
					usuario.setNome(nome);
					usuario.setId(id);

					usuarios.add(usuario);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usuarios;
	}

	public Usuario buscar(Integer id) {
		String sql = "SELECT email, nome FROM usuario WHERE id = ?";
		Usuario usuario = new Usuario();
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();
			try (ResultSet result = pstm.getResultSet()) {
				while (result.next()) {
					String email = result.getString(1);
					String nome = result.getString(2);
					usuario.setEmail(email);
					usuario.setNome(nome);
					usuario.setId(id);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usuario;
	}

	public Usuario buscar(String email) {
		String sql = "SELECT id, nome FROM usuario WHERE email = ?";
		Usuario usuario = null;
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setString(1, email);
			pstm.execute();
			try (ResultSet result = pstm.getResultSet()) {
				while (result.next()) {
					Integer id = result.getInt(1);
					String nome = result.getString(2);
					usuario = new Usuario();
					usuario.setEmail(email);
					usuario.setNome(nome);
					usuario.setId(id);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return usuario;
	}

	public boolean validaUsuario(Usuario usuario) {
		String sql = "SELECT senha FROM usuario WHERE email = ?";

		String senha = "";
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setString(1, usuario.getEmail());

			pstm.execute();
			try (ResultSet result = pstm.getResultSet()) {
				while (result.next()) {
					senha = result.getString(1);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return senha.equals(usuario.getSenha());
	}
	
	public List<Comentario> listarComentarios(Usuario usuario) {
		String sql = "SELECT c.id, c.conteudo, c.data_criacao, c.data_edicao FROM comentario c "
				+ "INNER JOIN usuario u ON c.id_autor = u.id "
				+ "WHERE u.id = ?";
		List<Comentario> comentarios = new ArrayList<>();
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.setInt(1, usuario.getId());
			pstm.execute();
			try(ResultSet result = pstm.getResultSet()) {
				while (result.next()) {
					Integer id_comentario = result.getInt(1);
					String conteudo = result.getString(2);
					String data_criacao = result.getString(3);
					String data_edicao = result.getString(4);
					Comentario comentario = new Comentario();
					comentario.setId(id_comentario);
					comentario.setConteudo(conteudo);
					comentario.setData_criacao(data_criacao);
					comentario.setData_edicao(data_edicao);
					comentario.setAutor(usuario);
					
					comentarios.add(comentario);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return comentarios;
	}
}
