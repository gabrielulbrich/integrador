package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	boolean logado = Boolean.valueOf(String.valueOf(session.getAttribute("logado")));
    	if(logado == true) {
	    	request.setAttribute("views", "dashboard.xhtml");
	    	request.getRequestDispatcher("/views/layout.xhtml").forward(request, response);
    	}else {
    		response.sendRedirect("/Integrador/login");
    	}
    }
}