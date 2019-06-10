package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Paciente_model {
	
	private int cod_paciente;
	private String nome;
	private String cpf;
	private String idade;
	private String sexo;
	private String endereco;
	private String telefone;
	private String horacadastro;
	private String datacadastro;	
	private String categoria;
	private int cod_categoria;
	private String prioridade;
	private String new_prioridade;
		
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
	
	public String getHoracadastro() {
		return horacadastro;
	}

	public void setHoracadastro(String horacadastro) {
//		horacadastro = horacadastro+" "+getHoracadastro();
//		java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(horacadastro) ;
//		this.horacadastro = timestamp.toInstant().toString();
//		System.out.println(this.horacadastro);
		this.horacadastro = horacadastro;
	}

	public String getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(String datacadastro) {
		this.datacadastro = datacadastro;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNew_prioridade() {
		return new_prioridade;
	}

	public void setNew_prioridade(String new_prioridade) {
		this.new_prioridade = new_prioridade;
	}

	public int getCod_categoria() {
		return cod_categoria;
	}

	public void setCod_categoria(int cod_categoria) {
		this.cod_categoria = cod_categoria;
	}
	

	
}
