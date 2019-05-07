package views;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import controllers.CadastradosBean;
import models.PacienteDAO;

@ManagedBean
@ViewScoped
public class ViewPaciente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cod_paciente;
	private String nome;
	private String cpf;
	private String idade;
	private String sexo;
	private String endereco;
	private String telefone;
	private String datacadastro;
	private String horacadastro;
	private String prioridade;
	
	public void deletarPaciente(int cod_paciente) {
        PacienteDAO.deletarPaciente(cod_paciente);
    	FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage("Paciente deletado com sucesso!");
		facesContext.addMessage(null, facesMessage);
    }

	public int getCod_paciente() {
		return cod_paciente;
	}

	public void setCod_paciente(int cod_paciente) {
		this.cod_paciente = cod_paciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(String datacadastro) {
		this.datacadastro = datacadastro;
	}

	public String getHoracadastro() {
		return horacadastro;
	}

	public void setHoracadastro(String horacadastro) {
		this.horacadastro = horacadastro;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	
	
}
