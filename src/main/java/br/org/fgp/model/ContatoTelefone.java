package br.org.fgp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.org.fgp.model.pk.ContatoTelefoneId;

@Entity
@Table(name = "CONTATO_TELEFONE")
public class ContatoTelefone {

	@EmbeddedId
	ContatoTelefoneId id;
	
	@ManyToOne
	@JoinColumn(name = "IdContato", insertable = false, updatable = false)
	public Contato contato;
	
	@ManyToOne
	@JoinColumn(name = "IdTelefone", insertable = false, updatable = false)
	public Telefone telefone;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}
