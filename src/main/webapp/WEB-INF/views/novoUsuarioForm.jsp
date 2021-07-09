<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/CadastrarUsuario" var="linkCadastrarUsuario" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar novo usuário</title>
</head>
<body>
${erro }
<form action="${linkCadastrarUsuario }" method="POST">
	Email: <input type="email" name="email"/>
	<br>
	Nome: <input type="text" name="nome"/>
	<br>
	Senha: <input type="password" name="senha" />
	<br>
	<input type="submit" />
</form>

</body>
</html>