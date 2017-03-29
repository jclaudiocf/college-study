function validaConta(formValida) {
	if (formValida.nomeCredor.value == '') {
		alert("O campo 'nomeCredor' é obrigatório.");
		formValida.nomeCredor.focus();
		return false;
	} else {
		return true;
	}
}

function cadastrarConta() {
	document.frmCadastro.action = 'cadastrar-conta.jsp';
	document.frmCadastro.submit();
}

function deletarProva() {
	document.formConsulta.action = 'deletar-conta.jsp';
	document.formConsulta.submit();
}

function salvarProva() {
	document.formConsulta.action = 'cadastrar-conta.jsp';
	document.formConsulta.submit();
}

function validaCredor(frmCadastro) {
	if (frmCadastro.nome.value == '') {
		alert("O campo 'nome' é obrigatório.");
		frmCadastro.nome.focus();
		return false;
	} else {
		return true;
	}
}

function cadastrarCredor() {
	document.frmCadastro.action = 'cadastrar-credor.jsp';
	document.frmCadastro.submit();
}

function deletarCredor() {
	document.formConsulta.action = 'deletar-credor.jsp';
	document.formConsulta.submit();
}

function salvarCredor() {
	document.formConsulta.action = 'cadastrar-credor.jsp';
	document.formConsulta.submit();
}

function formConsultaVoltarTelaPrincipal() {
	document.formConsulta.action = 'index.jsp';
	document.formConsulta.submit();
}

function formCadastroVoltarTelaPrincipal() {
	document.frmCadastro.action = 'index.jsp';
	document.frmCadastro.submit();
}

function formConsultaVoltarCredores() {
	document.formConsulta.action = 'lista-credores.jsp';
	document.formConsulta.submit();
}

function formCadastroVoltarCredores() {
	document.frmCadastro.action = 'lista-credores.jsp';
	document.frmCadastro.submit();
}

/* Autor: Mario Costa */
function formatar_moeda(campo, separador_milhar, separador_decimal, tecla) {
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? tecla.which : tecla.keyCode;

	if (whichCode == 13) return true; // Tecla Enter
	if (whichCode == 8) return true; // Tecla Delete
	key = String.fromCharCode(whichCode); // Pegando o valor digitado
	if (strCheck.indexOf(key) == -1) return false; // Valor inválido (não inteiro)
	len = campo.value.length;
	for(i = 0; i < len; i++)
	if ((campo.value.charAt(i) != '0') && (campo.value.charAt(i) != separador_decimal)) break;
	aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(campo.value.charAt(i))!=-1) aux += campo.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0) campo.value = '';
	if (len == 1) campo.value = '0'+ separador_decimal + '0' + aux;
	if (len == 2) campo.value = '0'+ separador_decimal + aux;

	if (len > 2) {
		aux2 = '';

		for (j = 0, i = len - 3; i >= 0; i--) {
			if (j == 3) {
				aux2 += separador_milhar;
				j = 0;
			}
			aux2 += aux.charAt(i);
			j++;
		}

		campo.value = '';
		len2 = aux2.length;
		for (i = len2 - 1; i >= 0; i--)
		campo.value += aux2.charAt(i);
		campo.value += separador_decimal + aux.substr(len - 2, len);
	}

	return false;
}