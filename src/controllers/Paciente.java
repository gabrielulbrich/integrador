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
	private List<Paciente_model> models = new ArrayList<>();
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
		models.add(model);
		pac.Inserir(model);
		model = new Paciente_model();
	}
	
	public void Listar() {
		models = pac.Buscar();
	}

	public Paciente_model getModel() {
		return model;
	}

	public void Editar() {
		models.add(model);
		pac.Mudar(model);
		model = new Paciente_model();
		
	}
	
	public void setModel(Paciente_model model) {
		this.model = model;
	}

	public List<Paciente_model> getModels() {
		return models;
	}

	public void setModels(List<Paciente_model> models) {
		this.models = models;
	}
	
	

}
