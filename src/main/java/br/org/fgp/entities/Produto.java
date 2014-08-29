package br.org.fgp.entities;

public class Produto {
	public int ProdId;
	public String ProdNome;
	public String ProdDescricao;
	public Double ProdPrecoUnitario;
	public int ProdEstoqueMaximo;
	public int ProdEstoqueMinimo;
	public int ProdEstoqueAtual;
	
	public int CatId;
	public Categoria Categoria;
	public int MarcId;
	public Marca Marca;
	
	public int getProdId() {
		return ProdId;
	}
	public void setProdId(int prodId) {
		ProdId = prodId;
	}
	public String getProdNome() {
		return ProdNome;
	}
	public void setProdNome(String prodNome) {
		ProdNome = prodNome;
	}
	public String getProdDescricao() {
		return ProdDescricao;
	}
	public void setProdDescricao(String prodDescricao) {
		ProdDescricao = prodDescricao;
	}
	public Double getProdPrecoUnitario() {
		return ProdPrecoUnitario;
	}
	public void setProdPrecoUnitario(Double prodPrecoUnitario) {
		ProdPrecoUnitario = prodPrecoUnitario;
	}
	public int getProdEstoqueMaximo() {
		return ProdEstoqueMaximo;
	}
	public void setProdEstoqueMaximo(int prodEstoqueMaximo) {
		ProdEstoqueMaximo = prodEstoqueMaximo;
	}
	public int getProdEstoqueMinimo() {
		return ProdEstoqueMinimo;
	}
	public void setProdEstoqueMinimo(int prodEstoqueMinimo) {
		ProdEstoqueMinimo = prodEstoqueMinimo;
	}
	public int getProdEstoqueAtual() {
		return ProdEstoqueAtual;
	}
	public void setProdEstoqueAtual(int prodEstoqueAtual) {
		ProdEstoqueAtual = prodEstoqueAtual;
	}
	public int getCatId() {
		return CatId;
	}
	public void setCatId(int catId) {
		CatId = catId;
	}
	public Categoria getCategoria() {
		return Categoria;
	}
	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
	}
	public int getMarcId() {
		return MarcId;
	}
	public void setMarcId(int marcId) {
		MarcId = marcId;
	}
	public Marca getMarca() {
		return Marca;
	}
	public void setMarca(Marca marca) {
		Marca = marca;
	}	
}
