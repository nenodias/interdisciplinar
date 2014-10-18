package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.FuncionarioTelefoneDao;
import br.org.fgp.model.UsuarioTelefone;

@Repository
public class FuncionarioTelefoneDaoImpl extends GenericoDaoImpl<UsuarioTelefone, UsuarioTelefone> implements FuncionarioTelefoneDao {

}
