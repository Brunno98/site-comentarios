<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario de Comentário</title>
</head>
<body>

Autor: ${usuario.nome }
<br>
<form action="${link }" method="POST">
	<input type="hidden" name="id" value="${id }" />
	Comentario: <input type="text" name="comentario" value="${conteudo }"/>
	<br>
	<input type="submit" />

</form>

</body>
</html>