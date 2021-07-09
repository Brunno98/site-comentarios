package br.com.siteComentario.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.siteComentario.connection.ConnectionFactory;
import br.com.siteComentario.modelo.Comentario;
import br.com.siteComentario.modelo.Usuario;

public class ComentarioDAO {
	
	private Connection connection;
	private Usuario autor;
	
	public ComentarioDAO(Usuario autor) {
		this.connection = new ConnectionFactory().getConnection();
		this.autor = autor;
	}
	
	public void inserir(Comentario comentario) {
		String sql = "INSERT INTO comentario (id_autor, conteudo, data_criacao, data_edicao) values(?, ?, ?, ?)";
		
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setInt(1, this.autor.getId());
			pstm.setString(2, comentario.getConteudo());
			pstm.setString(3, LocalDateTime.now().toString());
			pstm.setString(4, LocalDateTime.now().toString());
			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void alterar(Comentario comentario) {
		String sql = "UPDATE comentario SET conteudo = ?, data_edicao = ? WHERE id = ?";

		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setString(1, comentario.getConteudo());
			pstm.setString(2, LocalDateTime.now().toString());
			pstm.setInt(3, comentario.getId());

			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void deletar(Integer id) {
		String sql = "DELETE comentario WHERE id = ?";
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setInt(1, id);

			pstm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public Comentario recuperar(Integer id) {
		String sql = "SELECT c.conteudo, c.data_criacao, c.data_edicao, u.id, u.email, u.nome FROM comentario c "
				+ "INNER JOIN usuario u ON id_autor = u.id "
				+ "WHERE c.id = ?";
		Comentario comentario = new Comentario();
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.setInt(1, id);
			pstm.execute();
			try (ResultSet result = pstm.getResultSet()) {
				while (result.next()) {
					String conteudo = result.getString(1);
					String data_criacao = result.getString(2);
					String data_edicao = result.getString(3);
					Integer id_usuario = result.getInt(4);
					String email = result.getString(5);
					String nome = result.getString(6);
					Usuario usuario = new Usuario();
					usuario.setId(id_usuario);
					usuario.setEmail(email);
					usuario.setNome(nome);
					comentario.setId(id);
					comentario.setConteudo(conteudo);
					comentario.setAutor(usuario);
					comentario.setData_criacao(data_criacao);
					comentario.setData_edicao(data_edicao);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return comentario;
	}
	
//	public List<Comentario> listar(){
//		String sql = "SELECT id, id_autor, conteudo, data_criacao, data_edicao FROM comentario";
//		List<Comentario> comentarios = new ArrayList<>();
//		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
//			pstm.execute();
//			try (ResultSet result = pstm.getResultSet()) {
//				while (result.next()) {
//					Integer id = result.getInt(1);
//					Integer id_autor = result.getInt(2);
//					String conteudo = result.getString(3);
//					String data_criacao = result.getString(4);
//					String data_edicao = result.getString(5);
//					
//					Comentario comentario = new Comentario();
//					comentario.setId(id);
//					comentario.setConteudo(conteudo);
//					comentario.setData_criacao(data_criacao);
//					comentario.setData_edicao(data_edicao);
//					
//					comentarios.add(comentario);
//				}
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//		return comentarios;
//	}
	
	public List<Comentario> listar() {
		String sql = "SELECT c.id , c.conteudo, c.data_criacao, c.data_edicao, u.id, u.email, u.nome"
				+ " FROM comentario c"
				+ " INNER JOIN usuario u ON c.id_autor = u.id"
				+ " ORDER BY c.data_criacao DESC";
		List<Comentario> comentarios = new ArrayList<>();
		try (PreparedStatement pstm = this.connection.prepareStatement(sql)) {
			pstm.execute();
			try (ResultSet result = pstm.getResultSet()) {
				while (result.next()) {
					Integer id_comentario = result.getInt(1);
					String conteudo = result.getString(2);
					String data_criacao = result.getString(3);
					String data_edicao = result.getString(4);
					Integer id_usuario = result.getInt(5);
					String email = result.getString(6);
					String nome = result.getString(7);
					Comentario comentario = new Comentario();
					Usuario usuario = new Usuario();
					usuario.setId(id_usuario);
					usuario.setEmail(email);
					usuario.setNome(nome);
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
