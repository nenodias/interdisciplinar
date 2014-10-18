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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.model.usertype.TipoUsuarioUserType;

@Entity
@Table(name = "USUARIO", indexes = @Index(columnList = "IdEndereco") )
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdUsuario")
	private Integer id;
	
	@Column(name = "Login", length = 150)
	private String login;
	
	@Column(name = "Senha", length = 150)
	private String senha;
	
	@Column(name = "IdTipo")
	@Type(type = TipoUsuarioUserType.USER_TYPE)
	private TipoUsuario tipo;
	
	@Column(name = "Nome", length = 200)
	public String nome;
	
	@Column(name = "Cpf", length = 20)
	public String cpf;
	
	@Cascade(value = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "IdEndereco")
	public Endereco endereco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

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

}
