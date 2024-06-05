package br.com.fiap.beans;

/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class Oceanis {

	private int idMenu;
	private String ufAcesso;
	
	public int getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(int id_menu) {
		this.idMenu = id_menu;
	}
	public String getUfAcesso() {
		return ufAcesso;
	}
	public void setUfAcesso(String ufAcesso) {
		this.ufAcesso = ufAcesso;
	}
	
	
	public Oceanis() {

	}
	
	public Oceanis(int id_menu, String ufAcesso) {
		this.idMenu = id_menu;
		this.ufAcesso = ufAcesso;
	}
	@Override
	public String toString() {
		return "Oceanis \n" + idMenu + "\nufAcesso=" + ufAcesso + "\n";
	}
	
	
	
	
}
