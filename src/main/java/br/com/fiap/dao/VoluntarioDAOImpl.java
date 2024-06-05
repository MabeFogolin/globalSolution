package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Voluntario;
import br.com.fiap.dao.interfaces.VoluntarioDAO;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.IDRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class VoluntarioDAOImpl implements VoluntarioDAO{
	
	private List<Voluntario> voluntarios = new ArrayList<>();

	@Override
	public List<Voluntario> listarVoluntarios() {
		return voluntarios;
	}

	@Override
	public void inserirVoluntario(Voluntario voluntario) throws IDRepetido {
		  if (voluntarios.stream().anyMatch(v -> v.getVolId() == voluntario.getVolId())) {
		        System.err.println("Erro com o ID repetido.\n");
		        
		        // Ajustar o ID para ser o tamanho da lista + 1
		        voluntario.setVolId(String.valueOf(voluntarios.size() + 1));
		        throw new IDRepetido();
		    }
		    voluntarios.add(voluntario);
	}

	@Override
	public Voluntario buscarVoluntario(String cpf) throws ObjetoNaoEncontrado {
		for (Voluntario voluntarioProcurar : voluntarios) {
			try {
				if (voluntarioProcurar.getVolCpf() == cpf) {
					return voluntarioProcurar;
				}
			} catch (NullPointerException e) {
				System.err.println("Não há este voluntário cadastrado, segue a lista dos funcionários cadastrados: \n");
				listarVoluntarios();
				throw new ObjetoNaoEncontrado();
			}
		}
		throw new ObjetoNaoEncontrado();
	}
	

	@Override
	public Voluntario atualizarVoluntario(Voluntario voluntario) throws DadoRepetido {
		 if (voluntarios.stream().anyMatch(v -> v.getVolId() == voluntario.getVolId())) {
			 throw new DadoRepetido("ID já cadastrado, por favor verifique os parâmetros!\n");
		 }
		voluntario.setVolCpf(voluntario.getVolCpf());
		voluntario.setVolNome(voluntario.getVolNome());
		voluntario.setVolSobrenome(voluntario.getVolSobrenome());
		voluntario.setVolTel(voluntario.getVolTel());
		return voluntario;
	}

	@Override
	public void deletarVoluntario(String cpf) throws ObjetoNaoEncontrado {
		Voluntario voluntarioRemover = buscarVoluntario(cpf);
		if(voluntarioRemover != null) {
			voluntarios.remove(voluntarioRemover);
		} else {
			throw new ObjetoNaoEncontrado();
		}
		
	}

}
