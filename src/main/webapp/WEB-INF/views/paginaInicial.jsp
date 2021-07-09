<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:url value="/Comentar" var="linkComentar" />
<c:url value="/Editar" var="linkEditarComentario" />
<c:url value="/DeletarComentario" var="linkDeletar" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<c:import url="header.jsp" />

<a href="${linkComentar }">Comentar +</a>
<br>
<br>

<table>
<tr>
	<th>Autor</th>
	<th>Comentario</th>
	<th>Data de comentario</th>
	<th>Data de Edição</th>
	<th colspan="2">Acao</th>
</tr>
	<c:forEach items="${comentarios }" var="comentario">
	<tr>
		<td>${comentario.autor.nome }</td>
		<td>${comentario.conteudo }</td>
		<td>${comentario.data_criacao }</td>
		<td>  
		<c:if test="${comentario.data_criacao ne comentario.data_edicao }">
			${comentario.data_edicao }
		</c:if>
		<c:if test="${comentario.data_criacao eq comentario.data_edicao }">
			-
		</c:if>
		</td>
		<td>
			<a href="${linkEditarComentario }?id=${comentario.id }">Editar</a>
		</td>
		<td>
			<a href="${linkDeletarComentario }">Deletar</a>
		</td>
	</tr>
	</c:forEach>
</table>


</body>
</html>