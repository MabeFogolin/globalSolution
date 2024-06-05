package br.com.fiap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Endereco;
import br.com.fiap.beans.EnderecoViaCep;
import br.com.fiap.beans.Login;
import br.com.fiap.beans.Usuario;
import br.com.fiap.conexoes.ConnectionFactory;
import br.com.fiap.services.ViacepService;

public class GerenciadorUsuario {

	public Connection conn;

	public GerenciadorUsuario() throws ClassNotFoundException, SQLException {
		super();
		this.conn = new ConnectionFactory().connection();
	}

	public boolean adicionarUsuario(Usuario usuario) {
		String sql = "INSERT INTO Usuario (user_nome, user_idade, user_email, user_senha, user_cep, fk_oceanis) VALUES ( ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getUserNome());
			ps.setInt(2, usuario.getUserIdade());
			ps.setString(3, usuario.getUserEmail());
			ps.setString(4, usuario.getUserSenha());
			ps.setString(5, usuario.getUserCep());
			ps.setInt(6, usuario.getFkOceanis());
			ps.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException erroIntegridade) {
			System.err.println("Chave primária (E-mail) já cadastrada, por favor, utilize outra");
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

	public boolean deletarUsuario(String email) {
		String sqlFilho = "DELETE FROM Endereco WHERE fk_usuario = ?";
		String sql = "DELETE FROM Usuario WHERE user_email = ?";
		try {
			PreparedStatement psFilho = conn.prepareStatement(sqlFilho);
			psFilho.setString(1, email);
			psFilho.execute();

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
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

	public boolean atualizarUsuario(Usuario usuario) {
		String sql = "UPDATE Usuario SET user_nome = ?, user_idade = ?, user_cep = ? WHERE user_email = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, usuario.getUserNome());
			ps.setInt(2, usuario.getUserIdade());
			ps.setString(3, usuario.getUserCep());
			ps.setString(4, usuario.getUserEmail());
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

	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();

		String sql = "SELECT user_nome, user_idade, user_email, user_cep FROM Usuario";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String userNome = rs.getString("user_nome");
				int userIdade = rs.getInt("user_idade");
				String userEmail = rs.getString("user_email");
				String userCep = rs.getString("user_cep");

				Usuario usuario = new Usuario(userNome, userIdade, userEmail, userCep);
				usuarios.add(usuario);
			}
		} catch (SQLException se) {
			do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
		}
		return usuarios;
	}
	
	public boolean buscarUsuario(String email) {
		String sql = "SELECT * FROM Usuario WHERE user_email = ?";
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
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
	
	public Login validarLogin(String email, String senha) throws SQLException {
		String sql = "SELECT user_email, user_senha FROM Usuario WHERE user_email = ? and user_senha = ?";
	    Login login = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
	            login = new Login();
	            login.setEmail(rs.getString("user_email"));
	            login.setSenha(rs.getString("user_senha"));
	            // Preencha outros campos de acordo com a sua necessidade
	        }
	    } catch (SQLException se) {
	    	do {
				System.out.println("SQL STATE: " + se.getSQLState());
				System.out.println("ERROR CODE: " + se.getErrorCode());
				System.out.println("MESSAGE: " + se.getMessage());
				System.out.println();
			} while (se != null);
	    }
	    return login;
	}
	
	public Endereco cadastrarEnderecoViaCep(Usuario usuario) {
	    Endereco endereco = null;
	    ViacepService viacepService = new ViacepService();
	    try {
	      EnderecoViaCep enderecoViaCep = viacepService.getEnderecoViaCep(usuario.getUserCep());
	        
	        // Preenchendo os dados do EnderecoViaCep conforme a classe EnderecoViaCep fornecida
	        endereco = new Endereco();
	        endereco.setFkUsuario(usuario.getUserEmail()); // Usando o e-mail do usuário como chave estrangeira
	        endereco.setCidade(enderecoViaCep.getLocalidade());
	        endereco.setRua(enderecoViaCep.getLogradouro());
	        endereco.setUf(enderecoViaCep.getUf());
	        
	        // Retornando o EnderecoViaCep modificado
	        return endereco;
	    } catch (IOException e) {
	        System.err.println("Erro ao buscar o endereço via CEP: " + e.getMessage());
	        return null;
	    }
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
