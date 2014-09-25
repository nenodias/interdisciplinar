package br.org.fgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.org.fgp.view.annotations.LabelDescricao;
import br.org.fgp.view.annotations.Pesquisa;

@Entity
@Table(name = "MARCA")
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdMarca")
	public Integer id;
	
	@Column(name = "Marca")
	@LabelDescricao
	public String marca;

	@Pesquisa(nome = "Id", posicao = 0)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Pesquisa(nome = "Marca", posicao = 1)
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
