package br.org.fgp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.org.fgp.core.dao.Filtravel;
import br.org.fgp.core.dao.impl.GenericoDaoImpl;
import br.org.fgp.dao.UsuarioDao;
import br.org.fgp.model.Usuario;
import br.org.fgp.model.UsuarioTelefone;

@Repository
public class UsuarioDaoImpl extends GenericoDaoImpl<Usuario, Integer> implements UsuarioDao, Filtravel<Usuario> {

    @Override
    public Usuario buscarPorLogin(String login) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("login", login);
        return selectPrimeiroHQL(" FROM Usuario u where u.login = :login ", parametros);
    }

    @Override
    public List<Usuario> filtrarPorDescricao(String descricao) {
        return buscarPorCriteria(Restrictions.like("login", "%" + descricao + "%"));
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        Usuario usuario = super.buscarPorId(id);
        iniciarChavesEstrangeiras(usuario);
        return usuario;
    }

    private void iniciarChavesEstrangeiras(Usuario usuario) {
        if (usuario.getListaTelefone() != null) {
            for (UsuarioTelefone usuarioTelefone : usuario.getListaTelefone()) {
                usuarioTelefone.getId();
                usuarioTelefone.getTelefone().getId();
            }
        }
        if (usuario.getEndereco().getCidade() != null) {
            usuario.getEndereco().getCidade().getId();
            if (usuario.getEndereco().getCidade().getEstado() != null) {
                usuario.getEndereco().getCidade().getEstado().getId();
            }
        }
    }

}
