package br.com.fiap.beans;

import java.util.Calendar;

public class Voluntario {
	
	private String volId;
	private String volCpf;
	private String volNome;
	private String volSobrenome;
	private long volTel;
	private Calendar dataInscricao= Calendar.getInstance();
	private String fkOng;
	
	public String getVolId() {
		return volId;
	}
	public void setVolId(String volId) {
		this.volId = volId;
	}
	public String getVolCpf() {
		return volCpf;
	}
	public void setVolCpf(String volCpf) {
		this.volCpf = volCpf;
	}
	public String getVolNome() {
		return volNome;
	}
	public void setVolNome(String volNome) {
		this.volNome = volNome;
	}
	public String getVolSobrenome() {
		return volSobrenome;
	}
	public void setVolSobrenome(String volSobrenom) {
		this.volSobrenome = volSobrenom;
	}
	public long getVolTel() {
		return volTel;
	}
	public void setVolTel(long volTel) {
		this.volTel = volTel;
	}
	public Calendar getDataInscricao() {
		return dataInscricao;
	}
	public void setDataInscricao(Calendar dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	
	public String getFkOng() {
		return fkOng;
	}
	public void setFkOng(String fkOng) {
		this.fkOng = fkOng;
	}
	
	public Voluntario() {
		
	}
	
	
	public Voluntario(String volCpf, String volNome, String volSobrenome, long volTel, String fkOng) {
		this.volCpf = volCpf;
		this.volNome = volNome;
		this.volSobrenome = volSobrenome;
		this.volTel = volTel;
		this.fkOng = fkOng;
	}
	
	public Voluntario(String volId, String volCpf, String volNome, String volSobrenom, long volTel, String fkOng) {
		this.volId = volId;
		this.volCpf = volCpf;
		this.volNome = volNome;
		this.volSobrenome = volSobrenom;
		this.volTel = volTel;
		this.fkOng = fkOng;
	}
	public Voluntario(String volId, String volCpf, String volNome, String volSobrenom, long volTel,
			Calendar dataInscricao, String fkOng) {
		this.volId = volId;
		this.volCpf = volCpf;
		this.volNome = volNome;
		this.volSobrenome = volSobrenom;
		this.volTel = volTel;
		this.dataInscricao = dataInscricao;
		this.fkOng = fkOng;
	}
	@Override
	public String toString() {
		return "Voluntario: " +  "Nome: " + volNome + " Sobrenome: "
				+ volSobrenome + " Telefone: " + volTel + " ONG: " + fkOng + "\n";
	}
	

}
