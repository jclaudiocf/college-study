<%@page import="br.com.contaspagar.entidade.Credor"%>
<%@page import="br.com.contaspagar.dao.CredorDAO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="js/script.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Consulta de credores</title>
</head>
<body>

<%
	Integer codigo = Integer.valueOf(request.getParameter("codigo"));
	CredorDAO credorDAO = new CredorDAO();
	List<Credor> lista = credorDAO.getLista(codigo);
	Credor credor = lista.get(0);
%>

<form method="post" name="formConsulta" onsubmit="return validaCredor(this);">
	<table width="60%" align="center" border="0">
		<caption>Consulta de Credor</caption>
		<tr>
			<td>Código</td>
			<td><input name="codigo" type="text" maxlength="45" size="30" value="<%=credor.getCodigo() %>" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>Nome</td>
			<td><input name="nome" type="text" maxlength="45" size="100" value="<%=credor.getNome() %>" /></td>
		</tr>
		<tr>
			<td>Endereço</td>
			<td><input name="endereco" type="text" maxlength="100" size="100" value="<%=credor.getEndereco() %>" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" name="btnSalvar" value="Salvar" onclick="salvarCredor();" >  
				<input type="button" name="btnDeletar" value="Deletar" onclick="deletarCredor();" >  
				<input type="button" name="btnVoltar" value="Voltar tela credores" onclick="formConsultaVoltarCredores();" >
			</td>
		</tr>
	</table>
</form>

</body>
</html>