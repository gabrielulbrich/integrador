package controllers;

import java.util.List;
import java.util.Vector;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PacienteDAO;
import models.ConexaoBD;
import models.Paciente_model;

@ManagedBean
@SessionScoped
public class Paciente extends HttpServlet {
	private Paciente_model model = new Paciente_model();
	private List<Paciente_model> lista = new Vector<>();
	private PacienteDAO pac = new PacienteDAO();

	

	@PostConstruct
	public void init() {
		lista = pac.selectPaciente();
		HeapSort ob = new HeapSort();
		ob.sort(lista);
	}

	public void cadastrar() {
		FacesContext context = FacesContext.getCurrentInstance();
		lista.add(model);
		if (pac.inserir(model))
			context.addMessage(null, new FacesMessage("Sucesso", "Paciente cadastrado com sucesso"));
		else
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Erro ao cadastrar"));	
		this.init();
	}

	public void editar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (pac.editarPaciente(model))
			context.addMessage(null, new FacesMessage("Sucesso", "Editado com sucesso"));
		else
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Escolha outra categoria"));			
		this.init();
	}

	public void atender() {
		FacesContext context = FacesContext.getCurrentInstance();
		Paciente_model primeiro = lista.get(0);
		if (pac.atenderPaciente(primeiro))
			context.addMessage(null, new FacesMessage("Sucesso", "Paciente " + primeiro.getNome() + " foi atendido"));
		else
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Não foi possível atender paciente"));
		this.init();
	}

	public void emergencia() {
		FacesContext context = FacesContext.getCurrentInstance();
		model.setNew_prioridade("400");
		if (pac.editarPaciente(model))
			context.addMessage(null, new FacesMessage("Sucesso", "Paciente movido para emergencia"));
		else
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Não foi possível mover paciente para emergencia"));
		this.init();
	}

	public Paciente_model getModel() {
		return model;
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
