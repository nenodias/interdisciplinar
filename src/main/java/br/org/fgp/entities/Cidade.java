package br.org.fgp.entities;

public class Cidade {
	public int CidId;
	public String CidCidade;

	public int EstId;
	public Estado Estado;
	
	public int getCidId() {
		return CidId;
	}
	public void setCidId(int cidId) {
		CidId = cidId;
	}
	public String getCidCidade() {
		return CidCidade;
	}
	public void setCidCidade(String cidCidade) {
		CidCidade = cidCidade;
	}
	public int getEstId() {
		return EstId;
	}
	public void setEstId(int estId) {
		EstId = estId;
	}
	public Estado getEstado() {
		return Estado;
	}
	public void setEstado(Estado estado) {
		Estado = estado;
	}
}
