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
	Cadastro de centro custo
</div>

<div id="section">
	<form name="cadastro" method="post">
		Código:<br>
		<input type="hidden" name="codigo" value="${param.codigo}" />
		<input type="hidden" name="codigoUsuario" value="${param.codigoUsuario}" />
		<input type="text" value="${param.codigo}" disabled="disabled" /><br>
		Descrição:<br>
		<input type="text" name="descricao" value="${param.descricao}" /><br>
		Situação:<br>
		<select name="situacao">
			<option value="ATIVO" ${param.situacao == 'ATIVO' ? 'selected' : ''}>Ativo</option>
			<option value="CANCELADO" ${param.situacao == 'CANCELADO' ? 'selected' : ''}>Cancelado</option>
		</select><br><br>
		
		<input type="submit" value="Salvar" onclick="acaoFormulario('salvar-centrocusto')" />&emsp;
		<input type="submit" value="Excluir" onclick="acaoFormulario('excluir-centrocusto?codigo=${param.codigo}')" />&emsp;
		<input type="submit" value="Voltar para cadastros" onclick="acaoFormulario('listar-centrocustos')" />
	</form>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>