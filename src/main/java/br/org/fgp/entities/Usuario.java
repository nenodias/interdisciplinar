package br.org.fgp.entities;

public class Usuario {
	public int UsuId;
	public String UsuLogin;
	public String UsuSenha;
	public int UsuTipo;

	public int FuncId;
	public Funcionario Funcionario;
	public int getUsuId() {
		return UsuId;
	}
	public void setUsuId(int usuId) {
		UsuId = usuId;
	}
	public String getUsuLogin() {
		return UsuLogin;
	}
	public void setUsuLogin(String usuLogin) {
		UsuLogin = usuLogin;
	}
	public String getUsuSenha() {
		return UsuSenha;
	}
	public void setUsuSenha(String usuSenha) {
		UsuSenha = usuSenha;
	}
	public int getUsuTipo() {
		return UsuTipo;
	}
	public void setUsuTipo(int usuTipo) {
		UsuTipo = usuTipo;
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
