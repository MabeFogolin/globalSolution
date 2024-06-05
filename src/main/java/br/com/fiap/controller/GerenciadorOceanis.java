package br.com.fiap.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Oceanis;
import br.com.fiap.conexoes.ConnectionFactory;


public class GerenciadorOceanis {
	public Connection conn;

	public GerenciadorOceanis() throws ClassNotFoundException, SQLException {
		super();
		this.conn = new ConnectionFactory().connection();
	}


	public boolean adicionarOceanis(Oceanis oceanis) {
		String sql = "INSERT INTO Oceanis (id_menu, uf_acesso) VALUES (?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, oceanis.getIdMenu());
			ps.setString(2, oceanis.getUfAcesso());
			ps.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException erroIntegridade) {
			System.err.println("Chave primária (id_menu) já cadastrada, por favor, utilize outra");
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

	public boolean deletarOceanis(int id) {
		String sqlUser = "DELETE FROM Usuario WHERE fk_oceanis = ?";
		String sqlFunc = "DELETE FROM Funcionario WHERE fk_oceanis = ?";
		String sqlOng = "DELETE FROM Ong WHERE fk_oceanis = ?";
		String sql = "DELETE FROM Oceanis WHERE id_menu = ?";
		try {
			PreparedStatement psUser = conn.prepareStatement(sqlUser);
			psUser.setInt(1, id);
			psUser.execute();
			
			PreparedStatement psFunc = conn.prepareStatement(sqlFunc);
			psFunc.setInt(1, id);
			psFunc.execute();
			
			PreparedStatement psOng = conn.prepareStatement(sqlOng);
			psOng.setInt(1, id);
			psOng.execute();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
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

	public boolean atualizarOceanis(Oceanis oceanis) {
		String sql = "UPDATE Oceanis SET uf_acesso = ? WHERE id_menu = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, oceanis.getUfAcesso());
			ps.setInt(2, oceanis.getIdMenu());
		} catch (SQLIntegrityConstraintViolationException erroIntegridade) {
			System.err.println("Chave (func_id) já cadastrada, por favor, utilize outra");
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

	public List<Oceanis> listarOceanis() {
		List<Oceanis> menusOceanis = new ArrayList<Oceanis>();

		String sql = "SELECT * FROM Oceanis";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int idMenu = rs.getInt("id_menu");
				String ufAcesso = rs.getString("uf_acesso");

				Oceanis oceanis = new Oceanis(idMenu, ufAcesso);
				menusOceanis.add(oceanis);
			}
		} catch (SQLException se) {
			do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
		}
		return menusOceanis;
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
