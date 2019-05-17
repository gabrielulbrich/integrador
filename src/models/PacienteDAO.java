package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.ConexaoBD;

public class PacienteDAO {

	ConexaoBD conexao = new ConexaoBD();

	public boolean Inserir(Paciente_model model){
		conexao.conexao();

		String tempo = model.getDatacadastro() +" "+ model.getHoracadastro();		
		System.out.println(tempo);

		long ts = 0;
		
		try {			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    			
			ts = dateFormat.parse(tempo).getTime()/1000;			
		} catch(ParseException e) { //this generic but you can control another types of exception
		    System.out.println("Erro na data");
		}		
		
		return true;
		
		try {
			PreparedStatement pst = conexao.con.prepareStatement(
					"INSERT INTO paciente (nome, cpf, idade, sexo, endereco, telefone, datacadastro, horacadastro, prioridade) VALUES (?,?,?,?,?,?,to_date(?, 'DD-MM-YYYY'),?,?);");
			pst.setString(1, model.getNome());
			pst.setString(2, model.getCpf());
			pst.setString(3, model.getIdade());
			pst.setString(4, model.getSexo());
			pst.setString(5, model.getEndereco());
			pst.setString(6, model.getTelefone());
			pst.setString(7, model.getDatacadastro());
			pst.setString(8, model.getHoracadastro());
			pst.setString(9, model.getPrioridade());
			pst.execute();
			conexao.desconecta();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
		}
		conexao.desconecta();
		return false;
	}

//	public static String time(String data, String hora) throws ParseException {
//		Paciente_model model = new Paciente_model();
//		String date = model.getDatacadastro() + " " + model.getHoracadastro();
//		Timestamp timestamp = null;
//		try {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//			Date parsedDate = dateFormat.parse(date);
//			timestamp = new java.sql.Timestamp(parsedDate.getTime());
//		} catch (Exception e) { // this generic but you can control another types of exception
//			// look the origin of excption
//		}
//		System.out.println(timestamp.toInstant().toString());
//		return timestamp.toInstant().toString();
//	}

	public List<Paciente_model> Buscar() {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement("SELECT * FROM paciente ORDER BY prioridade ASC;");
			ResultSet rs = pst.executeQuery();
			List<Paciente_model> models = new ArrayList<>();
			while (rs.next()) {
				Paciente_model model = new Paciente_model();
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
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
			return null;
		}
	}

	public boolean Mudar(Paciente_model mode) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement(
					"UPDATE paciente SET nome = ?, cpf = ?, idade = ?, sexo = ?, endereco = ?, telefone = ?, datacadastro = ?, horacadastro = ?, prioridade = ? where cod_paciente = ?;");
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
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
		}
		conexao.desconecta();
		return false;
	}

	public static void deletarPaciente(Integer cod_paciente) {
		Statement stm;
		List<Paciente_model> models = new ArrayList<>();
		ConexaoBD conexao = new ConexaoBD();
		conexao.conexao();
		try {
			stm = conexao.con.createStatement();
			stm.executeQuery("DELETE FROM PACIENTE WHERE cod_paciente='" + cod_paciente + "';");
			conexao.desconecta();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
	}

}
