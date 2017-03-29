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
	Consulta de centro de custos
</div>

<div id="section">
	<table width="40%">
		<tr align="left">
			<th>Código</th>
			<th>Descrição</th>
			<th>Situação</th>
			<th>Ação</th>
		</tr>
		<c:forEach items="${centrocustos}" var="centrocusto">
			<tr class="listas">
				<td>${centrocusto.codigo}</td>
				<td>${centrocusto.descricao}</td>
				<td>${centrocusto.situacao}</td>
				<td><a href="consultar-centrocusto?codigo=${centrocusto.codigo}">Consultar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>