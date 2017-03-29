<%@page import="br.com.contaspagar.dao.CredorDAO"%>
<%@page import="br.com.contaspagar.entidade.Credor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de conta</title>
</head>
<body>

<%
Integer codigo = Integer.valueOf(request.getParameter("codigo"));
Boolean cadastrado = false;

Credor credor = new Credor();
credor.setNome(request.getParameter("nome"));
credor.setEndereco(request.getParameter("endereco"));

CredorDAO credorDAO = new CredorDAO();

if (codigo > 0) {
	credor.setCodigo(codigo);
	cadastrado = credorDAO.alterar(credor);
} else {
	cadastrado = credorDAO.inserir(credor);
}
%>

<center>
	<c:set var="retorno" value="<%=cadastrado %>"/>
	
    <c:if test="${retorno==true}">
      Credor salvo com sucessso! <br>
    </c:if>
    
    <c:if test="${retorno==false}">
      Erro ao salvar credor! <br>
    </c:if>

	<a href='lista-credores.jsp'>Voltar a tela credores</a>
</center>

</body>
</html>