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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.view.annotations.Pesquisa;
@Entity
@Table(name = "ENTRADA_PRODUTO", indexes = { @Index(columnList = "IdFornecedor" ), @Index(columnList = "IdUsuario" ), @Index(columnList = "IdProduto" ) } )
public class EntradaProduto {
	
	private static final String QUANTIDADE = "Quantidade";

	private static final String PRODUTO = "Produto";

	private static final String FORNECEDOR = "Fornecedor";

	private static final int PRECO_MAX = 15;

	private static final int PRECO_INT = 13;

	private static final int PRECO_FRACAO = 2;

	private static final String PRECO = "Preço Unitário";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdEntradaProduto")
	public Integer id;

	@NotNull(message = MessagemUtil.CAMPO + QUANTIDADE + MessagemUtil.NOT_BLANK)
	@Column(name = QUANTIDADE)
	public Integer quantidade;
	
	@Column(name = "Data")
	public Date data;
	
	@Digits(integer = PRECO_INT, fraction = PRECO_FRACAO, message = MessagemUtil.CAMPO + PRECO + MessagemUtil.DIGITS + PRECO_MAX + MessagemUtil.INTEIROS + MessagemUtil.AND+PRECO_FRACAO + MessagemUtil.CASAS_DECIMAIS)
	@Column(name = "PrecoCusto", scale = PRECO_FRACAO, precision = PRECO_MAX)
	public BigDecimal precoCusto;
	
	@NotNull(message = MessagemUtil.CAMPO + FORNECEDOR + MessagemUtil.NOT_BLANK)
	@ManyToOne
	@JoinColumn(name = "IdFornecedor")
	public Fornecedor fornecedor;
	
	@ManyToOne
	@JoinColumn(name = "IdUsuario")
	public Usuario usuario;
	
	@NotNull(message = MessagemUtil.CAMPO + PRODUTO + MessagemUtil.NOT_BLANK)
	@ManyToOne
	@JoinColumn(name = "IdProduto")
	public Produto Produto;

	@Pesquisa(nome = "Código", posicao = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Pesquisa(nome = PRODUTO, posicao = 1)
	public String getProdutoTexto(){
		return Produto.getNome();
	}

	@Pesquisa(nome = QUANTIDADE, posicao = 2)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Pesquisa(nome = "Data", posicao = 3)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Pesquisa(nome = "Preço", posicao = 4)
	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	@Pesquisa(nome = FORNECEDOR, posicao = 5)
	public String getFornecedorTexto() {
		return fornecedor.getNomeFantasia();
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Pesquisa(nome = "Funcionário", posicao = 6)
	public String getUsuarioTexto() {
		return usuario.getNome();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario funcionario) {
		this.usuario = funcionario;
	}

	public Produto getProduto() {
		return Produto;
	}

	public void setProduto(Produto produto) {
		Produto = produto;
	}

}
