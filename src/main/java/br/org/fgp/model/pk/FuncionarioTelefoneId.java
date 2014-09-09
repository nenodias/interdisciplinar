package br.org.fgp.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FuncionarioTelefoneId implements Serializable{

	private static final long serialVersionUID = -2833221620401589330L;

	@Column(name = "IdFuncionario")
	private Integer idFuncionario;
	
	@Column(name = "IdTelefone")
	private Integer idTelefone;
	
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Integer getIdTelefone() {
		return idTelefone;
	}
	public void setIdTelefone(Integer idTelefone) {
		this.idTelefone = idTelefone;
	}
	
	
}
