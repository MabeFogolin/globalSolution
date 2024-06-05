package br.com.fiap.dao.interfaces;

import java.util.List;

import br.com.fiap.beans.Usuario;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


public interface UsuarioDAO {
	
	public List<Usuario> listarUsuarios();
	public void inserirUsuario(Usuario usuario) throws DadoRepetido;
	public Usuario buscarUsuario(String email);
	public Usuario atualizarUsuario(Usuario usuario) throws DadoRepetido;
	public void deletarUsuario(String email) throws ObjetoNaoEncontrado;
}
