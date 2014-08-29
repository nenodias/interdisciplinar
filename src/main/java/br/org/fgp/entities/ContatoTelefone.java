package br.org.fgp.entities;

public class ContatoTelefone {
	public int ContId;
	public Contato Contato;
	
	public int TelId;
	public Telefone Telefone;
	public int getContId() {
		return ContId;
	}
	public void setContId(int contId) {
		ContId = contId;
	}
	public Contato getContato() {
		return Contato;
	}
	public void setContato(Contato contato) {
		Contato = contato;
	}
	public int getTelId() {
		return TelId;
	}
	public void setTelId(int telId) {
		TelId = telId;
	}
	public Telefone getTelefone() {
		return Telefone;
	}
	public void setTelefone(Telefone telefone) {
		Telefone = telefone;
	}
	
}
