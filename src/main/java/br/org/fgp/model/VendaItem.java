package br.org.fgp.model;

import java.math.BigDecimal;

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
@Table(name = "VENDA_ITEM", indexes = { @Index(columnList = "IdProduto"), @Index(columnList = "IdVenda") } )
public class VendaItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVendaItem")
	public Integer id;
	
	@Column(name = "Quantidade")
	public Integer quantidade;
	
	@Column(name = "ValorUnitario", scale = 2, precision = 15)
	public BigDecimal valorUnitario;
	
	@ManyToOne
	@JoinColumn(name = "IdProduto")
	public Produto produto;

	@ManyToOne
	@JoinColumn(name = "IdVenda")
	public Venda venda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
