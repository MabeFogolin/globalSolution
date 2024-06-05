package br.com.fiap.dao.interfaces;

import java.util.List;

import br.com.fiap.beans.Voluntario;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.IDRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;

public interface VoluntarioDAO {
	
	public List<Voluntario> listarVoluntarios();
	public void inserirVoluntario(Voluntario voluntario) throws IDRepetido;
	public Voluntario buscarVoluntario(String cpf) throws ObjetoNaoEncontrado;
	public Voluntario atualizarVoluntario(Voluntario voluntario) throws DadoRepetido;
	public void deletarVoluntario(String cpf) throws ObjetoNaoEncontrado;

}
