package br.org.fgp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VENDA", indexes = @Index( columnList = "IdUsuario" ) )
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVenda")
	public Integer id;
	
	@Column(name = "Data")
	public Date data;
	
	@Column(name = "ValorTotal", scale = 2, precision = 15)
	public BigDecimal valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "IdUsuario")
	public Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario funcionario) {
		this.usuario = funcionario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
