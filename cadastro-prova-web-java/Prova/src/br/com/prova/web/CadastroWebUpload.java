package br.com.prova.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.prova.dao.ProvaDAO;
import br.com.prova.entidade.Prova;

@WebServlet("/cadastrar-prova")
public class CadastroWebUpload extends HttpServlet {
	private static final long serialVersionUID = -8297418470533482420L;
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int THRESHOLD_SIZE 	= 1024 * 1024 * 3; 	// 3MB
	private static final int MAX_FILE_SIZE 		= 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE 	= 1024 * 1024 * 50; // 50MB
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Configura upload
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		// Diretório de destino do upload
		String uploadPath = getServletContext().getRealPath("")	+ File.separator + UPLOAD_DIRECTORY;
		
		// Cria o diretório caso não existir
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		Prova prova = new Prova();
		PrintWriter out = response.getWriter();
		try {
			// Trabalha as requisições
			List<?> formItems = upload.parseRequest(request);
			Iterator<?> iter = formItems.iterator();
			
			// Percore os campos do formulário
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				
				if (!item.isFormField()) {
					String fileName = new File(item.getName()).getName();
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					
					// Grava imagem em disco
					item.write(storeFile);
					prova.setImagem(fileName);
				}
				
				if (item.isFormField()) {  
	                if (item.getFieldName().equals("nome")) {   
	                	prova.setNome(item.getString());  
	                } else if (item.getFieldName().equals("data")) {
	                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	            		LocalDateTime localData = LocalDateTime.parse(item.getString(), formatter);
	            		prova.setData(localData);
	                } else if (item.getFieldName().equals("chave")) {   
	                	prova.setChave(item.getString());  
	                } else if (item.getFieldName().equals("prova")) {   
	                	prova.setProva(item.getString());  
	                } else if (item.getFieldName().equals("observacao")) {   
	                	prova.setObservacao(item.getString());
	                } 
	            }
			}
			
			ProvaDAO provaDAO = new ProvaDAO();
			if (provaDAO.inserir(prova)) {
		        out.println("<html>");
		        out.println("<body><center>");
		        out.println("Prova cadastrada com sucessso! <br>");
		        out.println("<a href='index.jsp'>Voltar a tela principal</a>");
		        out.println("</center></body>");
		        out.println("</html>");
			} else {
		        out.println("<html>");
		        out.println("<body><center>");
		        out.println("Erro ao cadastrar prova! <br>");
		        out.println("<a href='index.jsp'>Voltar a tela principal</a>");
		        out.println("</center></body>");
		        out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
