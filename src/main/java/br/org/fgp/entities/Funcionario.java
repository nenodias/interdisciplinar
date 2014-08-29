package br.org.fgp.entities;

public class Funcionario {
	public int FuncId;
	public String FuncNome;
	public String FuncCpf;
	public String FuncEndereco;
	
	public int CidId;
	public Cidade Cidade;
	
	
	public int getFuncId() {
		return FuncId;
	}
	public void setFuncId(int funcId) {
		FuncId = funcId;
	}
	public String getFuncNome() {
		return FuncNome;
	}
	public void setFuncNome(String funcNome) {
		FuncNome = funcNome;
	}
	public String getFuncCpf() {
		return FuncCpf;
	}
	public void setFuncCpf(String funcCpf) {
		FuncCpf = funcCpf;
	}
	public String getFuncEndereco() {
		return FuncEndereco;
	}
	public void setFuncEndereco(String funcEndereco) {
		FuncEndereco = funcEndereco;
	}
	public int getCidId() {
		return CidId;
	}
	public void setCidId(int cidId) {
		CidId = cidId;
	}
	public Cidade getCidade() {
		return Cidade;
	}
	public void setCidade(Cidade cidade) {
		Cidade = cidade;
	}
	
	
}
