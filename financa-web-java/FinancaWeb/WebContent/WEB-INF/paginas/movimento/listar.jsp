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
	Consulta de movimentos
</div>

<div id="section">
	<table width="100%">
		<tr align="left">
			<th>Código</th>
			<th>Centro de custo</th>
			<th>Conta</th>
			<th>Tipo do movimento</th>
			<th>Descrição</th>
			<!-- <th>Data do lançamento</th> -->
			<th>Valor</th>
			<th>Parcelado</th>
			<th>Situação</th>
			<th>Ação</th>
		</tr>
		<c:forEach items="${movimentos}" var="movimento">
			<tr class="listas">
				<td>${movimento.codigo}</td>
				<td>${movimento.codigoCentroCusto}</td>
				<td>${movimento.codigoConta}</td>
				<td>${movimento.tipoMovimento}</td>
				<td>${movimento.descricao}</td>
				<!-- <td>${movimento.dataLancamento}</td> -->
				<td>${movimento.valor}</td>
				<td>${movimento.parcelado}</td>
				<td>${movimento.situacao}</td>
				<td><a href="consultar-movimento?codigo=${movimento.codigo}">Consultar</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>