package com.projeto.controller.conexao.configuracao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ArquivoPropriedades {
	
	private final static File ARQUIVO_PROPRIEDADES = new File("./configuracoes.properties");
	private Properties propriedades = new Properties();
	
	public ArquivoPropriedades() {
		try {
			propriedades.load(new FileInputStream(getArquivoPropriedades()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private File getArquivoPropriedades() {
		File arquivo = getArquivoConfiguracao();
		if (!arquivo.exists()) {
			criarConfiguracoes(arquivo);
		}
		return arquivo;
	}
	
	private void criarConfiguracoes(File arquivo) {
		propriedades.put("servidor.banco.homologado", Configuracoes.BANCO_HOMOLOGADO);
		propriedades.put("servidor.banco.dados.ip", Configuracoes.BANCO_IP);
		propriedades.put("servidor.banco.dados.porta", Configuracoes.BANCO_PORTA);
		propriedades.put("banco.dados.nome", Configuracoes.BANCO_NOME);
		propriedades.put("banco.dados.usuario.nome", Configuracoes.BANCO_USUARIO);
		propriedades.put("banco.dados.usuario.senha", Configuracoes.BANCO_SENHA);
		try {  
		    FileOutputStream propriedadesPadrao = new FileOutputStream(arquivo);  
		    propriedades.store(propriedadesPadrao, "Configurações de acesso sistema Finança");  
		    propriedadesPadrao.close();  
		    getArquivoPropriedades();
		} catch (IOException e) {  
		    e.printStackTrace();  
		}
	}
	
	public static void salvarConfiguracoes(Properties propriedades) {
		try {  
		    FileOutputStream propriedadesPadrao = new FileOutputStream(getArquivoConfiguracao());  
		    propriedades.store(propriedadesPadrao, "Configurações de acesso sistema Finança");
		    propriedadesPadrao.close();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}
	}
	
	public static File getArquivoConfiguracao() {
		return ARQUIVO_PROPRIEDADES;
	}
	
	public Properties getPropriedades() {
		return propriedades;
	}
}
