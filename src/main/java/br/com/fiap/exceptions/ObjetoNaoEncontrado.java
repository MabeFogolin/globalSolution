package br.com.fiap.exceptions;

public class ObjetoNaoEncontrado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String mensagem = "Resultado da busca não encontrada, revise os parâmetros solicitados ou reveja os dados cadastrados!";
	
	public ObjetoNaoEncontrado() {
		super(mensagem);
	}

	public ObjetoNaoEncontrado(Throwable erro) {
		super(mensagem, erro);
	}
}
