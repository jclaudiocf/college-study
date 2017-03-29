<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Finança pessoal</title>
	<link rel="icon" href="<c:url value="/resources/img/financa.png" />"/>
	<link href="<c:url value="/resources/css/padrao.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/default.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/component.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/modernizr.custom.js" />"></script>
	<script src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
</head>
<body>
	<div class="container">

		<header class="clearfix">
			<span>Finança pessoal</span>
		</header>

		<div class="main">
			<nav id="cbp-hrmenu" class="cbp-hrmenu">
				<ul>
					<li class="cbp-hropen">
					    <a href="#">Menu</a>
						<div class="cbp-hrsub">
							<div class="cbp-hrsub-inner">
								<div>
									<h4>Cadastros</h4>
									<ul>
										<li><a href="cadastrar-usuario" target="pagina" class="jw-submenu">Usuários</a></li>
										<li><a href="cadastrar-centrocusto" target="pagina" class="jw-submenu">Centro de custos</a></li>
										<li><a href="cadastrar-conta" target="pagina" class="jw-submenu">Contas</a></li>
										<li><a href="cadastrar-fixo" target="pagina" class="jw-submenu">Fixos</a></li>
									</ul>
									<h4>Ajuda</h4>
									<ul>
										<li><a href="https://docs.google.com/document/d/112eSTXWISL1ACphoPh2tmsEAr0IKnxwPfOoXQYpiPFc/edit?usp=sharing" target="_blank" class="jw-submenu">Documentação</a></li>
										<li><a href="sobre" target="pagina" class="jw-submenu">Sobre o sistema</a></li>
									</ul>
								</div>
								<div>
									<h4>Lançamentos</h4>
									<ul>
										<li><a href="cadastrar-movimento" target="pagina" class="jw-submenu">Movimentos</a></li>
										<li><a href="construcao" target="pagina" class="jw-submenu">Quitar parcelas</a></li>
										<li><a href="construcao" target="pagina" class="jw-submenu">Gerar fixos</a></li>
									</ul>
								</div>
								<div>
									<h4>Relatórios</h4>
									<ul>
										<li><a href="relatorio-saldo" target="pagina" class="jw-submenu">Saldo</a></li>
										<li><a href="construcao" target="pagina" class="jw-submenu">Extrato</a></li>
									</ul>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div id="logout"><a href="logout">Sair</a></div>
					</li>
				</ul>
			</nav>
		</div>
	</div>

	<script src="<c:url value="/resources/js/cbpHorizontalMenu.js" />"></script>
	<script type="text/javascript">
		// inicializa o menu principal
		$(function() {
			cbpHorizontalMenu.init();
		});
		
		// adiciona evento aos submenus
		$('.jw-submenu').click(function(event) {
		  $( '#cbp-hrmenu > ul > li' ).removeClass('cbp-hropen');
		  current = -1;
		})
	</script>

	<div>
		<iframe id="pagina" name="pagina" scrolling="no" frameborder="0" width="100%" height="600px"></iframe>
	</div>

</body>
</html>
