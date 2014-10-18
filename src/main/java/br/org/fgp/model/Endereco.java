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

@Entity
@Table(name = "ENDERECO", indexes = {@Index(columnList = "IdCidade" ),@Index(columnList = "IdUsuario" ),@Index(columnList = "IdFornecedor" )})
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdEndereco")
	public Integer id;
	
	@Column(name = "Rua", length = 50)
	public String rua;
	
	@Column(name = "Numero", length = 10)
	public String numero;
	
	@Column(name = "Bairro", length = 30)
	public String bairro;
	
	@ManyToOne
	@JoinColumn(name = "IdCidade", nullable = false)
	public Cidade cidade;
	
	@ManyToOne
	@JoinColumn(name = "IdUsuario", nullable = true)
	public Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "IdFornecedor", nullable = true)
	public Fornecedor fornecedor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
