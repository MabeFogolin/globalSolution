package br.com.fiap.exceptions;

public class IDRepetido extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2303333260110193005L;
	private static String mensagem = "ID repetido, este número já está atribuído, por default, pegamos o último ID utilizado e adicionamos + 1";
	
	public IDRepetido() {
		super(mensagem);
	}
	
	public IDRepetido(Throwable erro) {
		super(mensagem, erro);
	}
}
