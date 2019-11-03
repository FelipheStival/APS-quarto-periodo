package Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class FuncoesBanco {

	public static ArrayList<InfoImagem> ObterImagens() {
		String Query = "SELECT * FROM imagens;";
		PreparedStatement stmt = null;
		Connection Ligacao = null;
		ArrayList<InfoImagem> Imagens = new ArrayList<InfoImagem>();
		try {
			Ligacao = Conexao.CriarConexao();
			stmt = Ligacao.prepareStatement(Query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				InfoImagem dados = new InfoImagem(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBytes(4));
				Imagens.add(dados);
			}
			if (Imagens.size() > 0) {
				return Imagens;
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}

		return null;
	}

	public InfoImagem ObterImagem(int id) {
		String Query = "SELECT * FROM imagens WHERE idImagens = (?);";
		PreparedStatement stmt = null;
		Connection Ligacao = null;
		InfoImagem Imagem = null;
		try {
			Ligacao = Conexao.CriarConexao();
			stmt = Ligacao.prepareStatement(Query);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getRow() > 0) {
					Imagem = new InfoImagem(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBytes(4));
					if (Imagem != null) {
						return Imagem;
					}
				} else {
					return null;
				}
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}
		return null;
	}
}
