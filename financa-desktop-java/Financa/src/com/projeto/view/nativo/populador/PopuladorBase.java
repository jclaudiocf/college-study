package com.projeto.view.nativo.populador;

import com.projeto.controller.enums.EPeriodicidade;
import com.projeto.controller.enums.ETipoConta;
import com.projeto.controller.enums.ETipoFixo;
import com.projeto.controller.factorys.FactoryNegocio;
import com.projeto.model.tabelas.CentroCustos;
import com.projeto.model.tabelas.Contas;
import com.projeto.model.tabelas.Fixos;
import com.projeto.model.tabelas.Usuarios;

public class PopuladorBase {
	
	public void popular() {
		final Integer CODIGO_USUARIO = 1;
		
		// Usuário administrador
		Usuarios usuario = new Usuarios();
		usuario.setCodigo(CODIGO_USUARIO);
		usuario.setNome("Administrador");
		usuario.setLogin("adm");
		usuario.setSenha("adm");
		FactoryNegocio.adicionar(usuario);
		
		// Centro de custos
		CentroCustos centroCusto = new CentroCustos();
		centroCusto.setCodigoUsuario(CODIGO_USUARIO);
		centroCusto.setDescricao("Lazer");
		FactoryNegocio.adicionar(centroCusto);
		
		centroCusto.setDescricao("Viagem");
		FactoryNegocio.adicionar(centroCusto);
		
		centroCusto.setDescricao("Moradia");
		FactoryNegocio.adicionar(centroCusto);
		
		// Contas
		Contas conta = new Contas();
		conta.setCodigoUsuario(CODIGO_USUARIO);
		conta.setConta("1234");
		conta.setDigitoConta("0");
		conta.setAgencia("4321");
		conta.setDigitoAgencia("0");
		conta.setEnderecoAgencia("Rua XV");
		conta.setDescricao("Banco HSBC");
		conta.setSaldo(800D);
		conta.setTipoConta(ETipoConta.BANCO);
		FactoryNegocio.adicionar(conta);
		
		conta.setConta("6789");
		conta.setDigitoConta("0");
		conta.setAgencia("9876");
		conta.setDigitoAgencia("0");
		conta.setEnderecoAgencia("Rua Tamandaré");
		conta.setDescricao("Banco do Brasil");
		conta.setSaldo(300D);
		conta.setTipoConta(ETipoConta.BANCO);
		FactoryNegocio.adicionar(conta);
		
		conta.setConta("2345");
		conta.setDigitoConta("0");
		conta.setAgencia("5432");
		conta.setDigitoAgencia("0");
		conta.setEnderecoAgencia("Rua São Paulo");
		conta.setDescricao("Caixa");
		conta.setSaldo(300D);
		conta.setTipoConta(ETipoConta.BANCO);
		FactoryNegocio.adicionar(conta);
		
		conta.setConta("4433");
		conta.setDigitoConta("0");
		conta.setAgencia("3344");
		conta.setDigitoAgencia("0");
		conta.setEnderecoAgencia("Rua Paulista");
		conta.setDescricao("Carteira");
		conta.setSaldo(300D);
		conta.setTipoConta(ETipoConta.CAIXA);
		FactoryNegocio.adicionar(conta);
		
		// Fixos
		Fixos fixo = new Fixos();
		fixo.setCodigoUsuario(CODIGO_USUARIO);
		fixo.setDescricao("Aluguel");
		fixo.setPeriodicidade(EPeriodicidade.MENSAL);
		fixo.setTipoFixo(ETipoFixo.DESCONTO);
		fixo.setValor(800D);
		FactoryNegocio.adicionar(fixo);
		
		fixo.setCodigoUsuario(CODIGO_USUARIO);
		fixo.setDescricao("Condomínio");
		fixo.setPeriodicidade(EPeriodicidade.MENSAL);
		fixo.setTipoFixo(ETipoFixo.DESCONTO);
		fixo.setValor(200D);
		FactoryNegocio.adicionar(fixo);
		
		fixo.setCodigoUsuario(CODIGO_USUARIO);
		fixo.setDescricao("Salário");
		fixo.setPeriodicidade(EPeriodicidade.MENSAL);
		fixo.setTipoFixo(ETipoFixo.PROVENTO);
		fixo.setValor(2500D);
		FactoryNegocio.adicionar(fixo);
	}
}
