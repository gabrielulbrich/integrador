package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoriaModel {
	private int idcategoria;
	private String categ;
	
	ConexaoBD conexao = new ConexaoBD();
	
	public boolean Gravar() {
		conexao.conexao();
		try {	
			PreparedStatement pst = conexao.con.prepareStatement("INSERT INTO categoria (categ) VALUES (?);");
			pst.setString(1, this.getCateg());
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
	
	public boolean Excluir() {
		conexao.conexao();
		try {	
			PreparedStatement pst = conexao.con.prepareStatement("DELETE FROM categoria WHERE categ = ?;");
			pst.setString(1, this.getCateg());
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
	
	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getCateg() {
		return categ;
	}

	public void setCateg(String categ) {
		this.categ = categ;
	}

	
}
