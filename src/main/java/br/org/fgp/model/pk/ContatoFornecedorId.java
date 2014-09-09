package br.org.fgp.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContatoFornecedorId implements Serializable {

	private static final long serialVersionUID = 6405007476610992709L;

	@Column(name = "IdFornecedor")
	private Integer idFornecedor;
	
	@Column(name = "IdContato")
	private Integer idContato;

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getIdContato() {
		return idContato;
	}

	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}
	
}
