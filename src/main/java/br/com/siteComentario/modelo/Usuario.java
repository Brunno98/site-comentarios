package br.com.siteComentario.modelo;

import java.util.Collections;
import java.util.List;

public class Usuario {

	private int id;
	private String email;
	private String nome;
	private String senha;
	private List<Comentario> comentarios;

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		/* Essa implementação não é segura pra senha */
		this.senha = Integer.toString(senha.hashCode());
	}

	public List<Comentario> getComentarios() {
		return Collections.unmodifiableList(comentarios);
	}

	public void adicionar(Comentario comentario) {
		this.comentarios.add(comentario);
	}
}
