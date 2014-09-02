package br.org.fgp.model;

public class Fornecedor {

	public Integer id;
	public String cnpj;
	public String inscricaoEstadual;
	public String nomeFantasia;
	public String razaoSocial;
	public String enderecoComercial;
	public Cidade cidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEnderecoComercial() {
		return enderecoComercial;
	}

	public void setEnderecoComercial(String enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
