<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDateTime"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>

<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="js/script.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Cadastro de contas a pagar</title>
</head>
<body>

<jsp:useBean id="dao" class="br.com.contaspagar.dao.CredorDAO"/>
<form method="post" name="frmCadastro" onsubmit="return validaConta(this);">
	<table width="60%" align="center" border="0">
		<caption>Contas a Pagar</caption>
		<tr>
			<td>Credor</td>
			<td>
				<input name="codigo" type="hidden" value="0" />
				<select name="codigoCredor">
					<c:forEach var="credor" items="${dao.lista}" varStatus="id">
						<option value="${credor.codigo}">${credor.codigo} - ${credor.nome} </option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Descrição</td>
			<td><input name="descricao" type="text" size="30" value="" maxlength="100" /></td>
		</tr>
		<tr>
			<td>Valor</td>
			<td><input name="valor" type="text" size="30" value="" maxlength="15" onkeypress="return formatar_moeda(this, '.', ',', event);" /></td>
		</tr>
		<tr>
			<td>Desconto</td>
			<td><input name="desconto" type="text" size="30" value="" maxlength="15" onkeypress="return formatar_moeda(this, '.', ',', event);" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" name="btnCadastrar" value="Cadastrar" onclick="cadastrarConta();" > 
				<input type="button" name="btnVoltar" value="Voltar tela principal" onclick="formCadastroVoltarTelaPrincipal();" >
			</td>
		</tr>
	</table>
</form>

</body>
</html>