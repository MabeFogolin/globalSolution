package br.com.fiap.beans;

/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class Endereco {
	
	private String fkUsuario; //Chave estrangeira do Usuario
	private String cidade;
	private String rua;
	private String uf;
	
	
	public String getFkUsuario() {
		return fkUsuario;
	}
	public void setFkUsuario(String fkUsuario) {
		this.fkUsuario = fkUsuario;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public Endereco() {
	
	}
	public Endereco(String fkUsuario, String cidade, String rua, String uf) {
		this.fkUsuario = fkUsuario;
		this.cidade = cidade;
		this.rua = rua;
		this.uf = uf;
	}
	@Override
	public String toString() {
		return "EnderecoViaCep: \nUsuario=" + fkUsuario + "\nCidade: " + cidade + "\nRua: " + rua + "\nUF: " + uf + "\n";
	}
	

	
}
