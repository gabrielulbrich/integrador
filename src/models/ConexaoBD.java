package models;

import java.sql.*;
import java.util.List;

import javax.swing.JOptionPane;

public class ConexaoBD {
	
	public Connection con;
	public Statement stm;
	public ResultSet rs;
	private String caminho = "jdbc:hsqldb:file:C:\\workspace\\Integrador\\banco\\hospital";
	private String usuario = "SA";
	private String senha = "";

	public void conexao() { //METODO RESPOSAVEL POR REALIZAR CONEXAO COM O BD
		int result = 0; 
		try {
			//System.setProperty("jdbc.Drivers", driver);
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection(caminho, usuario, senha);
		}catch(ClassNotFoundException e){
		    System.out.println("Erro ao carregar o driver JDBC. ");
		}catch (SQLException e) {
		    System.out.println("Erro de SQL conexao: "+e);
		    e.printStackTrace();
		}
	}
	
	public void desconecta() { //METODO RESPONSAVEL PARA DESCONECTAR DO BANCO
		try {
			//stm.execute("SHUTDOWN");
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERRO ao fechar conexao com BD:\n"+e.getMessage());
		}
	}
	
	public void executaSql(String sql) {
		try {
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERRO ao executar SQL:\n"+e.getMessage());
		}
	}
}
