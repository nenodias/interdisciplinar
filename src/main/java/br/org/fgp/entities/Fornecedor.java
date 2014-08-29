package br.org.fgp.entities;

public class Fornecedor {
	public int FornId;
	public String FornCnpj;
	public String FornInscricaoEstadual;
	public String FornNomeFantasia;
	public String ForRazaoSocial;
	public String FornEnderecoComercial;	
	
	public int CidId;
	public Cidade Cidade;
	
	public int getFornId() {
		return FornId;
	}
	public void setFornId(int fornId) {
		FornId = fornId;
	}
	public String getFornCnpj() {
		return FornCnpj;
	}
	public void setFornCnpj(String fornCnpj) {
		FornCnpj = fornCnpj;
	}
	public String getFornInscricaoEstadual() {
		return FornInscricaoEstadual;
	}
	public void setFornInscricaoEstadual(String fornInscricaoEstadual) {
		FornInscricaoEstadual = fornInscricaoEstadual;
	}
	public String getFornNomeFantasia() {
		return FornNomeFantasia;
	}
	public void setFornNomeFantasia(String fornNomeFantasia) {
		FornNomeFantasia = fornNomeFantasia;
	}
	public String getForRazaoSocial() {
		return ForRazaoSocial;
	}
	public void setForRazaoSocial(String forRazaoSocial) {
		ForRazaoSocial = forRazaoSocial;
	}
	public String getFornEnderecoComercial() {
		return FornEnderecoComercial;
	}
	public void setFornEnderecoComercial(String fornEnderecoComercial) {
		FornEnderecoComercial = fornEnderecoComercial;
	}
	public int getCidId() {
		return CidId;
	}
	public void setCidId(int cidId) {
		CidId = cidId;
	}
	public Cidade getCidade() {
		return Cidade;
	}
	public void setCidade(Cidade cidade) {
		Cidade = cidade;
	}
}
