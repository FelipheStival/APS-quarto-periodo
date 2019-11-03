package Banco;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static String serverName = "localhost:3306";
	private static String mydatabase = "APS";
	private static String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
	private static String username = "root";
	private static String password = "root";

	public static java.sql.Connection CriarConexao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection Conexao = DriverManager.getConnection(url, username, password);
			System.out.println("Conectado com sucesso");
			return Conexao;
		} catch (SQLException e) {
			return null;
		} catch (ClassNotFoundException e) {
		}
		return null;
	}

	public static void FecharConexao(java.sql.Connection Conexao, java.sql.PreparedStatement stmt) {
		try {
			if (Conexao != null && stmt != null) {
				stmt.close();
				Conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
