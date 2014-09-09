package br.org.fgp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.org.fgp.model.pk.ContatoFornecedorId;
@Entity
@Table(name = "CONTATO_FORNECEDOR")
public class ContatoFornecedor {

	
	@EmbeddedId
	ContatoFornecedorId id;
	
	@ManyToOne
	@JoinColumn(name = "IdContato", insertable = false, updatable = false)
	public Contato contato;
	
	@ManyToOne
	@JoinColumn(name = "IdFornecedor",insertable = false, updatable = false)
	public Fornecedor fornecedor;

	public ContatoFornecedorId getId() {
		return id;
	}

	public void setId(ContatoFornecedorId id) {
		this.id = id;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
