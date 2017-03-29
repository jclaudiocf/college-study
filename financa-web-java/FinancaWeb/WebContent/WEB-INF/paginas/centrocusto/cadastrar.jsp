<%@page import="com.projeto.model.tabelas.Usuarios"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadastro de centro de custo</title>
	<link href="<c:url value="/resources/css/padrao.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/cadastros.js" />"></script>
</head>
<body>

<% Usuarios usuarioLogado = (Usuarios) request.getSession().getAttribute("usuarioLogado"); %>

<div id="header">
	Cadastro de centro de custo
</div>

<div id="section">
	<form name="cadastro" method="post">
		<input type="hidden" name="codigo" value="0" />
		<input type="hidden" name="codigoUsuario" value="<%=usuarioLogado.getCodigo() %>" />
		Descrição:<br>
		<input type="text" name="descricao" /><br>
		Situação:<br>
		<select name="situacao">
			<option value="ATIVO">Ativo</option>
			<option value="CANCELADO">Cancelado</option>
		</select><br><br>
		
		<input type="submit" value="Salvar" onclick="acaoFormulario('salvar-centrocusto')" />&emsp;
		<input type="submit" value="Listar cadastros" onclick="acaoFormulario('listar-centrocustos')" />
	</form>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>