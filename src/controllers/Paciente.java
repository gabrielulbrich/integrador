package controllers;

import java.util.List;
import java.util.Vector;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
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
	private List<Paciente_model> lista = new Vector<>();
	private PacienteDAO pac = new PacienteDAO();	

    @PostConstruct
    public void init() {    	
    	lista = pac.selectPaciente();
        HeapSort ob = new HeapSort(); 
        ob.sort(lista);    	
    }
    
	
	public void adicionar() {
		lista.add(model);
		pac.inserir(model);
		model = new Paciente_model();
	}
	
	public void listar() {
		lista = pac.selectPaciente();
	}

	public Paciente_model getModel() {
		return model;
	}

	public void Editar() {
		pac.editarPaciente(model);		
		model = new Paciente_model();
		this.listar();
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
