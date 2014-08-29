package br.org.fgp.entities;

public class Contato {
	public int ContId;
	public String ContEmail;
	public String ContNome;
	
	public int SetId;
	public Setor Setor;
	
	public int FornId;
	public Fornecedor Fornecedor;
	public int getContId() {
		return ContId;
	}
	public void setContId(int contId) {
		ContId = contId;
	}
	public String getContEmail() {
		return ContEmail;
	}
	public void setContEmail(String contEmail) {
		ContEmail = contEmail;
	}
	public String getContNome() {
		return ContNome;
	}
	public void setContNome(String contNome) {
		ContNome = contNome;
	}
	public int getSetId() {
		return SetId;
	}
	public void setSetId(int setId) {
		SetId = setId;
	}
	public Setor getSetor() {
		return Setor;
	}
	public void setSetor(Setor setor) {
		Setor = setor;
	}
	public int getFornId() {
		return FornId;
	}
	public void setFornId(int fornId) {
		FornId = fornId;
	}
	public Fornecedor getFornecedor() {
		return Fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		Fornecedor = fornecedor;
	}
	
	
}
