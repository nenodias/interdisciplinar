package br.org.fgp.model.usertype;

import br.org.fgp.core.usertype.IntegerUserType;
import br.org.fgp.model.enums.TipoUsuario;

public class TipoUsuarioUserType extends IntegerUserType<TipoUsuario> {

    public static final String USER_TYPE = "br.org.fgp.model.usertype.TipoUsuarioUserType";

    @Override
    public Class<TipoUsuario> returnedClass() {
        return TipoUsuario.class;
    }

    @Override
    protected TipoUsuario getValue(Integer codigo) {
        return TipoUsuario.generate(codigo);
    }

    @Override
    protected Integer getInteger(Object value) {
        Integer retorno = null;
        if (value instanceof TipoUsuario) {
            TipoUsuario tipo = (TipoUsuario) value;
            retorno = tipo.getCodigo();
        }
        return retorno;
    }
}
