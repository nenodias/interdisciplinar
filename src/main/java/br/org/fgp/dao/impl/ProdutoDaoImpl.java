package br.org.fgp.dao.impl;

import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.ValidationException;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.Produto;
import br.org.fgp.model.VendaItem;

@Repository
public class ProdutoDaoImpl extends GenericoDaoImpl<Produto, Integer> implements ProdutoDao, Filtravel<Produto> {

	private static final Integer ZERO = 0;

	@Override
	public List<Produto> filtrarPorDescricao(String descricao) {
		return buscarPorCriteria(Restrictions.like("nome", "%"+descricao+"%"));
	}

	@Override
	public Produto buscarPorId(Integer id) {
		Produto produto = super.buscarPorId(id);
		initialize(produto);
		return produto;
	}

	private void initialize(Produto produto) {
		if(produto.getCategoria() != null ){
			produto.getCategoria().getId();
		}
		if(produto.getMarca() != null ){
			produto.getMarca().getId();
		}
		
	}
	
	@Override
	public void removeItensEstoque(VendaItem vendaItem) {
		Produto produto = vendaItem.getProduto();
		int estoqueAtual = produto.getEstoqueAtual()-vendaItem.getQuantidade();
		if( produto.getEstoqueMaximo().equals(ZERO) || estoqueAtual >= ZERO ){
			produto.setEstoqueAtual(estoqueAtual);
		}else{
			JOptionPane.showMessageDialog(null, "Quantidade maior que estoque máximo");
			throw new ValidationException("Quantidade maior que estoque máximo");
		}
		salvar(produto);
		flush();
	}
	
	@Override
	public void adicionaItensEstoque(VendaItem vendaItem) {
		Produto produto = vendaItem.getProduto();
		int estoqueAtual = produto.getEstoqueAtual()+vendaItem.getQuantidade();
		produto.setEstoqueAtual(estoqueAtual);
		salvar(produto);
		flush();
	}
	
}
