<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Cadastro de usuário</title>
	<link href="<c:url value="/resources/css/padrao.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/cadastros.js" />"></script>
</head>
<body>

<div id="header">
	Cadastro de fixo
</div>

<div id="section">
	<form name="cadastro" method="post">
		Código:<br>
		<input type="hidden" name="codigo" value="${param.codigo}" />
		<input type="hidden" name="codigoUsuario" value="${param.codigoUsuario}" />
		<input type="text" value="${param.codigo}" disabled="disabled" /><br>
		Tipo:<br>
		<select name="tipoFixo">
			<option value="PROVENTO" ${param.tipoFixo == 'PROVENTO' ? 'selected' : ''}>Provento</option>
			<option value="DESCONTO" ${param.tipoFixo == 'DESCONTO' ? 'selected' : ''}>Desconto</option>
		</select><br>
		Descrição:<br>
		<input type="text" name="descricao" value="${param.descricao}" /><br>
		Valor:<br>
		<input type="text" name="valor" value="${param.valor}" /><br>
		Periodicidade:<br>
		<select name="periodicidade">
			<option value="SEMANAL" ${param.situacao == 'SEMANAL' ? 'selected' : ''}>Semanal</option>
			<option value="MENSAL" ${param.situacao == 'MENSAL' ? 'selected' : ''}>Mensal</option>
			<option value="ANUAL" ${param.situacao == 'ANUAL' ? 'selected' : ''}>Anual</option>
		</select><br>
		Situação:<br>
		<select name="situacao">
			<option value="ATIVO" ${param.situacao == 'ATIVO' ? 'selected' : ''}>Ativo</option>
			<option value="CANCELADO" ${param.situacao == 'CANCELADO' ? 'selected' : ''}>Cancelado</option>
		</select><br><br>
		
		<input type="submit" value="Salvar" onclick="acaoFormulario('salvar-fixo')" />&emsp;
		<input type="submit" value="Excluir" onclick="acaoFormulario('excluir-fixo?codigo=${param.codigo}')" />&emsp;
		<input type="submit" value="Voltar para cadastros" onclick="acaoFormulario('listar-fixos')" />
	</form>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>