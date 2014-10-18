package br.org.fgp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.org.fgp.model.pk.FuncionarioTelefoneId;

@Entity
@Table(name = "USUARIO_TELEFONE", indexes = { @Index(columnList = "IdUsuario" ), @Index(columnList = "IdTelefone" ) } )
public class UsuarioTelefone {

	@EmbeddedId
	private FuncionarioTelefoneId id;	
	
	@ManyToOne
	@JoinColumn(name = "IdUsuario", insertable = false, updatable = false)
	public Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "IdTelefone", insertable = false, updatable = false)
	public Telefone telefone;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public FuncionarioTelefoneId getId() {
		return id;
	}

	public void setId(FuncionarioTelefoneId id) {
		this.id = id;
	}

}
