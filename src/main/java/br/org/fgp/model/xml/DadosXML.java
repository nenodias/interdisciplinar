package br.org.fgp.model.xml;

import java.math.BigDecimal;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias(value = "dados")
public class DadosXML {
	
	@XStreamImplicit
	private List<MovimentacaoXML> movimentacao;
	
	private BigDecimal lucroBruto;
	private BigDecimal lucroLiquido;

	public List<MovimentacaoXML> getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(List<MovimentacaoXML> movimentacao) {
		this.movimentacao = movimentacao;
	}

	public BigDecimal getLucroBruto() {
		return lucroBruto;
	}

	public void setLucroBruto(BigDecimal lucroBruto) {
		this.lucroBruto = lucroBruto;
	}

	public BigDecimal getLucroLiquido() {
		return lucroLiquido;
	}

	public void setLucroLiquido(BigDecimal lucroLiquido) {
		this.lucroLiquido = lucroLiquido;
	}

}
