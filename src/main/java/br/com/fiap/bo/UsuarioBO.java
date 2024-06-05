package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.beans.Login;
import br.com.fiap.beans.Usuario;
import br.com.fiap.controller.GerenciadorUsuario;
import br.com.fiap.exceptions.DadoRepetido;


public class UsuarioBO {
	
	GerenciadorUsuario gereciadorUser = null;
	
	public void inserirUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, DadoRepetido {
		
		GerenciadorUsuario gu = new GerenciadorUsuario();
		
		if(gu.buscarUsuario(usuario.getUserEmail()) == true) {
			gu.adicionarUsuario(usuario);
			gu.fecharConexao();
		}
		else {
			throw new DadoRepetido("E-mail já cadastrado, utilize um novo e-mail para se cadastrar.\n");
		}
		
	}
	
	public void deletarUsuario(String email) throws ClassNotFoundException, SQLException {
		GerenciadorUsuario gu = new GerenciadorUsuario();
		gu.deletarUsuario(email);
		gu.fecharConexao();
	}
	
	public ArrayList<Usuario> listarUsuarios() throws ClassNotFoundException, SQLException{
		GerenciadorUsuario gu = new GerenciadorUsuario();
		return (ArrayList<Usuario>) gu.listarUsuarios();
	}
	
	public void atualizarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
		GerenciadorUsuario gu = new GerenciadorUsuario();
		gu.atualizarUsuario(usuario);
		gu.fecharConexao();
	}
	
	public Login verificarLogin(String email, String senha) throws ClassNotFoundException, SQLException {
	    GerenciadorUsuario gu = new GerenciadorUsuario();
	    Login login = gu.validarLogin(email, senha);
	    gu.fecharConexao();
	    return login;
	}

}
