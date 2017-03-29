<%@page import="br.com.contaspagar.dao.ContasPagarDAO"%>
<%@page import="br.com.contaspagar.entidade.ContasPagar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete de conta</title>
</head>
<body>

<%
Boolean deletado = false;

ContasPagar conta = new ContasPagar();
conta.setCodigo(Integer.valueOf(request.getParameter("codigo")));

ContasPagarDAO contaDAO = new ContasPagarDAO();
deletado = contaDAO.deletar(conta);
%>

<center>
	<c:set var="retorno" value="<%=deletado %>"/>
	
	<c:if test="${retorno=true}">
      Conta deletada com sucessso! <br>
    </c:if>
    
    <c:if test="${retorno=false}">
      Erro ao deletar conta! <br>
    </c:if>

	<a href='index.jsp'>Voltar a tela principal</a>
</center>

</body>
</html>