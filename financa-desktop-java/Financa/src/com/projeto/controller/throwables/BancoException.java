package com.projeto.controller.throwables;

import com.projeto.controller.enums.EBancoErro;

public class BancoException extends Exception {

	/**
	 * Sereal
	 */
	private static final long serialVersionUID = 1L;
	
	public BancoException(EBancoErro aMensagem, String aDetalhes) {
		super(aMensagem.getDescricao().concat("\nDetalhes: ").concat(aDetalhes));
	}

}
