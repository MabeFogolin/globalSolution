package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Ong;
import br.com.fiap.dao.interfaces.OngDAO;
import br.com.fiap.exceptions.DadoRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;


public class OngDAOImpl implements OngDAO{
	
	private List<Ong> ongs = new ArrayList<>();

	@Override
	public List<Ong> listarOngs() {
		return ongs;
	}

	@Override
	public void inserirOng(Ong ong) throws DadoRepetido{
		if(ongs.stream().anyMatch(o -> o.getOngCnpj() == ong.getOngCnpj())) {
			throw new DadoRepetido("CNPJ já cadastrado, por favor verifique os parâmetros!\n");
		}
		ongs.add(ong);
		
	}

	@Override
	public Ong buscarOng(String cnpj) {
		for(Ong ongBuscar : ongs) {
			if(ongBuscar.getOngCnpj() == cnpj) {
				return ongBuscar;
			}
		}
		return null;
	}

	@Override
	public Ong atualizarOng(Ong ong) throws DadoRepetido {
		if(ongs.stream().anyMatch(o -> o.getOngCnpj() == ong.getOngCnpj())) {
			throw new DadoRepetido("CNPJ já cadastrado, por favor verifique os parâmetros!\n");
		}
		else {
			ong.setOngCnpj(ong.getOngCnpj());
			ong.setCidade(ong.getCidade());
			ong.setOngQtdFunc(ong.getOngQtdFunc());
			ong.setUf(ong.getUf());
			return ong;
		}
	}
	
	@Override
	public void deletarOng(String cnpj) throws ObjetoNaoEncontrado {
		Ong ongRemover = buscarOng(cnpj);
		if(ongRemover != null) {
			ongs.remove(ongRemover);
		} else throw new ObjetoNaoEncontrado();
		
	}

}
