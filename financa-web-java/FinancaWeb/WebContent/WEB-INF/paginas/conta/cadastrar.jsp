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
	Cadastro de conta
</div>

<div id="section">
	<form name="cadastro" method="post">
		<input type="hidden" name="codigo" value="0" />
		<input type="hidden" name="codigoUsuario" value="<%=usuarioLogado.getCodigo() %>" />
		Descrição:<br>
		<input type="text" name="descricao" /><br>
		Tipo da conta:<br>
		<select name="tipoConta">
			<option value="BANCO">Banco</option>
			<option value="CAIXA">Caixa</option>
			<option value="APLICACAO">Aplicação</option>
		</select><br>
		Agência:<br>
		<input type="text" name="agencia" /><br>
		Dígito da agência:<br>
		<input type="text" name="digitoAgencia" /><br>
		Conta:<br>
		<input type="text" name="conta" /><br>
		Dígito da conta:<br>
		<input type="text" name="digitoConta" /><br>
		Endereço da agência:<br>
		<input type="text" name="enderecoAgencia" /><br>
		Saldo:<br>
		<input type="text" name="saldo" value="" onkeypress="return formatar_moeda(this, '', '.', event);" /><br>
		Situação:<br>
		<select name="situacao">
			<option value="ATIVO">Ativo</option>
			<option value="CANCELADO">Cancelado</option>
		</select><br><br>
		
		<input type="submit" value="Salvar" onclick="acaoFormulario('salvar-conta')" />&emsp;
		<input type="submit" value="Listar cadastros" onclick="acaoFormulario('listar-contas')" />
	</form>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>