package br.com.fiap.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Funcionario;
import br.com.fiap.conexoes.ConnectionFactory;


public class GerenciadorFuncionario {
	public Connection conn;

	public GerenciadorFuncionario() throws ClassNotFoundException, SQLException {
		super();
		this.conn = new ConnectionFactory().connection();
	}

	public boolean adicionarFuncionario(Funcionario funcionario) {
		String sql = "INSERT INTO Funcionario (func_id, func_cpf, func_nome, func_sobrenome, func_tel, func_salario, func_email) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, funcionario.getFuncId());
			ps.setString(2, funcionario.getFuncCpf());
			ps.setString(3, funcionario.getFuncNome());
			ps.setString(4, funcionario.getFuncSobrenome());
			ps.setLong(5, funcionario.getFuncTel());
			ps.setBigDecimal(6, BigDecimal.valueOf(funcionario.getFuncSalario()));
			ps.setString(7, funcionario.getFuncEmail());
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			System.err.println("Erro ao adicionar funcionário:");
			se.printStackTrace();
			return false;
		}
	}

	public boolean deletarFuncionario(String cpf) {
		String sql = "DELETE FROM Funcionario WHERE func_cpf = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.execute();
			return true;
		} catch (SQLException se) {
			System.err.println("Erro ao deletar funcionário:");
			se.printStackTrace();
			return false;
		}
	}

	public boolean atualizarFuncionario(Funcionario funcionario) {
	    String sql = "UPDATE Funcionario SET func_nome = ?, func_sobrenome = ?, func_tel = ?, func_salario = ?, func_email = ?, func_senha = ? WHERE func_cpf = ?";
	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, funcionario.getFuncNome());
	        ps.setString(2, funcionario.getFuncSobrenome());
	        ps.setLong(3, funcionario.getFuncTel());
	        ps.setBigDecimal(4, BigDecimal.valueOf(funcionario.getFuncSalario()));
	        ps.setString(5, funcionario.getFuncEmail());
	        ps.setString(6, funcionario.getFuncSenha());
	        ps.setString(7, funcionario.getFuncCpf());
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException se) {
	        System.err.println("Erro ao atualizar funcionário:");
	        se.printStackTrace();
	        return false;
	    }
	}


	public List<Funcionario> listarFuncionarios() {
		List<Funcionario> funcionarios = new ArrayList<>();
		String sql = "SELECT func_id, func_cpf, func_nome, func_sobrenome, func_tel, func_salario FROM Funcionario";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String funcId = rs.getString("func_id");
				String funcCpf = rs.getString("func_cpf");
				String funcNome = rs.getString("func_nome");
				String funcSobrenome = rs.getString("func_sobrenome");
				long funcTel = rs.getLong("func_tel");
				float funcSalario = rs.getFloat("func_salario");

				Funcionario funcionario = new Funcionario(funcId, funcCpf, funcNome, funcSobrenome, funcTel,
						funcSalario);
				funcionarios.add(funcionario);
			}
		} catch (SQLException se) {
			System.err.println("Erro ao listar funcionários:");
			se.printStackTrace();
		}
		return funcionarios;
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
