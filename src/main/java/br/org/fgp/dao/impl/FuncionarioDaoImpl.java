package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.FuncionarioDao;
import br.org.fgp.model.Funcionario;

@Repository
public class FuncionarioDaoImpl extends GenericoDaoImpl<Funcionario, Integer> implements FuncionarioDao {

}
