package br.org.fgp.entities;

import java.util.Date;

public class EntradaProduto {
	public int EntProdId;
	public int EntProdQuantidade;
	public Date EntProdData;
	public Double EntProdPrecoCusto;
	
	public int FornId;
	public Fornecedor Fornecedor;
	
	public int FuncId;
	public Funcionario Funcionario;
	
	public int ProdId;
	public Produto Produto;
	public int getEntProdId() {
		return EntProdId;
	}
	public void setEntProdId(int entProdId) {
		EntProdId = entProdId;
	}
	public int getEntProdQuantidade() {
		return EntProdQuantidade;
	}
	public void setEntProdQuantidade(int entProdQuantidade) {
		EntProdQuantidade = entProdQuantidade;
	}
	public Date getEntProdData() {
		return EntProdData;
	}
	public void setEntProdData(Date entProdData) {
		EntProdData = entProdData;
	}
	public Double getEntProdPrecoCusto() {
		return EntProdPrecoCusto;
	}
	public void setEntProdPrecoCusto(Double entProdPrecoCusto) {
		EntProdPrecoCusto = entProdPrecoCusto;
	}
	public int getFornId() {
		return FornId;
	}
	public void setFornId(int fornId) {
		FornId = fornId;
	}
	public Fornecedor getFornecedor() {
		return Fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		Fornecedor = fornecedor;
	}
	public int getFuncId() {
		return FuncId;
	}
	public void setFuncId(int funcId) {
		FuncId = funcId;
	}
	public Funcionario getFuncionario() {
		return Funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		Funcionario = funcionario;
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
}
