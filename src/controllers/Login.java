package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.LoginModel;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	LoginModel model = new LoginModel();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Preprocess request: we actually don't need to do any business stuff, so just display JSP.
        request.getRequestDispatcher("/views/login.xhtml").forward(request, response);
        //login.Salvar();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	
    	model.setLogin(request.getParameter("login"));
    	String senha = request.getParameter("senha");
    	
    	if(model.LoginExiste()) {    		
    		if(verificaSenha(senha)) {
    			session.setAttribute("nomeUsuario", model.getNome());
    			session.setAttribute("logado", true);
    			response.sendRedirect("/integrador/dashboard");
    		}else {
    			invalidaSessao(request, response);
    		}
    	}else {
    		invalidaSessao(request, response);
    	}	
    }
    
    private boolean verificaSenha(String senha) {
    	//model.getSenha();
    	String senhaInput = this.hashSenha(senha);
    	String senhaBanco = model.getSenha();
    	
    	if(senhaInput.equals(senhaBanco)) {
    		return true;
    	}else {
    		return false;
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
		request.setAttribute("mensagem", "Erro ao logar-se");
		request.getRequestDispatcher("views/login.xhtml").forward(request, response);
	}

}