package br.org.fgp.dao.impl;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.EntradaProdutoDao;
import br.org.fgp.dao.ProdutoDao;
import br.org.fgp.model.EntradaProduto;
import br.org.fgp.model.Produto;

@Repository
public class EntradaProdutoDaoImpl extends GenericoDaoImpl<EntradaProduto, Integer> implements EntradaProdutoDao {

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
	
	@Override
	public List<EntradaProduto> buscarPorFaixa(Date dataInicio, Date dataTermino){
		return buscarPorCriteria(Restrictions.between("data", dataInicio, dataTermino));
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
}
