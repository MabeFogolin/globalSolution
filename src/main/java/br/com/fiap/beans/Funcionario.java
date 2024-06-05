package br.com.fiap.beans;

/**
 * @author </strong> Maria Beatriz
 * @author </strong> Nathalia Comeron
 * @author </strong> Nicholas Lima
 * @version 17.0.4.1
 */

public class Funcionario {

	private String funcId;
	private String funcCpf;
	private String funcNome;
	private String funcSobrenome;
	private long funcTel;
	private float funcSalario;
	private String funcEmail;
	private String funcSenha;
	private int fkOceanis; // Chave estrangeira do menu

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(int id) {
		this.funcId = String.valueOf(id);
	}

	public String getFuncCpf() {
		return funcCpf;
	}

	public void setFuncCpf(String funcCpf) {
		this.funcCpf = funcCpf;
	}

	public String getFuncNome() {
		return funcNome;
	}

	public void setFuncNome(String funcNome) {
		this.funcNome = funcNome;
	}

	public String getFuncSobrenome() {
		return funcSobrenome;
	}

	public void setFuncSobrenome(String funcSobrenome) {
		this.funcSobrenome = funcSobrenome;
	}

	public long getFuncTel() {
		return funcTel;
	}

	public void setFuncTel(long funcTel) {
		this.funcTel = funcTel;
	}

	public float getFuncSalario() {
		return funcSalario;
	}

	public void setFuncSalario(float funcSalario) {
		this.funcSalario = funcSalario;
	}

	public String getFuncEmail() {
		return funcEmail;
	}

	public void setFuncEmail(String funcEmail) {
		this.funcEmail = funcEmail;
	}

	public String getFuncSenha() {
		return funcSenha;
	}

	public void setFuncSenha(String funcSenha) {
		this.funcSenha = funcSenha;
	}

	public int getFkOceanis() {
		return fkOceanis;
	}

	public void setFkOceanis(int fkOceanis) {
		this.fkOceanis = fkOceanis;
	}

	public Funcionario(String funcId, String funcCpf, String funcNome, String funcSobrenome, long funcTel,
			float funcSalario) {
		this.funcId = funcId;
		this.funcCpf = funcCpf;
		this.funcNome = funcNome;
		this.funcSobrenome = funcSobrenome;
		this.funcTel = funcTel;
		this.funcSalario = funcSalario;

	}

	public Funcionario(String funcId, String funcCpf, String funcNome, String funcSobrenome, long funcTel,
			float funcSalario, int fkOceanis) {
		this.funcId = funcId;
		this.funcCpf = funcCpf;
		this.funcNome = funcNome;
		this.funcSobrenome = funcSobrenome;
		this.funcTel = funcTel;
		this.funcSalario = funcSalario;
		this.fkOceanis = fkOceanis;
	}

	public Funcionario(String funcId, String funcCpf, String funcNome, String funcSobrenome, long funcTel,
			float funcSalario, String funcEmail, String funcSenha, int fkOceanis) {
		this.funcId = funcId;
		this.funcCpf = funcCpf;
		this.funcNome = funcNome;
		this.funcSobrenome = funcSobrenome;
		this.funcTel = funcTel;
		this.funcSalario = funcSalario;
		this.funcEmail = funcEmail;
		this.funcSenha = funcSenha;
		this.fkOceanis = fkOceanis;
	}

	@Override
	public String toString() {
		return "Funcionario ID: " + funcId + "CPF: " + funcCpf + "Nome: " + funcNome + "Sobrenome: " + funcSobrenome
				+ ", funcTel=" + funcTel + ", funcSalario=" + funcSalario + ", funcEmail=" + funcEmail + "\n";
	}

}
