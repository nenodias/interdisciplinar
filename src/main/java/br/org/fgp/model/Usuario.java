package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.org.fgp.model.enums.TipoUsuario;
import br.org.fgp.model.usertype.TipoUsuarioUserType;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdUsuario")
	private Integer id;
	
	@Column(name = "Login")
	private String login;
	
	@Column(name = "Senha")
	private String senha;
	
	@Column(name = "IdTipo")
	@Type(type = TipoUsuarioUserType.USER_TYPE)
	private TipoUsuario tipo;
	
	@ManyToOne
	@JoinColumn(name = "IdFuncionario")
	private Funcionario funcionario;

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
