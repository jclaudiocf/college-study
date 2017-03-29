<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadastro de usuário</title>
	<link href="<c:url value="/resources/css/padrao.css" />" rel="stylesheet">
</head>
<body>

<div id="header">
	Consulta de usuários
</div>

<div id="section">
	<table width="60%">
		<tr align="left">
			<th>Código</th>
			<th>Nome</th>
			<th>Login</th>
			<th>Senha</th>
			<th>Situação</th>
			<th>Ação</th>
		</tr>
		<c:forEach items="${usuarios}" var="usuario">
			<tr class="listas">
				<td>${usuario.codigo}</td>
				<td>${usuario.nome}</td>
				<td>${usuario.login}</td>
				<td>${usuario.senha}</td>
				<td>${usuario.situacao}</td>
				<td><a href="consultar-usuario?codigo=${usuario.codigo}">Consultar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>