package br.com.fiap.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Endereco;
import br.com.fiap.conexoes.ConnectionFactory;


public class GerenciadorEndereco {
	
	public Connection conn;

	public GerenciadorEndereco() throws ClassNotFoundException, SQLException {
		super();
		this.conn = new ConnectionFactory().connection();
	}

	public boolean adicionarEndereco(Endereco endereco) {
		String sql = "INSERT INTO EnderecoViaCep (fk_usuario, cidade, rua, uf) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, endereco.getFkUsuario());
			ps.setString(2, endereco.getCidade());
			ps.setString(3, endereco.getRua());
			ps.setString(4, endereco.getUf());
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			System.err.println("Erro ao adicionar endereço:");
			se.printStackTrace();
			return false;
		}
	}

	public boolean deletarEndereco(String email) {
		String sql = "DELETE FROM EnderecoViaCep WHERE fk_usuario = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.execute();
			return true;
		} catch (SQLException se) {
			System.err.println("Erro ao deletar endereço:");
			se.printStackTrace();
			return false;
		}
	}

	public boolean atualizarEndereco(Endereco endereco) {
		String sql = "UPDATE EnderecoViaCep SET cidade = ?, rua = ?, uf = ? WHERE fk_usuario = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, endereco.getCidade());
			ps.setString(2, endereco.getRua());
			ps.setString(3, endereco.getUf());
			ps.setString(4, endereco.getFkUsuario());
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			System.err.println("Erro ao atualizar endereço:");
			se.printStackTrace();
			return false;
		}
	}

	public List<Endereco> listaEndereco() {
		List<Endereco> enderecos = new ArrayList<>();
		String sql = "SELECT * FROM EnderecoViaCep";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				String fkUsuario = rs.getString("fk_usuario");
				String cidade = rs.getString("cidade");
				String rua = rs.getString("rua");
				String uf = rs.getString("uf");
				Endereco endereco = new Endereco(fkUsuario, cidade, rua, uf);
				enderecos.add(endereco);
			}
		} catch (SQLException se) {
			System.err.println("Erro ao listar endereços:");
			se.printStackTrace();
		}
		return enderecos;
	}

	public void fecharConexao() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Conexão fechada.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao fechar a conexão:");
			e.printStackTrace();
		}
	}
}
