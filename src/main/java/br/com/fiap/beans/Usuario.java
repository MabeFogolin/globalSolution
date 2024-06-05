package br.com.fiap.beans;

/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class Usuario {
	
	private String userNome;
	private int userIdade;
	private String userEmail;
	private String userSenha;
	private String userCep;
	private int fkOceanis; //Chave estrangeira do menu
	
	
	public String getUserNome() {
		return userNome;
	}
	public void setUserNome(String userNome) {
		this.userNome = userNome;
	}
	public int getUserIdade() {
		return userIdade;
	}
	public void setUserIdade(int userIdade) {
		this.userIdade = userIdade;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserSenha() {
		return userSenha;
	}
	public void setUserSenha(String userSenha) {
		this.userSenha = userSenha;
	}
	public String getUserCep() {
		return userCep;
	}
	public void setUserCep(String userCep) {
		this.userCep = userCep;
	}
	public int getFkOceanis() {
		return fkOceanis;
	}
	public void setFkOceanis(int fkOceanis) {
		this.fkOceanis = fkOceanis;
	}
	
	public Usuario() {
	
	}
	
	public Usuario(String userNome, int userIdade, String userEmail, String userCep) {
		this.userNome = userNome;
		this.userIdade = userIdade;
		this.userEmail = userEmail;
		this.userCep = userCep;
	}
	public Usuario(String userNome, int userIdade, String userEmail, String userSenha, String userCep, int fkOceanis) {
		this.userNome = userNome;
		this.userIdade = userIdade;
		this.userEmail = userEmail;
		this.userSenha = userSenha;
		this.userCep = userCep;
		this.fkOceanis = fkOceanis;
	}
	@Override
	public String toString() {
		return "Usuario: " + userNome + " Idade: " + userIdade + " Email: " + userEmail
				+" Cep: " + userCep + "\n";
	}
	
	 
}
