package br.org.fgp.model.usertype;

import br.org.fgp.core.usertype.IntegerUserType;
import br.org.fgp.model.enums.TipoTelefone;

public class TipoTelefoneUserType extends IntegerUserType<TipoTelefone> {

    public static final String USER_TYPE = "br.org.fgp.model.usertype.TipoTelefoneUserType";

    @Override
    public Class<TipoTelefone> returnedClass() {
        return TipoTelefone.class;
    }

    @Override
    protected TipoTelefone getValue(Integer codigo) {
        return TipoTelefone.generate(codigo);
    }

    @Override
    protected Integer getInteger(Object value) {
        Integer retorno = null;
        if (value instanceof TipoTelefone) {
            TipoTelefone tipo = (TipoTelefone) value;
            retorno = tipo.getCodigo();
        }
        return retorno;
    }
}
