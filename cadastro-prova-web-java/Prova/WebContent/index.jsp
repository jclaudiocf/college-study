<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Provas</title>
</head>
<body>

<form action="cadastro.jsp" method="post" name="frmAcao">
	<table width="60%" align="center" border="0">
		<tr>
			<td><input type="submit" name="cadastrar" value="Cadastrar prova" ></td>
			<td><a href="Prova.zip">[Link para donwload do workspace]</a></td>
			<td><a href="https://github.com/jclaudiocf/cadastro-prova" target="_blank">[Link para o workspace no GitHub]</a></td>
		</tr>
	</table>
</form>

<br>

<jsp:useBean id="dao" class="br.com.prova.dao.ProvaDAO"/>
<table width="90%" align="center" border="0" class=".table-caption">
	<tr>
		<th width="5%">Código</th>
		<th width="30%">Nome</th>
		<th width="15%">Data</th>
		<th width="35%">Observação</th>
		<th width="15%">Ação</th>
	</tr>
	
	<c:forEach var="prova" items="${dao.lista}" varStatus="id">
		<tr bgcolor="#${id.count % 2 == 0 ? 'CCCCCC' : 'ffffff' }">
			<td align="center">${prova.codigo}</td>
			<td>${fn:substring(prova.nome, 0, 20)}</td>
			<td>${prova.dataString}</td>
			<td>${fn:substring(prova.observacao, 0, 25)}</td>
			<td align="center">
			<a href="consulta.jsp?codigo=${prova.codigo}" >Consultar</a></td>
		</tr>
	</c:forEach>
	
</table>

</body>
</html>