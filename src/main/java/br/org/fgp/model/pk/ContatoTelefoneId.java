package br.org.fgp.model.pk;

import java.io.Serializable;

import javax.persistence.Column;

public class ContatoTelefoneId implements Serializable{

	private static final long serialVersionUID = -8145566659081882120L;

	@Column(name = "IdContato")
	private Integer idContato;
	
	@Column(name = "IdTelefone")
	private Integer idTelefone;

	public Integer getIdContato() {
		return idContato;
	}

	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}

	public Integer getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Integer idTelefone) {
		this.idTelefone = idTelefone;
	}
	
	
}
