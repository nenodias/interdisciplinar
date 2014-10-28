package br.org.fgp.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioTelefoneId implements Serializable{

	private static final long serialVersionUID = -2833221620401589330L;

	@Column(name = "IdUsuario")
	private Integer idUsuario;
	
	@Column(name = "IdTelefone")
	private Integer idTelefone;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(Integer idTelefone) {
		this.idTelefone = idTelefone;
	}
	
}
