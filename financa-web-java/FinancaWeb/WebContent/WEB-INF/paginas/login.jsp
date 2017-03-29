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
			<h2>Finança pessoal</h2>
			<form action="login" method="post">
				<input type="text" name="login" placeholder="Login" />
				<input type="password" name="senha" placeholder="Senha" />
				<button>Login</button>
			</form>
		</div>
	</div>

	<script src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/da0415260bc83974687e3f9ae.js" />"></script>
	<script src="<c:url value="/resources/js/index.js" />"></script>
</body>
</html>
