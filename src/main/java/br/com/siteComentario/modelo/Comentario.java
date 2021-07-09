package br.com.siteComentario.modelo;

public class Comentario {
	
	/* 
	 * eu deveria fazer a referencia pro autor aqui ou deveria fazer na classe
	 * Autor com uma lista de Comentario?
	 */
	private int id;
	private String conteudo;
	private String data_criacao;
	private String data_edicao;
	private Usuario autor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(String data_criacao) {
		this.data_criacao = data_criacao;
	}
	public String getData_edicao() {
		return data_edicao;
	}
	public void setData_edicao(String data_edicao) {
		this.data_edicao = data_edicao;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	
}
