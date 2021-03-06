package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import br.org.fgp.core.MessagemUtil;
import br.org.fgp.model.enums.TipoTelefone;
import br.org.fgp.model.usertype.TipoTelefoneUserType;


@Entity
@Table(name = "TELEFONE")
public class Telefone {

	private static final int TELEFONE_MAX = 20;

	private static final String TELEFONE = "Telefone";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdTelefone")
	private Integer id;
	
	@Length(max = TELEFONE_MAX, message = MessagemUtil.CAMPO + TELEFONE + MessagemUtil.MAX + TELEFONE_MAX )
	@Column(name = TELEFONE, length = TELEFONE_MAX)
	public String telefone;
	
	@Type(type = TipoTelefoneUserType.USER_TYPE)
	@Column(name = "Tipo")
	public TipoTelefone tipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return telefone;
	}

}
