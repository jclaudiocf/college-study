<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta charset="ISO-8859-1">
	<title>Finança pessoal</title>
	<link rel="icon" href="<c:url value="/resources/img/financa.png" />"/>
	<link href="<c:url value="/resources/css/reset.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
	
	<link rel='stylesheet prefetch'	href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
	<link rel='stylesheet prefetch'	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
</head>

<body>
	<div class="pen-title"></div>
	<div class="module form-module">
		<div class="toggle"></div>
		<div class="form">
			<h2>Configuração da base</h2>
			<form action="salvar-testar-configuracao" method="post">
				<input type="text" name="usuario" placeholder="root" />
				<input type="password" name="senha" placeholder="123456" />
				<input type="text" name="porta" placeholder="3306" />
				<input type="text" name="host" placeholder="locahost" />
				Deseja popular a base de dados?
				<select name="popular">
					<option value="S">Sim</option>
					<option value="N" selected="selected">Não</option>
				</select><br><br>
				
				<button>Salvar e Testar</button>
			</form>
		</div>
	</div>

	<script src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/da0415260bc83974687e3f9ae.js" />"></script>
	<script src="<c:url value="/resources/js/index.js" />"></script>
</body>
</html>
