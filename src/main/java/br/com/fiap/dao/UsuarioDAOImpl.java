package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Usuario;
import br.com.fiap.dao.interfaces.UsuarioDAO;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class UsuarioDAOImpl implements UsuarioDAO{
	
	private List<Usuario> usuarios = new ArrayList<>();

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarios;
	}

	@Override
	public void inserirUsuario(Usuario usuario) throws DadoRepetido {
		if(usuarios.stream().anyMatch(u -> u.getUserEmail() == usuario.getUserEmail())) {
			throw new DadoRepetido("E-mail já cadastrado, utilize um novo e-mail para se cadastrar.\n");
		}
		usuarios.add(usuario);
	}

	@Override
	public Usuario buscarUsuario(String email) {
		for(Usuario usuarioBuscar : usuarios) {
			if(usuarioBuscar.getUserEmail() == email) {
				return usuarioBuscar;
			}
		}
		return null;
	}

	@Override
	public Usuario atualizarUsuario(Usuario usuario) throws DadoRepetido {
		if(usuarios.stream().anyMatch(u -> u.getUserEmail() == usuario.getUserEmail())) {
			throw new DadoRepetido("E-mail já cadastrado, utilize um novo e-mail para se cadastrar.\n");
	}
		usuario.setUserEmail(usuario.getUserEmail());
		usuario.setUserIdade(usuario.getUserIdade());
		usuario.setUserNome(usuario.getUserNome());
		usuario.setUserCep(usuario.getUserCep());
		return usuario;
}

	@Override
	public void deletarUsuario(String email) throws ObjetoNaoEncontrado {
		Usuario usuarioRemover = buscarUsuario(email);
		if(usuarioRemover != null) {
			usuarios.remove(usuarioRemover);
		} else throw new ObjetoNaoEncontrado();
		
	}

}
