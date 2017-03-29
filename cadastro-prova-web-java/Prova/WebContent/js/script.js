function validaCadastro(frmCadasto) {
	if(frmCadasto.nome.value == '') {
		alert("O campo nome é obrigatório.");
		frmCadasto.nome.focus();
		return false;
	}
	
	return true;
}

function cadastrarProva() {
	document.frmCadasto.action = 'cadastrar-prova';
	document.frmCadasto.submit();
}

function deletarProva() {
	document.formConsulta.action = 'deletar-prova';
	document.formConsulta.submit();
}

function salvarProva() {
	document.formConsulta.action = 'salvar-prova';
	document.formConsulta.submit();
}

function formConsultaVoltarTelaPrincipal() {
	document.formConsulta.action = 'index.jsp';
	document.formConsulta.submit();
}

function formCadastroVoltarTelaPrincipal() {
	document.frmCadasto.action = 'index.jsp';
	document.frmCadasto.submit();
}