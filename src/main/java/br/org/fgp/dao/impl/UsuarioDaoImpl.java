package br.org.fgp.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;

@Transactional
@Repository
public class UsuarioDaoImpl extends GenericoDaoImpl<Usuario, Integer> implements UsuarioDao{	

}
