package br.org.fgp.model.xml;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GerarXml {
	
	public static void main(String[] args) {
		DadosXML dados = new DadosXML();
			dados.setLucroBruto(new  BigDecimal("23.50"));
			dados.setLucroLiquido(new  BigDecimal("20.50"));
			List<MovimentacaoXML> movimentacoes = new ArrayList<MovimentacaoXML>();
			
				MovimentacaoXML movimentacoes1 = new MovimentacaoXML();
				movimentacoes1.setTipo("VENDA");
				movimentacoes1.setData("10/10/2014");
				movimentacoes1.setMes("Outubro");	
			movimentacoes.add(movimentacoes1);
				
			List<ProdutoXML> produtos = new ArrayList<ProdutoXML>();
			
			movimentacoes1.setProdutos(produtos);
			
			ProdutoXML produtos0 = new ProdutoXML();
			produtos0.setDescricao("Coca-Cola");
			produtos0.setPreco(new BigDecimal("4.50"));
			produtos0.setQuantidade(10);
			
			produtos.add(produtos0);
			
			ProdutoXML produtos1 = new ProdutoXML();
			produtos1.setDescricao("Coca-Cola Zero");
			produtos1.setPreco(new BigDecimal("5.50"));
			produtos1.setQuantidade(5);
			
			produtos.add(produtos1);
				
			MovimentacaoXML movimentacoes2 = new MovimentacaoXML();
				movimentacoes2.setTipo("COMPRA");
				movimentacoes2.setData("10/10/2014");
				movimentacoes2.setMes("Outubro");	
				movimentacoes2.setProdutos(new ArrayList<ProdutoXML>());
				ProdutoXML produtos2 = new ProdutoXML();
				produtos2 = new ProdutoXML();
				produtos2.setDescricao("Coca-Cola");
				produtos2.setPreco(new BigDecimal("2.50"));
				produtos2.setQuantidade(10);
				
				ProdutoXML produtos3 = new ProdutoXML();
				produtos3.setDescricao("Coca-Cola Zero");
				produtos3.setPreco(new BigDecimal("3.50"));
				produtos3.setQuantidade(5);
			
				movimentacoes2.getProdutos().add(produtos2);
				movimentacoes2.getProdutos().add(produtos3);
			
			dados.setMovimentacao(movimentacoes);
			
			
		XStream xStream = new XStream(new DomDriver());
		xStream.autodetectAnnotations(true);
		System.out.println(xStream.toXML(dados));
	}
	
}
