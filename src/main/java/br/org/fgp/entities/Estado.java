package br.org.fgp.entities;
public class Estado {
	public int EstId;
	public String EstEstado;
	
	public int PaisId;
	public Pais pais;
	
	
	public int getEstId() {
		return EstId;
	}
	public void setEstId(int estId) {
		EstId = estId;
	}
	public String getEstEstado() {
		return EstEstado;
	}
	public void setEstEstado(String estEstado) {
		EstEstado = estEstado;
	}
	public int getPaisId() {
		return PaisId;
	}
	public void setPaisId(int paisId) {
		PaisId = paisId;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
}
