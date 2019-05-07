package controllers;

import java.util.List;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import models.ConexaoBD;
import models.NewRegisterModel;
import models.PacienteDAO;

@ManagedBean
@SessionScoped
public class CadastradosBean {
	private NewRegisterModel model = new NewRegisterModel();
	private List<NewRegisterModel> models = new ArrayList<>();
	private PacienteDAO pac = new PacienteDAO();
	
	public void adicionar() {
		models.add(model);
		pac.Inserir(model);
		model = new NewRegisterModel();
	}
	
	public void Listar() {
		models = pac.Buscar();
	}

	public NewRegisterModel getModel() {
		return model;
	}

	public void Editar() {
		models.add(model);
		pac.Mudar(model);
		model = new NewRegisterModel();
		
	}
	
	public void setModel(NewRegisterModel model) {
		this.model = model;
	}

	public List<NewRegisterModel> getModels() {
		return models;
	}

	public void setModels(List<NewRegisterModel> models) {
		this.models = models;
	}
	
	

}
