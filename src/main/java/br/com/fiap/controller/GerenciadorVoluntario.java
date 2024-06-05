package br.com.fiap.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.beans.Voluntario;
import br.com.fiap.conexoes.ConnectionFactory;

public class GerenciadorVoluntario {
	public Connection conn;

	public GerenciadorVoluntario() throws ClassNotFoundException, SQLException {
		super();
		this.conn = new ConnectionFactory().connection();
	}
	public boolean adicionarVoluntario(Voluntario voluntario) {
		String sql = "INSERT INTO Voluntario (vol_id, vol_cpf, vol_nome, vol_sobrenome, vol_tel, fk_ong)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, voluntario.getVolId());
			ps.setString(2, voluntario.getVolCpf());
			ps.setString(3, voluntario.getVolNome());
			ps.setString(4, voluntario.getVolSobrenome());
			ps.setLong(5, voluntario.getVolTel());
			ps.setString(6, voluntario.getFkOng());
		} catch (SQLIntegrityConstraintViolationException erroIntegridade) {
			System.err.println("Chave primária (CPF) já cadastrada, por favor, utilize outra");
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

	public boolean deletarVoluntario(String cpf) {
		String sql = "DELETE FROM Voluntario WHERE vol_cpf = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
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

	public boolean atualizarVoluntario(Voluntario voluntario) {
		String sql = "UPDATE Voluntario SET vol_nome = ?, vol_sobrenome = ?, vol_tel = ?, fk_ong = ? WHERE vol_cpf = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, voluntario.getVolNome());
			ps.setString(2, voluntario.getVolSobrenome());
			ps.setLong(3, voluntario.getVolTel());
			ps.setString(4, voluntario.getFkOng());
			ps.setString(5, voluntario.getVolCpf());
			ps.executeUpdate();
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

	public List<Voluntario> listarVoluntarios() {
		List<Voluntario> voluntarios = new ArrayList<>();

		String sql = "SELECT vol_id, vol_nome, vol_sobrenome, vol_tel,  fk_ong FROM Voluntario";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String volId = rs.getString("vol_id");
				String volNome = rs.getString("vol_nome");
				String volSobrenome = rs.getString("vol_sobrenome");
				long volTel = rs.getLong("vol_tel");
				String fkOng = rs.getString("fk_ong");

				Voluntario voluntario = new Voluntario(volId, volNome, volSobrenome, volTel, fkOng);
				voluntarios.add(voluntario);
			}
		} catch (SQLException se) {
			do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
		}
		return voluntarios;
	}
	
	public String cadastrarCpf(String cpf) {
        Scanner input = new Scanner(System.in);
        do {
            if (cpf.length() != 11 ||
                    cpf.equals("00000000000") || cpf.equals("11111111111") ||
                    cpf.equals("22222222222") || cpf.equals("33333333333") ||
                    cpf.equals("44444444444") || cpf.equals("55555555555") ||
                    cpf.equals("66666666666") || cpf.equals("77777777777") ||
                    cpf.equals("88888888888") || cpf.equals("99999999999")) {

                System.out.println("CPF inválido");
                System.out.println("Informe um novo CPF");
                cpf = input.nextLine();

            } else {
            	input.close();
                return cpf;
            }
        } while (cpf.length() != 11);
        input.close();
        
        return null;
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
