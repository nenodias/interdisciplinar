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
@Table(name = "ENTRADA_PRODUTO", indexes = { @Index(columnList = "IdFornecedor" ), @Index(columnList = "IdFuncionario" ), @Index(columnList = "IdProduto" ) } )
public class EntradaProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdEntradaProduto")
	public Integer id;
	
	@Column(name = "Quantidade")
	public Integer quantidade;
	
	@Column(name = "Data")
	public Date data;
	
	@Column(name = "PrecoCusto")
	public BigDecimal precoCusto;
	
	@ManyToOne
	@JoinColumn(name = "IdFornecedor")
	public Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "IdFuncionario")
	public Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "IdProduto")
	public Produto Produto;

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

}
