package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import models.ConexaoBD;
import controllers.Register;

public class RegisterModel {
	
	private String nome;
	private String login;
	private String email;
	private String senha;
	private String senha_confirmacao;

	ConexaoBD conexao = new ConexaoBD();

	public boolean Salvar() {
		conexao.conexao();
		try {	
			PreparedStatement pst = conexao.con.prepareStatement("INSERT INTO usuario (nome, login, email, senha) VALUES (?,?,?,?);");
			pst.setString(1, this.getNome());
			pst.setString(2, this.getLogin());
			pst.setString(3, this.getEmail());
			pst.setString(4, this.getSenha());
			pst.execute();
			conexao.desconecta();
			return true;
		} catch (SQLException e) {
		    System.out.println("Erro de SQL: "+e);
		    e.printStackTrace();
		}
		conexao.desconecta();
		return false;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha_confirmacao() {
		return senha_confirmacao;
	}

	public void setSenha_confirmacao(String senha_confirmacao) {
		this.senha_confirmacao = senha_confirmacao;
	}
}
