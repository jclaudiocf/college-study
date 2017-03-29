<%@page import="com.projeto.model.dao.ContaDAO"%>
<%@page import="com.projeto.model.tabelas.Contas"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.projeto.model.tabelas.Movimentos"%>
<%@page import="java.util.List"%>
<%@page import="com.projeto.model.tabelas.CentroCustos"%>
<%@page import="com.projeto.model.dao.CentroCustoDAO"%>
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

<% 

Usuarios usuarioLogado = (Usuarios) request.getSession().getAttribute("usuarioLogado"); 

CentroCustos centroCustoLogado = new CentroCustos();
centroCustoLogado.setCodigo(0);
centroCustoLogado.setCodigoUsuario(usuarioLogado.getCodigo());

CentroCustoDAO centroCustoDAO = new CentroCustoDAO();
List<CentroCustos> listaCentroCustos = new ArrayList<>(centroCustoDAO.listar(centroCustoLogado).values());

Contas contaLogada = new Contas();
contaLogada.setCodigo(0);
contaLogada.setCodigoUsuario(usuarioLogado.getCodigo());

ContaDAO contaDAO = new ContaDAO();
List<Contas> listaContas = new ArrayList<>(contaDAO.listar(contaLogada).values());

%>

<div id="header">
	Cadastro de movimento
</div>

<div id="section">
	<form name="cadastro" method="post">
		<input type="hidden" name="codigo" value="0" />
		<input type="hidden" name="codigoUsuario" value="<%=usuarioLogado.getCodigo() %>" />
		Centro de custo:<br>
		<select name="codigoCentroCusto">
			<c:forEach items="<%=listaCentroCustos%>" var="centroCusto">
				<option value="${centroCusto.codigo}">${centroCusto.descricao}</option>
			</c:forEach>
		</select><br>
		Conta:<br>
		<select name="codigoConta">
			<c:forEach items="<%=listaContas%>" var="conta">
				<option value="${conta.codigo}">${conta.descricao}</option>
			</c:forEach>
		</select><br>
		Tipo do movimento:<br>
		<select name="tipoMovimento">
			<option value="CREDITO_A_VISTA">Crédito a vista</option>
			<option value="DEBITO_A_VISTA">Débito a vista</option>
			<option value="CREDITO_A_PRAZO">Crédito a prazo</option>
			<option value="DEBITO_A_PRAZO">Débito a prazo</option>
			<option value="QUITACAO_DE_CREDITO">Quitação de crédito</option>
			<option value="QUITACAO_DE_DEBITO">Quitação de débito</option>
		</select><br>
		Descrição:<br>
		<input type="text" name="descricao" /><br>
		<!-- Data do lançamento:<br>
		<input type="text" name="dataLancamento" /><br> -->
		Valor:<br>
		<input type="text" name="valor" value="" onkeypress="return formatar_moeda(this, '', '.', event);" /><br>
		Parcelado:<br>
		<select name="parcelado">
			<option value="SIM">Sim</option>
			<option value="NAO">Não</option>
		</select><br>
		Situação:<br>
		<select name="situacao">
			<option value="ATIVO">Ativo</option>
			<option value="CANCELADO">Cancelado</option>
		</select><br><br>
		
		<input type="submit" value="Salvar" onclick="acaoFormulario('salvar-movimento')" />&emsp;
		<input type="submit" value="Listar cadastros" onclick="acaoFormulario('listar-movimentos')" />
	</form>
</div>

<div id="footer">
	Copyright © Alessandra Pereira &amp; Claudio Costa
</div>

</body>
</html>