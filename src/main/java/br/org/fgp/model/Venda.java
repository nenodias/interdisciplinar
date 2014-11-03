package br.org.fgp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "VENDA", indexes = @Index( columnList = "IdUsuario" ) )
public class Venda {

	private static final int VALOR_MAX = 15;

	private static final String VALOR = "Valor Total";

	private static final int VALOR_FRACAO = 2;

	private static final int VALOR_INT = 13;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdVenda")
	public Integer id;
	
	@Column(name = "Data")
	public Date data;
	
	@Digits(integer = VALOR_INT, fraction = VALOR_FRACAO, message = MessagemUtil.CAMPO + VALOR + MessagemUtil.DIGITS + VALOR_MAX + MessagemUtil.INTEIROS + MessagemUtil.AND+VALOR_FRACAO + MessagemUtil.CASAS_DECIMAIS)
	@Column(name = "ValorTotal", scale = VALOR_FRACAO, precision = VALOR_MAX)
	public BigDecimal valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "IdUsuario")
	public Usuario usuario;
	
	@Valid
	@OneToMany(mappedBy = "venda")
	public List<VendaItem> listaItem;

	@Pesquisa(nome = "Código", posicao = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Pesquisa(nome = "Data", posicao = 1)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@Pesquisa(nome = "Funcionário", posicao = 2)
	public String getUsuarioTexto() {
		return usuario.getNome();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario funcionario) {
		this.usuario = funcionario;
	}

	@Pesquisa(nome = VALOR, posicao = 3)
	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
