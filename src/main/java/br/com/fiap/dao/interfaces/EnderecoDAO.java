package br.com.fiap.dao.interfaces;

import java.util.List;

import br.com.fiap.beans.Endereco;

public interface EnderecoDAO {
	
	public List<Endereco> listarEnderecos();
	public void inserirEndereco(Endereco endereco);
	public Endereco buscarEndereco(String rua);
	public Endereco atualizarEndereco(Endereco endereco);
	public void deletarEndereco(String rua);

}
