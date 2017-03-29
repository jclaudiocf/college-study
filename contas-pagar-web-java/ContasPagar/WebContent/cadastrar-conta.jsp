<%@page import="br.com.contaspagar.dao.ContasPagarDAO"%>
<%@page import="br.com.contaspagar.entidade.ContasPagar"%>
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

ContasPagar conta = new ContasPagar();
conta.setCodigoCredor(Integer.valueOf(request.getParameter("codigoCredor")));
conta.setDescricao(request.getParameter("descricao"));

String valor = request.getParameter("valor").replace(".", "").replace(",", ".");
conta.setValorBruto(Double.valueOf(valor));

valor = request.getParameter("desconto").replace(".", "").replace(",", ".");
conta.setValorDesconto(Double.valueOf(valor));

ContasPagarDAO contaDAO = new ContasPagarDAO();

if (codigo > 0) {
	conta.setCodigo(codigo);
	cadastrado = contaDAO.alterar(conta);
} else {
	cadastrado = contaDAO.inserir(conta);
}
%>

<center>
	<c:set var="retorno" value="<%=cadastrado %>"/>
	
    <c:if test="${retorno==true}">
      Conta salva com sucessso! <br>
    </c:if>
    
    <c:if test="${retorno==false}">
      Erro ao salvar conta! <br>
    </c:if>

	<a href='index.jsp'>Voltar a tela principal</a>
</center>

</body>
</html>