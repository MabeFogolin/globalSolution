package br.com.fiap.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Ong;
import br.com.fiap.conexoes.ConnectionFactory;


public class GerenciadorOng {
	public Connection conn;

	public GerenciadorOng() throws ClassNotFoundException, SQLException {
		super();
		this.conn = new ConnectionFactory().connection();
	}
	

	public boolean adicionarOng(Ong ong) {
		String sql = "INSERT INTO Ong (ong_nome, ong_cnpj, ong_qtd_func, ong_uf, ong_cidade, fk_oceanis)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ong.getOngNome());
			ps.setString(2, ong.getOngCnpj());
			ps.setInt(3, ong.getOngQtdFunc());
			ps.setString(4, ong.getUf());
			ps.setString(5, ong.getCidade());
			ps.setInt(6, ong.getFkOceanis());
			ps.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException erroIntegridade) {
			System.err.println("Chave primária (CNPJ) já cadastrada, por favor, utilize outra");
			return false;
		} catch (SQLNonTransientConnectionException e) {
			System.err.println("CONEXÃO NULA");
			e.printStackTrace();
			return false;
		} catch (SQLException se) {
			do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
			return false;
		}
		return true;
	}

	public boolean deletarOng(String cnpj) {
		String sqlFilho = "DELETE FROM Voluntario WHERE fk_ong = ?";
		String sql = "DELETE FROM Ong WHERE ong_cnpj = ?";
		try {
			PreparedStatement psFilho = conn.prepareStatement(sqlFilho);
			psFilho.setString(1, cnpj);
			psFilho.execute();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cnpj);
			ps.execute();
		} catch (SQLException se) {
			do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
			return false;
		}
		return true;
	}

	public boolean atualizarOng(Ong ong) {
		String sql = "UPDATE Ong SET ong_nome = ?, ong_qtd_func = ?, ong_uf = ?, ong_cidade = ? WHERE ong_cnpj = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ong.getOngNome());
			ps.setInt(2, ong.getOngQtdFunc());
			ps.setString(3, ong.getUf());
			ps.setString(4, ong.getCidade());
			ps.setString(5, ong.getOngCnpj());
			ps.executeUpdate();
		} catch (SQLException se) {
			do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
			return false;
		}
		return true;
	}

	public List<Ong> listarOngs() {

		List<Ong> ongs = new ArrayList<>();

		String sql = "SELECT ong_nome, ong_qtd_func, ong_uf, ong_cidade, ong_cnpj FROM Ong";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String ongNome = rs.getString("ong_nome");
				int ongQtdFunc = rs.getInt("ong_qtd_func");
				String uf = rs.getString("ong_uf");
				String cidade = rs.getString("ong_cidade");
				String ongCnpj = rs.getString("ong_cnpj");

				Ong ong = new Ong(ongNome, ongCnpj, ongQtdFunc, uf, cidade, ongQtdFunc);
				ongs.add(ong);
			}

		} catch (SQLException se) {
			do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
		}
		return ongs;
	}

	public void fecharConexao() {
		try {
			System.out.println("Fechando a conexão");
			conn.close();
		} catch (SQLException e) {
			System.err.println("Erro ao fechar a conexão");
			e.printStackTrace();
		}
	}
}
