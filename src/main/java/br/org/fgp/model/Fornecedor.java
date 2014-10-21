package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "FORNECEDOR", indexes = @Index(columnList = "IdCidade" ) )
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdFornecedor")
	public Integer id;
	
	@Column(name = "Cnpj", length = 20)
	public String cnpj;
	
	@Column(name = "IncricaoEstadual", length = 20)
	public String inscricaoEstadual;
	
	@Column(name = "NomeFantasia", length = 200)
	public String nomeFantasia;
	
	@Column(name = "RazaoSocial", length = 200)
	public String razaoSocial;
	
	@Embedded
	public Endereco enderecoComercial;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Endereco getEnderecoComercial() {
		return enderecoComercial;
	}

	public void setEnderecoComercial(Endereco enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}

}
