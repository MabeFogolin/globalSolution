package br.com.fiap.dao.interfaces;

import java.util.List;

import br.com.fiap.beans.Ong;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


public interface OngDAO {

	public List<Ong> listarOngs();
	public void inserirOng(Ong ong) throws DadoRepetido;
	public Ong buscarOng(String cnpj);
	public Ong atualizarOng(Ong ong) throws DadoRepetido;
	public void deletarOng(String cnpj) throws ObjetoNaoEncontrado;
	
}
