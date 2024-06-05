package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Endereco;
import br.com.fiap.dao.interfaces.EnderecoDAO;


/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class EnderecoDAOImpl implements EnderecoDAO {

	private List<Endereco> enderecos = new ArrayList<>();

	@Override
	public List<Endereco> listarEnderecos() {
		return enderecos;
	}

	@Override
	public void inserirEndereco(Endereco endereco) {
		enderecos.add(endereco);
	}

	@Override
	public Endereco buscarEndereco(String email) {
		for (Endereco enderecoProcurar : enderecos) {
			if (enderecoProcurar.getFkUsuario().equals(email)) {
				return enderecoProcurar;
			}
		}
		return null;
	}

	@Override
	public Endereco atualizarEndereco(Endereco endereco) {
		endereco.setFkUsuario(endereco.getFkUsuario());
		endereco.setCidade(endereco.getCidade());
		endereco.setRua(endereco.getRua());
		endereco.setUf(endereco.getUf());

		return endereco;

	}

	@Override
	public void deletarEndereco(String email) {
		Endereco enderecoRemover = buscarEndereco(email);
		if (enderecoRemover != null) {
			enderecos.remove(enderecoRemover);
		} else {
			System.err.println("Endereço não encontrado, reveja o parâmetro passado");
		}
	}

}
