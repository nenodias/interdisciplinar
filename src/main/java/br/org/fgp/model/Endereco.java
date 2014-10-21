package br.org.fgp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Endereco implements Serializable{
	
	private static final long serialVersionUID = 3771961905394408023L;

	@Column(name = "Rua", length = 50)
	public String rua;
	
	@Column(name = "Numero", length = 10)
	public String numero;
	
	@Column(name = "Bairro", length = 30)
	public String bairro;
	
	@ManyToOne
	@JoinColumn(name = "IdCidade")
	public Cidade cidade;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
