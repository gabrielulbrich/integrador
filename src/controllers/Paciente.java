package controllers;

import java.util.List;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ConexaoBD;
import models.Paciente_model;
import models.PacienteDAO;

@ManagedBean
@SessionScoped
public class Paciente extends HttpServlet{
	private Paciente_model model = new Paciente_model();
	private List<Paciente_model> lista = new ArrayList<>();
	private PacienteDAO pac = new PacienteDAO();
	

    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	HttpSession session = request.getSession();
//    	boolean logado = Boolean.valueOf(String.valueOf(session.getAttribute("logado")));
//    	if(logado == true) {
//	    	request.setAttribute("views", "paciente_cadastro.xhtml");
//	    	request.getRequestDispatcher("/views/layout.xhtml").forward(request, response);
//    	}else {
//    		response.sendRedirect("/Integrador/login");
//    	}
//    }
    
	
	public void adicionar() {
		lista.add(model);
		pac.inserir(model);
		model = new Paciente_model();
	}
	
	public void Listar() {
		lista = pac.Buscar();
	}

	public Paciente_model getModel() {
		return model;
	}

	public void Editar() {
		lista.add(model);
		pac.Mudar(model);
		model = new Paciente_model();
		
	}
	
	public void setModel(Paciente_model model) {
		this.model = model;
	}

	public List<Paciente_model> getlista() {
		return lista;
	}

	public void setlista(List<Paciente_model> lista) {
		this.lista = lista;
	}
	
	

}
