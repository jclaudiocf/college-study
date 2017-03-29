package br.com.financaweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.projeto.controller.conexao.TesteConexao;

public class AutenticacaoInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		
		if (uri.endsWith("login")) {
			if (!TesteConexao.conexaoEstahAtiva()) {
				response.sendRedirect("configuracao");
			}
			return true;
		}
		
		if (uri.endsWith("configuracao") || uri.endsWith("logout") || uri.contains("resources")) {
			return true;
		}
		
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}
		
		response.sendRedirect("login");
		return false;
	}
}
