package com.projeto.controller.interfaces;

import java.sql.Connection;
import java.util.Properties;

public interface IConexao {
	
	public Connection getConexao(Properties propriedades);

}
