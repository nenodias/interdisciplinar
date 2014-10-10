package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "CONTATO", indexes = @Index(columnList = "IdSetor") )
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdContato")
	public Integer id;
	
	@Column(name = "Email")
	public String email;
	
	@Column(name = "Nome")
	public String nome;
	
	@ManyToOne
	@JoinColumn(name = "IdSetor")
	public Setor setor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

}
