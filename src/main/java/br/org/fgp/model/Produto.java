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

import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "PRODUTO", indexes = { @Index(columnList = "IdCategoria"), @Index(columnList = "IdMarca") }  )
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdProduto")
	public Integer id;
	
	@Column(name = "Nome")
	public String nome;
	
	@Column(name = "Descricao")
	public String descricao;
	
	@Column(name = "PrecoUnitario")
	public BigDecimal precoUnitario; //preco de venda
	
	@Column(name = "EstoqueMaximo")
	public Integer estoqueMaximo;
	
	@Column(name = "EstoqueMinimo")
	public Integer estoqueMinimo;
	
	@Column(name = "EstoqueAtual")
	public Integer estoqueAtual;
	
	@ManyToOne
	@JoinColumn(name = "IdCategoria")
	public Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "IdMarca")
	public Marca marca;

	@Pesquisa(nome = "Id", posicao = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Pesquisa(nome = "Nome", posicao = 1)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Pesquisa(nome = "Descrição", posicao = 2)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Pesquisa(nome = "Preço", posicao = 3)
	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Integer getEstoqueMaximo() {
		return estoqueMaximo;
	}

	public void setEstoqueMaximo(Integer estoqueMaximo) {
		this.estoqueMaximo = estoqueMaximo;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	@Pesquisa(nome = "Estoque", posicao = 4)
	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	@Pesquisa(nome = "Categoria", posicao = 5)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Pesquisa(nome = "Marca", posicao = 6)
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
