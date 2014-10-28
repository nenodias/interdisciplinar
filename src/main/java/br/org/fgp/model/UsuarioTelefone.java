package br.org.fgp.model;

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

import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "USUARIO_TELEFONE", indexes = { @Index(columnList = "IdUsuario" ), @Index(columnList = "IdTelefone" ) } )
public class UsuarioTelefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@Cascade(value = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "IdUsuario", nullable = false)
	public Usuario usuario;

	@Cascade(value = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "IdTelefone", nullable = false)
	public Telefone telefone;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Pesquisa(nome = "Telefone", posicao = 0)
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
