package br.org.fgp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.org.fgp.model.pk.FuncionarioTelefoneId;

@Entity
@Table(name = "FUNCIONARIO_TELEFONE")
public class FuncionarioTelefone {

	@EmbeddedId
	private FuncionarioTelefoneId id;	
	
	@ManyToOne
	@JoinColumn(name = "IdFuncionario", insertable = false, updatable = false)
	public Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "IdTelefone", insertable = false, updatable = false)
	public Telefone telefone;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
