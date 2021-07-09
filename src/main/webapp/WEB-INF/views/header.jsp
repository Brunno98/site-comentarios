<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/Logout" var="linkLogout" />
<!DOCTYPE html>
<html>
<body>

	<h3>${usuario.nome }${usuario.email }</h3>
	<br>
	<a href="${linkLogout }">Logout</a>
	<hr>
	<br>

</body>
</html>