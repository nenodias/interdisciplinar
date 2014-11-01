package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.org.fgp.core.MessagemUtil;

@Entity
@Table(name = "FORNECEDOR", indexes = @Index(columnList = "IdCidade" ) )
public class Fornecedor {

	private static final String RAZAO_SOCIAL = "Razao Social";

	private static final int RAZAO_SOCIAL_MAX = 200;

	private static final String NOME_FANTASIA = "Nome Fantasia";

	private static final int NOME_FANTASIA_MAX = 200;

	private static final int INSCRICAO_ESTADUAL_MAX = 20;

	private static final String CNPJ = "Cnpj";

	private static final int CNPJ_MAX = 20;

	private static final String INSCRICAO_ESTADUAL = "Inscrição Estadual";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdFornecedor")
	public Integer id;
	
	@NotBlank(message = MessagemUtil.CAMPO + CNPJ + MessagemUtil.NOT_BLANK)
	@Length(max = CNPJ_MAX, message = MessagemUtil.CAMPO + CNPJ + MessagemUtil.MAX + CNPJ_MAX )
	@Column(name = CNPJ, length = CNPJ_MAX)
	public String cnpj;
	
	@NotBlank(message = MessagemUtil.CAMPO + INSCRICAO_ESTADUAL + MessagemUtil.NOT_BLANK)
	@Length(max = INSCRICAO_ESTADUAL_MAX, message = MessagemUtil.CAMPO + INSCRICAO_ESTADUAL + MessagemUtil.MAX + INSCRICAO_ESTADUAL_MAX )
	@Column(name = "IncricaoEstadual", length = INSCRICAO_ESTADUAL_MAX)
	public String inscricaoEstadual;
	
	@NotBlank(message = MessagemUtil.CAMPO + NOME_FANTASIA + MessagemUtil.NOT_BLANK)
	@Length(max = NOME_FANTASIA_MAX, message = MessagemUtil.CAMPO + NOME_FANTASIA + MessagemUtil.MAX + NOME_FANTASIA_MAX )
	@Column(name = "NomeFantasia", length = NOME_FANTASIA_MAX)
	public String nomeFantasia;
	
	@NotBlank(message = MessagemUtil.CAMPO + RAZAO_SOCIAL + MessagemUtil.NOT_BLANK)
	@Length(max = RAZAO_SOCIAL_MAX, message = MessagemUtil.CAMPO + RAZAO_SOCIAL + MessagemUtil.MAX + RAZAO_SOCIAL_MAX )
	@Column(name = "RazaoSocial", length = RAZAO_SOCIAL_MAX)
	public String razaoSocial;
	
	@Valid
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
