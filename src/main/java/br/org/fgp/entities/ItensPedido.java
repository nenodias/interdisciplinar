package br.org.fgp.entities;

public class ItensPedido {
	public int ItPedId;
	public int ItPedQuantidade;
	public Double ItPedValorUnitario;
	
	public int ProdId;
	public Produto Produto;
	
	public int PedId;
	public Pedido Pedido;
	public int getItPedId() {
		return ItPedId;
	}
	public void setItPedId(int itPedId) {
		ItPedId = itPedId;
	}
	public int getItPedQuantidade() {
		return ItPedQuantidade;
	}
	public void setItPedQuantidade(int itPedQuantidade) {
		ItPedQuantidade = itPedQuantidade;
	}
	public Double getItPedValorUnitario() {
		return ItPedValorUnitario;
	}
	public void setItPedValorUnitario(Double itPedValorUnitario) {
		ItPedValorUnitario = itPedValorUnitario;
	}
	public int getProdId() {
		return ProdId;
	}
	public void setProdId(int prodId) {
		ProdId = prodId;
	}
	public Produto getProduto() {
		return Produto;
	}
	public void setProduto(Produto produto) {
		Produto = produto;
	}
	public int getPedId() {
		return PedId;
	}
	public void setPedId(int pedId) {
		PedId = pedId;
	}
	public Pedido getPedido() {
		return Pedido;
	}
	public void setPedido(Pedido pedido) {
		Pedido = pedido;
	}
	
}
