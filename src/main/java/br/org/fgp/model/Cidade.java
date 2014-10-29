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
@Table(name = "CIDADE",indexes = @Index(columnList = "IdEstado") )
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCidade")
	public Integer id;
	
	@Column(name = "Cidade" , length = 40)
	public String descricao;
	
	@ManyToOne
	@JoinColumn(name = "IdEstado", nullable = false)
	public Estado estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder().append(id).append(descricao);
		return builder.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Cidade){
			Cidade other = (Cidade)obj;
			EqualsBuilder builder = new EqualsBuilder().append(this.id, other.id).append(this.descricao, other.descricao);
			return builder.isEquals();
		}
		return false;
	}
	
}
