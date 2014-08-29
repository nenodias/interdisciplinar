package br.org.fgp.entities;

import java.util.Date;

public class Pedido {
	public int PedId;
	public Date PedData;
	public Double PedValorTotal;
	
	public int FuncId;
	public Funcionario Funcionario;
	public int getPedId() {
		return PedId;
	}
	public void setPedId(int pedId) {
		PedId = pedId;
	}
	public Date getPedData() {
		return PedData;
	}
	public void setPedData(Date pedData) {
		PedData = pedData;
	}
	public Double getPedValorTotal() {
		return PedValorTotal;
	}
	public void setPedValorTotal(Double pedValorTotal) {
		PedValorTotal = pedValorTotal;
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
}
