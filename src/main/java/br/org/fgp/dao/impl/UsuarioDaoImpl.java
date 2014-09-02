package br.org.fgp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.enums.TipoUsuario;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

	@Override
	public Usuario buscarPorId(Integer id) {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setLogin("nenodias");
		usuario.setSenha("******");
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		usuario.setFuncionario(null);
		return usuario;
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void salvar(Usuario entidade) {
		// TODO Auto-generated method stub
		
	}

}
