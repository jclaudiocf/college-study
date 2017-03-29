<%@page import="java.util.List"%>
<%@page import="br.com.prova.entidade.Prova"%>
<%@page import="br.com.prova.dao.ProvaDAO"%>
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

<title>Consulta de provas</title>
</head>
<body>

<%
	Integer codigo = Integer.valueOf(request.getParameter("codigo"));
	ProvaDAO provaDAO = new ProvaDAO();
	List<Prova> lista = provaDAO.getLista(codigo);
	Prova prova = lista.get(0);
	String imagem =  "/upload/" + prova.getImagem();
%>

<form method="post" name="formConsulta" onsubmit="return validaCadastro(this);">
	<table width="60%" align="center" border="0">
		<caption>Consulta de Prova</caption>
		<tr>
			<td>Código</td>
			<td><input id="codigo" name="codigo" type="text" size="30" value="<%= prova.getCodigo() %>" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>Nome</td>
			<td><input id="nome" name="nome" type="text" maxlength="200" size="100" value="<%= prova.getNome() %>" /></td>
		</tr>
		<tr>
			<td>Data</td>
			<td><input id="data" name="data" type="text" size="30" value="<%= prova.getDataString() %>" readonly="readonly" /></td>
		</tr>
		<tr>
			<td>Chave</td>
			<td><input id="chave" name="chave" type="text" maxlength="200" size="100" value="<%= prova.getChave() %>" /></td>
		</tr>
		<tr>
			<td>Prova</td>
			<td><textarea id="prova" name="prova" rows="20" cols="98"><%= prova.getProva() %></textarea></td>
		</tr>
		<tr>
			<td>Observação</td>
			<td><textarea id="observacao" name="observacao" rows="10" cols="98"><%= prova.getObservacao() %></textarea></td>
		</tr>
		<tr>
			<td>Imagem</td>
			<td align="center"><img src="${pageContext.request.contextPath}<%= imagem %>" width="200" height="200" /></td>
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