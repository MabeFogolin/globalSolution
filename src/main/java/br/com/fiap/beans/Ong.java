package br.com.fiap.beans;

public class Ong {
	
	private String ongNome;
	private String ongCnpj;
	private int ongQtdFunc; //Quantidade de funcionários
	private String uf;
	private String cidade;
	private int fkOceanis; //Chave estrangeira do menu
	
	
	public String getOngNome() {
		return ongNome;
	}
	public void setOngNome(String ongNome) {
		this.ongNome = ongNome;
	}
	public String getOngCnpj() {
		return ongCnpj;
	}
	public void setOngCnpj(String ongCnpj) {
		this.ongCnpj = ongCnpj;
	}
	public int getOngQtdFunc() {
		return ongQtdFunc;
	}
	public void setOngQtdFunc(int ongQtdFunc) {
		this.ongQtdFunc = ongQtdFunc;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public int getFkOceanis() {
		return fkOceanis;
	}
	public void setFkOceanis(int fkOceanis) {
		this.fkOceanis = fkOceanis;
	}
	
	public Ong() {
		
	}
	public Ong(String ongNome, String ongCnpj, int ongQtdFunc, String uf, String cidade, int fkOceanis) {
		this.ongNome = ongNome;
		this.ongCnpj = ongCnpj;
		this.ongQtdFunc = ongQtdFunc;
		this.uf = uf;
		this.cidade = cidade;
		this.fkOceanis = fkOceanis;
	}
	@Override
	public String toString() {
		return "Ong: " + ongNome + "\nCNPJ: " + ongCnpj + "\nQuantidade de funcionários: " + ongQtdFunc + "\nUF: " + uf
				+ "\nCidade: " + cidade + "\n";
	}
	

}
