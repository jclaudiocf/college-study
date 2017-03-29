<%@page import="com.projeto.model.relatorios.GridSaldo"%>
<%@page import="com.projeto.model.relatorios.ViewSaldo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.projeto.model.tabelas.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Saldo</title>
	<link href="<c:url value="/resources/css/padrao.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/cadastros.js" />"></script>
</head>
<body>

<%
Usuarios usuarioLogado = (Usuarios) request.getSession().getAttribute("usuarioLogado");

ViewSaldo viewSaldo = new ViewSaldo();
List<GridSaldo> lista = new ArrayList<>(viewSaldo.listar(0).values());

%>

<div id="header">
	Saldo
</div>

<div id="section">
	<table width="100%">
		<tr align="left">
			<th>Código</th>
			<th>Descrição</th>
			<th>Saldo</th>
		</tr>
		<c:forEach items="<%=lista%>" var="saldo">
			<tr class="listas">
				<td>${saldo.codigo}</td>
				<td>${saldo.descricao}</td>
				<td>${saldo.valor}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>