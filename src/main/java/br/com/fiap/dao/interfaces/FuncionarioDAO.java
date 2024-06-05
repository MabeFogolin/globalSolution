package br.com.fiap.dao.interfaces;

import java.util.List;

import br.com.fiap.beans.Funcionario;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.IDRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


public interface FuncionarioDAO {
	
	public List<Funcionario> listarFuncionarios();
	public void inserirFuncionario(Funcionario funcionario) throws IDRepetido;
	public Funcionario buscarFuncionario(int id) throws ObjetoNaoEncontrado;
	public void deletarFuncionario(int id) throws ObjetoNaoEncontrado;
	public Funcionario atualizarFuncionario(Funcionario Funcionario) throws DadoRepetido;

}
