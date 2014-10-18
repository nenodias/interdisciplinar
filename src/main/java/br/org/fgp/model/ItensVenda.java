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
@Table(name = "ITENSPEDIDOS", indexes = { @Index(columnList = "IdProduto"), @Index(columnList = "IdPedido") } )
public class ItensVenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdItensPedido")
	public Integer id;
	
	@Column(name = "Quantidade")
	public Integer quantidade;
	
	@Column(name = "ValorUnitario", scale = 2, precision = 15)
	public BigDecimal valorUnitario;
	
	@ManyToOne
	@JoinColumn(name = "IdProduto")
	public Produto produto;

	@ManyToOne
	@JoinColumn(name = "IdPedido")
	public Venda pedido;

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

	public Venda getPedido() {
		return pedido;
	}

	public void setPedido(Venda pedido) {
		this.pedido = pedido;
	}

}
