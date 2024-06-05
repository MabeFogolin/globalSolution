package br.com.fiap.exceptions;

public class DadoRepetido extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5899477015664566971L;

	public DadoRepetido(String mensagem) {
		super(mensagem);
	}
}
