package br.com.fiap.beans;

public class EnderecoViaCep {
	
	private String cep;
	private String logradouro;
	private String localidade;
	private String uf;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public EnderecoViaCep() {
		
	}
	
	public EnderecoViaCep(String cep, String logradouro, String localidade, String uf) {
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.localidade = localidade;
		this.uf = uf;
		
	}

	@Override
	public String toString() {
		return "Endereco CEP: " + cep + "Logradouro: " + logradouro + "Cidade: " + localidade + "UF: " + uf
				+ "\n";
	}
	
	
	
	
	
}
