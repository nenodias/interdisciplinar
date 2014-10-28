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
@Table(name = "CONTATO_TELEFONE", indexes = { @Index(columnList = "IdContato"), @Index(columnList = "IdTelefone") }, uniqueConstraints = @UniqueConstraint(columnNames = {"IdContato", "IdTelefone"}))
public class ContatoTelefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "IdContato", nullable = false)
	public Contato contato;
	
	@ManyToOne
	@JoinColumn(name = "IdTelefone", nullable = false)
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
