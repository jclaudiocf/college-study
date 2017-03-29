<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Credores</title>
</head>
<body>

<form action="cadastro-credor.jsp" method="post">
	<table align="left" border="0">
		<tr>
			<td>
				<input type="submit" name="cadastrar" value="Cadastrar credor" >
			</td>
		</tr>
	</table>
</form>

<form action="index.jsp" method="post">
	<table align="left" border="0">
		<tr>
			<td>
				<input type="submit" name="voltar" value="Voltar tela principal" >
			</td>
		</tr>
	</table>
</form>

<br clear="all" />

<jsp:useBean id="dao" class="br.com.contaspagar.dao.CredorDAO"/>
<table width="90%" align="center" border="0" class=".table-caption">
	<tr>
		<th width="5%">Código</th>
		<th width="30%">Nome</th>
		<th width="60%">Endereço</th>
		<th width="5%">Ação</th>
	</tr>
	
	<c:forEach var="credor" items="${dao.lista}" varStatus="id">
		<tr bgcolor="#${id.count % 2 == 0 ? 'CCCCCC' : 'ffffff' }">
			<td align="center">${credor.codigo}</td>
			<td>${fn:substring(credor.nome, 0, 30)}</td>
			<td>${fn:substring(credor.endereco, 0, 50)}</td>
			<td align="center"><a href="consulta-credor.jsp?codigo=${credor.codigo}" >Consultar</a></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>