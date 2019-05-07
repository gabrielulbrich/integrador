package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.ConexaoBD;

public class PacienteDAO {
	
	ConexaoBD conexao = new ConexaoBD();
	

	public boolean Inserir(NewRegisterModel mode) {
		conexao.conexao();
		try {	
			PreparedStatement pst = conexao.con.prepareStatement("INSERT INTO paciente (nome, cpf, idade, sexo, endereco, telefone, datacadastro, horacadastro, prioridade) VALUES (?,?,?,?,?,?,?,?,?);");
			pst.setString(1, mode.getNome());
			pst.setString(2, mode.getCpf());
			pst.setString(3, mode.getIdade());
			pst.setString(4, mode.getSexo());
			pst.setString(5, mode.getEndereco());
			pst.setString(6, mode.getTelefone());
			pst.setString(7, mode.getDatacadastro());
			pst.setString(8, mode.getHoracadastro());
			pst.setString(9, mode.getPrioridade());
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
	
	public List<NewRegisterModel> Buscar(){
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement("SELECT * FROM paciente ORDER BY prioridade ASC;");
			ResultSet rs = pst.executeQuery();
			List<NewRegisterModel> models = new ArrayList<>();
			while(rs.next()) {
				NewRegisterModel model = new NewRegisterModel();
				model.setCod_paciente(rs.getInt("cod_paciente"));
				model.setNome(rs.getString("nome"));
				model.setCpf(rs.getString("cpf"));
				model.setIdade(rs.getString("idade"));
				model.setSexo(rs.getString("sexo"));
				model.setEndereco(rs.getString("endereco"));
				model.setTelefone(rs.getString("telefone"));
				model.setDatacadastro(rs.getString("datacadastro"));
				model.setHoracadastro(rs.getString("horacadastro"));
				model.setPrioridade(rs.getString("prioridade"));
				models.add(model);
				
			}
			conexao.desconecta();
			return models;
							
		} catch (Exception e) {
			System.out.println("Erro de SQL: "+e);
		    e.printStackTrace();
		    return null;
		}
	}
	public boolean Mudar(NewRegisterModel mode) {
		conexao.conexao();
		try {	
			PreparedStatement pst = conexao.con.prepareStatement("UPDATE paciente SET nome = ?, cpf = ?, idade = ?, sexo = ?, endereco = ?, telefone = ?, datacadastro = ?, horacadastro = ?, prioridade = ? where cod_paciente = ?;");
			pst.setInt(1, mode.getCod_paciente());
			pst.setString(2, mode.getNome());
			pst.setString(3, mode.getCpf());
			pst.setString(4, mode.getIdade());
			pst.setString(5, mode.getSexo());
			pst.setString(6, mode.getEndereco());
			pst.setString(7, mode.getTelefone());
			pst.setString(8, mode.getDatacadastro());
			pst.setString(9, mode.getHoracadastro());
			pst.setString(10, mode.getPrioridade());
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
	
	public static void deletarPaciente(Integer cod_paciente) {
		Statement stm;
		List<NewRegisterModel> models = new ArrayList<>();  
	    ConexaoBD conexao = new ConexaoBD();
	    conexao.conexao();
	    try {	
			stm = conexao.con.createStatement();
			stm.executeQuery("DELETE FROM PACIENTE WHERE cod_paciente='"+cod_paciente+"';");
	    conexao.desconecta();
	    }catch(Exception sqlException) {
	        sqlException.printStackTrace();
	    } 
	}

}
