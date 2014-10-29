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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Entity
@Table(name = "CONTATO", indexes = @Index(columnList = "IdSetor") )
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdContato")	
	public Integer id;
	
	@Column(name = "Email" , length = 200)
	public String email;
	
	@Column(name = "Nome", length = 200)
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

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder().append(id).append(email).append(nome);
		return builder.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Contato){
			Contato other = (Contato)obj;
			EqualsBuilder builder = new EqualsBuilder().append(this.id, other.id).append(this.email, other.email).append(this.nome, other.nome);
			return builder.isEquals();
		}
		return false;
	}
	
}
