package dao;

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
import models.Paciente_model;

public class PacienteDAO {

	int categoria;
	int prioridade = 0;
	ConexaoBD conexao = new ConexaoBD();

	public boolean inserir(Paciente_model model){		

		String tempo = model.getDatacadastro() +" "+ model.getHoracadastro();		
//		long timestamp = 0;
//		
//		try {			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");    			
//			timestamp = dateFormat.parse(tempo).getTime()/1000;			
//		} catch(ParseException e) { //this generic but you can control another types of exception
//		    System.out.println("Erro na data");
//		}
		prioridade = Integer.parseInt(model.getPrioridade());
		
		categoria = this.getCategoria(prioridade);
		
		prioridade = selectMinPrioridade(categoria);
		if (prioridade == 0) {
			prioridade = Integer.parseInt(model.getPrioridade());
		}
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement(
					"INSERT INTO paciente (nome, cpf, idade, sexo, endereco, telefone, datacadastro, prioridade, cod_categoria) VALUES (?,?,?,?,?,?,TIMESTAMP(?, ? ),?, ?);");
			pst.setString(1, model.getNome());
			pst.setString(2, model.getCpf());
			pst.setString(3, model.getIdade());
			pst.setString(4, model.getSexo());
			pst.setString(5, model.getEndereco());
			pst.setString(6, model.getTelefone());
			pst.setString(7, model.getDatacadastro());
			pst.setString(8, model.getHoracadastro());
			pst.setInt(9, prioridade);
			pst.setInt(10, categoria);
			pst.execute();
			conexao.desconecta();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
			conexao.desconecta();			
			e.printStackTrace();
			return false;
		}
	}

	public int selectMinPrioridade(int categoria) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement("SELECT MIN(prioridade) as prioridade FROM paciente where cod_categoria="+categoria+";");
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getObject("prioridade") != null) {
					prioridade = (Integer) rs.getObject("prioridade");
				}else {
					conexao.desconecta();
					return 0;
				}
			}
			conexao.desconecta();
			return --prioridade;

		} catch (Exception e) {
			conexao.desconecta();
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
			return 0;
		}
	}
	
	public int selectMaxPrioridade(int categoria) {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement("SELECT MAX(prioridade) as prioridade FROM paciente where cod_categoria="+categoria+";");
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getObject("prioridade") != null) {
					prioridade = (Integer) rs.getObject("prioridade");
				}else {
					conexao.desconecta();
					return 0;
				}
			}
			conexao.desconecta();
			return ++prioridade;

		} catch (Exception e) {
			conexao.desconecta();
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
			return 0;
		}
	}

	public List<Paciente_model> selectPaciente() {
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement("SELECT cod_paciente, nome, cpf, idade, sexo, endereco, telefone, datacadastro, categoria, prioridade, cod_categoria FROM paciente as p, categoria as c WHERE p.cod_categoria = c.cod_categoria;");
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
				model.setCod_categoria(rs.getInt("cod_categoria"));
				model.setPrioridade(rs.getString("prioridade"));
				model.setCategoria(rs.getString("categoria"));
				models.add(model);

			}
			conexao.desconecta();
			return models;

		} catch (Exception e) {
			conexao.desconecta();
			System.out.println("Erro de SQL: " + e);
			e.printStackTrace();
			return null;
		}
	}

	public boolean editarPaciente(Paciente_model mode) {
		int old_prioridade = Integer.parseInt(mode.getPrioridade());
		int new_prioridade = Integer.parseInt(mode.getNew_prioridade());
		int old_categoria = mode.getCod_categoria();
		int new_categoria = getCategoria(Integer.parseInt(mode.getNew_prioridade()));
		new_prioridade = this.getNewprioridade(new_prioridade, old_prioridade, new_categoria, old_categoria);
		
		if (new_prioridade == 0)
			return false;		
		
		conexao.conexao();
		try {
			PreparedStatement pst = conexao.con.prepareStatement(
					"UPDATE paciente SET prioridade = ?, cod_categoria = ? where cod_paciente = ?;");
			pst.setInt(1, new_prioridade);
			pst.setInt(2, new_categoria);
			pst.setInt(3, mode.getCod_paciente());
			
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

	public boolean atenderPaciente(Paciente_model model) {
		Statement stm;
		ConexaoBD conexao = new ConexaoBD();
		conexao.conexao();
		try {
			stm = conexao.con.createStatement();
			stm.executeQuery("DELETE FROM PACIENTE WHERE cod_paciente='" + model.getCod_paciente() + "';");
			conexao.desconecta();
			return true;
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
		conexao.desconecta();
		return false;
	}
	
	private int getCategoria(int prioridade) {
		if (prioridade == 100) {
			categoria = 1;//baixa
		}else if(prioridade == 200) {
			categoria = 2;//media
		}else if(prioridade == 300) {
			categoria = 3;//alta
		}else if(prioridade == 400) {
			categoria = 4;//emergencia
		}else {
			categoria = 0;//nenhuma.
		}
		return categoria;
	}
	
	private int getNewprioridade(int new_prioridade, int old_prioridade, int new_categoria, int old_categoria) {
		if(new_categoria == old_categoria){
			return prioridade = 0;			
		}else if(new_prioridade > old_prioridade) {
			prioridade = this.selectMinPrioridade(new_categoria);
			if (prioridade == 0)
				prioridade = new_prioridade;
			return prioridade;
		}else if (new_prioridade < old_prioridade) {
			prioridade = this.selectMaxPrioridade(new_categoria);
			if (prioridade == 0)
				prioridade = new_prioridade;
			return prioridade;
		}
		return prioridade;
	}

}
