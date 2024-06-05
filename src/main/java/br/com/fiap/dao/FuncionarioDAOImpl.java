package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Funcionario;
import br.com.fiap.dao.interfaces.FuncionarioDAO;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.IDRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class FuncionarioDAOImpl implements FuncionarioDAO {

	private List<Funcionario> funcionarios = new ArrayList<>();

	@Override
	public List<Funcionario> listarFuncionarios() {
		return funcionarios;
	}

	@Override
	public void inserirFuncionario(Funcionario funcionario) throws IDRepetido {
		if (funcionarios.stream().anyMatch(f -> f.getFuncId() == funcionario.getFuncId())) {
			System.err.println("Erro com o ID repetido.\n");

			// Ajustar o ID para ser o tamanho da lista + 1
			funcionario.setFuncId(funcionarios.size() + 1);
			throw new IDRepetido();
		}

		funcionarios.add(funcionario);
	}

	@Override
	public Funcionario buscarFuncionario(int id) throws ObjetoNaoEncontrado {
		for (Funcionario funcionarioProcurar : funcionarios) {
			try {
				if (funcionarioProcurar.getFuncId() == String.valueOf(id)) {
					return funcionarioProcurar;
				}
			} catch (NullPointerException e) {
				System.err
						.println("Não há este funcionário cadastrado, segue a lista dos funcionários cadastrados: \n");
				listarFuncionarios();
				throw new ObjetoNaoEncontrado();

			}
		}
		throw new ObjetoNaoEncontrado();
	}

	@Override
	public void deletarFuncionario(int id) throws ObjetoNaoEncontrado {
		Funcionario funcionarioRemover = buscarFuncionario(id);
		if (funcionarioRemover != null) {
			funcionarios.remove(funcionarioRemover);
		} else {
			throw new ObjetoNaoEncontrado();
		}

	}

	@Override
	public Funcionario atualizarFuncionario(Funcionario Funcionario) throws DadoRepetido {
		if (funcionarios.stream().anyMatch(f -> f.getFuncId() == Funcionario.getFuncId())) {
			throw new DadoRepetido("CNPJ já cadastrado, por favor verifique os parâmetros!\n");
		}
		Funcionario.setFuncEmail(Funcionario.getFuncEmail());
		Funcionario.setFuncSalario(Funcionario.getFuncSalario());
		Funcionario.setFuncSobrenome(Funcionario.getFuncSobrenome());
		Funcionario.setFuncTel(Funcionario.getFuncTel());
		return Funcionario;
	}

}
