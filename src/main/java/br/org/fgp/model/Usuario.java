package br.org.fgp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.model.usertype.TipoUsuarioUserType;
import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "USUARIO", indexes = {@Index(columnList = "IdCidade" )}, uniqueConstraints = {@UniqueConstraint(columnNames = {Usuario.CPF } ), @UniqueConstraint(columnNames = {Usuario.LOGIN } )} )
public class Usuario {

	protected static final String TIPO = "IdTipo";
	protected static final int CPF_MAX = 20;
	protected static final String CPF = "Cpf";
	protected static final int NOME_MAX = 200;
	protected static final String NOME = "Nome";
	protected static final int SENHA_MAX = 150;
	protected static final String SENHA = "Senha";
	protected static final int LOGIN_MAX = 150;
	protected static final String LOGIN = "Login";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdUsuario")
	private Integer id;
	
	@NotBlank(message = MessagemUtil.CAMPO + LOGIN + MessagemUtil.NOT_BLANK)
	@Length(max = LOGIN_MAX, message = MessagemUtil.CAMPO + LOGIN + MessagemUtil.MAX + LOGIN_MAX )
	@Column(name = LOGIN, length = LOGIN_MAX)
	private String login;
	
	@NotBlank(message = MessagemUtil.CAMPO + SENHA + MessagemUtil.NOT_BLANK)
	@Length(max = SENHA_MAX, message = MessagemUtil.CAMPO + SENHA + MessagemUtil.MAX + SENHA_MAX )
	@Column(name = SENHA, length = SENHA_MAX)
	private String senha;
	
	@Column(name = TIPO)
	@Type(type = TipoUsuarioUserType.USER_TYPE)
	private TipoUsuario tipo;

	@NotBlank(message = MessagemUtil.CAMPO + NOME + MessagemUtil.NOT_BLANK)
	@Length(max = NOME_MAX, message = MessagemUtil.CAMPO + NOME + MessagemUtil.MAX + NOME_MAX )
	@Column(name = NOME, length = NOME_MAX)
	public String nome;
	
	@NotBlank(message = MessagemUtil.CAMPO + CPF + MessagemUtil.NOT_BLANK)
	@Length(max = CPF_MAX, message = MessagemUtil.CAMPO + CPF + MessagemUtil.MAX + CPF_MAX)
	@Column(name = CPF, length = CPF_MAX)
	@org.hibernate.validator.constraints.br.CPF(message = MessagemUtil.CAMPO + CPF + MessagemUtil.INVALIDO)
	public String cpf;
	
	@Valid
	@Embedded
	public Endereco endereco;
	
	@OneToMany(mappedBy = "usuario")
	public List<UsuarioTelefone> listaTelefone;

	@Pesquisa(nome = "Código", posicao = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Pesquisa(nome = LOGIN, posicao = 1)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Pesquisa(nome = "Tipo", posicao = 2)
	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	@Pesquisa(nome = NOME, posicao = 3)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<UsuarioTelefone> getListaTelefone() {
		return listaTelefone;
	}

	public void setListaTelefone(List<UsuarioTelefone> listaTelefone) {
		this.listaTelefone = listaTelefone;
	}

}
