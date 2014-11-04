package br.org.fgp.model.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value = "movimentacao")
public class MovimentacaoXML {

	private String tipo;
	private String data;
	private String mes;
	
	private List<ProdutoXML> produtos;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<ProdutoXML> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoXML> produtos) {
		this.produtos = produtos;
	}

}
