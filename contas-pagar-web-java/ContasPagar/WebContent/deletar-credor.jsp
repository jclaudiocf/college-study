<%@page import="br.com.contaspagar.dao.CredorDAO"%>
<%@page import="br.com.contaspagar.entidade.Credor"%>
<%@page import="br.com.contaspagar.dao.ContasPagarDAO"%>
<%@page import="br.com.contaspagar.entidade.ContasPagar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete de credor</title>
</head>
<body>

<%
Boolean deletado = false;

Credor credor = new Credor();
credor.setCodigo(Integer.valueOf(request.getParameter("codigo")));

CredorDAO credorDAO = new CredorDAO();
deletado = credorDAO.deletar(credor);
%>

<center>
	<c:set var="retorno" value="<%=deletado %>"/>
	
	<c:if test="${retorno=true}">
      Credor deletado com sucessso! <br>
    </c:if>
    
    <c:if test="${retorno=false}">
      Erro ao deletar credor! <br>
    </c:if>

	<a href='lista-credores.jsp'>Voltar a tela credores</a>
</center>

</body>
</html>