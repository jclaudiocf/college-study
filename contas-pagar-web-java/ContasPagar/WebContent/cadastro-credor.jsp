<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="js/script.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Cadastro de credor</title>
</head>
<body>

<form method="post" name="frmCadastro" onsubmit="return validaCredor(this);">
	<table width="60%" align="center" border="0">
		<caption>Cadastro de Credor</caption>
		<tr>
			<td>Nome</td>
			<td>
				<input name="codigo" type="hidden" value="0" />
				<input name="nome" type="text" maxlength="45" size="100" value="" />
			</td>
		</tr>
		<tr>
			<td>Endereço</td>
			<td><input name="endereco" type="text" maxlength="100" size="100" value="" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" name="btnCadastrar" value="Cadastrar" onclick="cadastrarCredor();" > 
				<input type="button" name="btnVoltar" value="Voltar tela credores" onclick="formCadastroVoltarCredores();" >
			</td>
		</tr>
	</table>
</form>

</body>
</html>