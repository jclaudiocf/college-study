package br.com.prova.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.prova.dao.ProvaDAO;
import br.com.prova.entidade.Prova;

@WebServlet("/deletar-prova")
public class DeletarWeb extends HttpServlet {
	private static final long serialVersionUID = -8297418470533482420L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer codigo = Integer.valueOf(request.getParameter("codigo"));
		
		Prova prova = new Prova();
		prova.setCodigo(codigo);
		
		PrintWriter out = response.getWriter();
		
		ProvaDAO provaDAO = new ProvaDAO();
		if (provaDAO.deletar(prova)) {
	        out.println("<html>");
	        out.println("<body><center>");
	        out.println("Prova deletada com sucessso! <br>");
	        out.println("<a href='index.jsp'>Voltar a tela principal</a>");
	        out.println("</center></body>");
	        out.println("</html>");
		} else {
	        out.println("<html>");
	        out.println("<body><center>");
	        out.println("Erro ao deletar prova! <br>");
	        out.println("<a href='index.jsp'>Voltar a tela principal</a>");
	        out.println("</center></body>");
	        out.println("</html>");
		}
	}
}
