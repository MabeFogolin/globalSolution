package br.com.fiap.dao.interfaces;

import java.util.List;

import br.com.fiap.beans.Oceanis;
import br.com.fiap.exceptions.IDRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


public interface OceanisDAO {
	
	public List<Oceanis> listarOceanis();
	public void inserirOceanis(Oceanis menuOceanis) throws IDRepetido;
	public Oceanis buscarOceanis(int numMenu);
	public Oceanis atualizarOceanis(Oceanis menuOceanis);
	public void deletarOceanis(int numMenu) throws ObjetoNaoEncontrado;
	

}
