package br.org.fgp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
@Entity
@Table(name = "CONTATO_FORNECEDOR", indexes = { @Index(columnList = "IdContato"), @Index(columnList = "IdFornecedor") }, uniqueConstraints = @UniqueConstraint(columnNames = {"IdContato", "IdFornecedor"}) )
public class ContatoFornecedor {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "IdContato",nullable = false)
	public Contato contato;
	
	@ManyToOne
	@JoinColumn(name = "IdFornecedor",nullable = false)
	public Fornecedor fornecedor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
