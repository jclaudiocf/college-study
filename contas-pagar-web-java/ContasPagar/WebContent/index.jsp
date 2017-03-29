<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri= "http://java.sun.com/jsp/jstl/functions" prefix= "fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">

<title>Contas a Pagar</title>
</head>
<body>

<form action="lista-credores.jsp" method="post">
	<table align="left" border="0">
		<tr>
			<td>
				<input type="submit" name="cadastrar" value="Credores" >
			</td>
		</tr>
	</table>
</form>

<form action="cadastro-conta.jsp" method="post">
	<table align="left" border="0">
		<tr>
			<td>
				<input type="submit" name="cadastrar" value="Cadastrar conta a pagar" >
			</td>
		</tr>
	</table>
</form>

<br clear="all" />

<jsp:useBean id="dao" class="br.com.contaspagar.dao.ContasPagarDAO"/>
<table width="90%" align="center" border="0" class=".table-caption">
	<tr>
		<th width="5%">Código</th>
		<th width="40%">Descricao</th>
		<th width="10%">Valor bruto</th>
		<th width="10%">Valor desconto</th>
		<th width="10%">Valor pago</th>
		<th width="5%">Ação</th>
	</tr>
	
	<c:forEach var="conta" items="${dao.lista}" varStatus="id">
		<tr bgcolor="#${id.count % 2 == 0 ? 'CCCCCC' : 'ffffff' }">
			<td align="center">${conta.codigo}</td>
			<td>${fn:substring(conta.descricao, 0, 20)}</td>
			<td>${fn:replace(conta.valorBruto, ".", ",")}</td>
			<td>${fn:replace(conta.valorDesconto, ".", ",")}</td>
			<td>${fn:replace(conta.valorPago, ".", ",")}</td>
			<td align="center"><a href="consulta-conta.jsp?codigo=${conta.codigo}" >Consultar</a></td>
		</tr>
	</c:forEach>
</table>

</body>
</html>