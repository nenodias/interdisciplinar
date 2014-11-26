package br.org.fgp.dao.impl;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Produto;

@Repository
public class EntradaProdutoDaoImpl extends GenericoDaoImpl<EntradaProduto, Integer> implements EntradaProdutoDao, Filtravel<EntradaProduto> {

	private static final int ZERO = 0;
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Override
	public void salvar(EntradaProduto entity){
		super.salvar(entity);
	}
	
	@Transactional
	@Override
	public void salvarRegra(EntradaProduto entity)throws Exception{
		Produto produto = entity.getProduto();
		int estoqueAtual = produto.getEstoqueAtual()+entity.getQuantidade();
		if( produto.getEstoqueMaximo().equals(ZERO) || estoqueAtual >= produto.getEstoqueMaximo() ){
			produto.setEstoqueAtual(estoqueAtual);
		}else{
			JOptionPane.showMessageDialog(null, "Quantidade maior que estoque máximo");
			throw new ValidationException("Quantidade maior que estoque máximo");
		}
		salvar(entity);
		produtoDao.salvar(produto);
	}
	
	@Transactional
	@Override
	public List<EntradaProduto> buscarPorFaixa(Date dataInicio, Date dataTermino){
		List<EntradaProduto> lista = buscarPorCriteriaOrder(Restrictions.between("data", dataInicio, dataTermino), Order.asc("data"));
		initialize(lista);
		return lista;
	}
	
	@Override
	public void deletar(Integer id) {
		EntradaProduto entradaProduto = buscarPorId(id);
		Produto produto = entradaProduto.getProduto();
		int estoqueAtual = produto.getEstoqueAtual()-entradaProduto.getQuantidade();
		produto.setEstoqueAtual(estoqueAtual);
		produtoDao.salvar(produto);
		flush();
		super.deletar(id);
	}
	
	private void initialize(List<EntradaProduto> compra) {
		for (EntradaProduto entradaProduto : compra) {
			initialize(entradaProduto);
		}
	}
	
	
	private void initialize(EntradaProduto compra) {
		if(compra.getUsuario() != null ){
			compra.getUsuario().getId();
		}
		if(compra.getProduto() != null){
			compra.getProduto().getId();
		}
		if(compra.getFornecedor() != null){
			compra.getFornecedor().getId();
		}
	}
	
	@Transactional
	@Override
	public EntradaProduto buscarPorId(Integer id) {
		EntradaProduto entidade = super.buscarPorId(id);
		initialize(entidade);
		return entidade;
	}

	@Override
	public List<EntradaProduto> filtrarPorDescricao(String descricao) {
		return getSessaoAtual().createCriteria(EntradaProduto.class, "t").createAlias("t.usuario", "u").add(Restrictions.like("u.nome", "%"+descricao+"%")).list();
	}
}
