package br.org.fgp.entities;

public class FuncionarioTelefone {
	public int FuncId;
	public Funcionario Funcionario;
	
	public int TelId;
	public Telefone Telefone;
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
	public int getTelId() {
		return TelId;
	}
	public void setTelId(int telId) {
		TelId = telId;
	}
	public Telefone getTelefone() {
		return Telefone;
	}
	public void setTelefone(Telefone telefone) {
		Telefone = telefone;
	}
	
}
