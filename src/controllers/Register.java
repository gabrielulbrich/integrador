package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import models.ConexaoBD;
import models.RegisterModel;

@WebServlet("/register")
public class Register extends HttpServlet {
	RegisterModel model = new RegisterModel();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("views/register.xhtml").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//verifica confirmação de senha
    	String senha = request.getParameter("senha");
    	String senha_confirmacao = request.getParameter("senha_confirmacao");
    	if(senha.equals(senha_confirmacao)) {		
        	model.setNome(request.getParameter("nome"));
        	model.setLogin(request.getParameter("login"));
        	model.setEmail(request.getParameter("email"));   
        	model.setSenha(this.hashSenha(senha)); //HASH SENHA
  
			if (model.Salvar()) {
				HttpSession session = request.getSession();
				session.setAttribute("mensagem", model.getNome()+" cadastrado com sucesso!");
				response.sendRedirect("/Integrador/login");
			} else {
				invalidaSessao(request, response);
			}
    	}else {
    		invalidaSessao(request, response);
    	}
    }
    
    public String hashSenha(String senha) { //HASH SENHA
    	MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	byte messageDigest[] = null;
		try {
			messageDigest = algorithm.digest(senha.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
    	  hexString.append(String.format("%02X", 0xFF & b));
    	}
    	String senhaHash = hexString.toString();
    	return senhaHash;
    }
    
	public static void invalidaSessao(HttpServletRequest 
			request, HttpServletResponse response) 
					throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		request.setAttribute("mensagem", "Erro ao se cadastrar");
		request.getRequestDispatcher("views/register.xhtml").forward(request, response);
	}
  
}