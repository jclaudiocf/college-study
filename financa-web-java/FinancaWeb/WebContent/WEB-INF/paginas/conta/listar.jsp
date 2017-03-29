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
	Consulta de conta
</div>

<div id="section">
	<table width="100%">
		<tr align="left">
			<th>Código</th>
			<th>Tipo da conta</th>
			<th>Agência</th>
			<th>Dígito da agência</th>
			<th>Conta</th>
			<th>Dígito da conta</th>
			<th>Endereço da agência</th>
			<th>Saldo</th>
			<th>Situação</th>
			<th>Ação</th>
		</tr>
		<c:forEach items="${contas}" var="conta">
			<tr class="listas">
				<td>${conta.codigo}</td>
				<td>${conta.tipoConta}</td>
				<td>${conta.agencia}</td>
				<td>${conta.digitoAgencia}</td>
				<td>${conta.conta}</td>
				<td>${conta.digitoConta}</td>
				<td>${conta.enderecoAgencia}</td>
				<td>${conta.saldo}</td>
				<td>${conta.situacao}</td>
				<td><a href="consultar-conta?codigo=${conta.codigo}">Consultar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>