package br.com.fiap.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Oceanis;
import br.com.fiap.dao.interfaces.OceanisDAO;
import br.com.fiap.exceptions.IDRepetido;
import br.com.fiap.exceptions.ObjetoNaoEncontrado;



/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class OceanisDAOImpl implements OceanisDAO{
	
	private List<Oceanis> menus = new ArrayList<Oceanis>();

	@Override
	public List<Oceanis> listarOceanis() {
		return menus;
	}

	@Override
	public void inserirOceanis(Oceanis menuOceanis) throws IDRepetido {
		if(menus.stream().anyMatch(o -> o.getIdMenu() == menuOceanis.getIdMenu())) {
			 System.err.println("Erro com o ID repetido.\n");
			 menuOceanis.setIdMenu(menus.size() + 1);
			 throw new IDRepetido();
		}
		
		menus.add(menuOceanis);
	}

	@Override
	public Oceanis buscarOceanis(int numMenu) {
		for(Oceanis menuProcurar : menus) {
			if(menuProcurar.getIdMenu() == numMenu) {
				return menuProcurar;
			}
		}
		return null;
	}

	@Override
	public Oceanis atualizarOceanis(Oceanis menuOceanis) {
		menuOceanis.setIdMenu(menuOceanis.getIdMenu());
		menuOceanis.setUfAcesso(menuOceanis.getUfAcesso());
		return menuOceanis;
	}

	@Override
	public void deletarOceanis(int numMenu) throws ObjetoNaoEncontrado {
		Oceanis menuRemover = buscarOceanis(numMenu);
		if(menuRemover != null) {
			menus.remove(numMenu);
		} else throw new ObjetoNaoEncontrado();
		
	}

}
