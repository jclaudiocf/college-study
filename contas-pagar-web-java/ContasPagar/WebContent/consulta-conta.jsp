<%@page import="br.com.contaspagar.entidade.Credor"%>
<%@page import="br.com.contaspagar.dao.CredorDAO"%>
<%@page import="br.com.contaspagar.entidade.ContasPagar"%>
<%@page import="br.com.contaspagar.dao.ContasPagarDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="js/script.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Consulta de contas a pagar</title>
</head>
<body>

<%
	Integer codigo = Integer.valueOf(request.getParameter("codigo"));
	String nomeCredor = "";

	ContasPagarDAO contaDAO = new ContasPagarDAO();
	List<ContasPagar> lista = contaDAO.getLista(codigo);
	ContasPagar conta = lista.get(0);
	
	String valorBruto = conta.getValorBruto().toString().replace(".", ",");
	String valorDesconto = conta.getValorDesconto().toString().replace(".", ",");
	
	CredorDAO credorDAO = new CredorDAO();
	List<Credor> listaCredor = credorDAO.getLista(conta.getCodigoCredor());
	Credor credor = listaCredor.get(0);
	nomeCredor = credor.getNome();
%>

<form method="post" name="formConsulta" onsubmit="return validaConta(this);">
	<table width="60%" align="center" border="0">
		<caption>Consulta de Contas a Pagar</caption>
		<tr>
			<td>Conta</td>
			<td>
				<input name="codigoCredor" type="hidden" value="<%=conta.getCodigoCredor() %>" />
				<input name="codigo" type="text" maxlength="45" size="30" value="<%=conta.getCodigo() %>" />
			</td>
		</tr>
		<tr>
			<td>Credor</td>
			<td><input name="nomeCredor" type="text" maxlength="45" size="100" value="<%=nomeCredor %>" /></td>
		</tr>
		<tr>
			<td>Descrição</td>
			<td><input name="descricao" type="text" maxlength="45" size="30" value="<%=conta.getDescricao() %>" /></td>
		</tr>
		<tr>
			<td>Valor</td>
			<td><input name="valor" type="text" size="30" value="<%=valorBruto %>" maxlength="15" onkeypress="return formatar_moeda(this, '.', ',', event);" /></td>
		</tr>
		<tr>
			<td>Desconto</td>
			<td><input name="desconto" type="text" size="30" value="<%=valorDesconto %>" maxlength="15" onkeypress="return formatar_moeda(this, '.', ',', event);" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" name="btnSalvar" value="Salvar" onclick="salvarProva();" >  
				<input type="button" name="btnDeletar" value="Deletar" onclick="deletarProva();" >  
				<input type="button" name="btnVoltar" value="Voltar tela principal" onclick="formConsultaVoltarTelaPrincipal();" >
			</td>
		</tr>
	</table>
</form>

</body>
</html>