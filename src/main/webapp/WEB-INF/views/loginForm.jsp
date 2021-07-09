<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/Cadastrar" var="linkNovoUsuario" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<c:url value="/Login" var="linkLogin" />
	<form action="${linkLogin }" method="POST">
		Email:<input type="text" name="email" />
		<br>
		Senha:<input type="password" name="senha" />
		<br>
		<input type="submit" />
	</form>
	<br>
	<a href="${linkNovoUsuario }">Novo Usuario</a>
</body>
</html>