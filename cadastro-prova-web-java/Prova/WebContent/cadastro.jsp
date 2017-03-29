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

<title>Cadastro de provas</title>
</head>
<body>

<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime localData = LocalDateTime.now();
	String dataAtual = formatter.format(localData);
%>

<form method="post" name="frmCadasto" onsubmit="return validaCadastro(this);" enctype="multipart/form-data">
	<table width="60%" align="center" border="0">
		<caption>Cadastro de Prova</caption>
		<tr>
			<td>Nome</td>
			<td><input id="nome" name="nome" type="text" maxlength="200" size="100" value="" /></td>
		</tr>
		<tr>
			<td>Data</td>
			<td><input id="data" name="data" type="text" size="30" value="<%= dataAtual %>" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>Chave</td>
			<td><input id="chave" name="chave" type="text" maxlength="200" size="100" value="" /></td>
		</tr>
		<tr>
			<td>Prova</td>
			<td><textarea id="prova" name="prova" rows="20" cols="98"></textarea></td>
		</tr>
		<tr>
			<td>Observação</td>
			<td><textarea id="observacao" name="observacao" rows="10" cols="98"></textarea></td>
		</tr>
		<tr>
			<td>Imagem</td>
			<td><input id="imagem" name="imagem" type="file" size="100" value="" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" name="btnCadastrar" value="Cadastrar" onclick="cadastrarProva();" >  
				<input type="button" name="btnVoltar" value="Voltar tela principal" onclick="formCadastroVoltarTelaPrincipal();" >
			</td>
		</tr>
	</table>
</form>

</body>
</html>