package br.org.fgp.core.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StringUserType<T> extends GenericUserType {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUserType.class);

    protected abstract T getValue(String codigo);

    protected abstract String getString(Object value);

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String codigo = rs.getString(names[0]);
        if (rs.wasNull() && codigo != null) {
            return null;
        }
        T value = getValue(codigo);
        if (value != null) {
            return value;
        }
        LOGGER.info("Codigo " + returnedClass().getSimpleName() + " desconhecido");
        return null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
        } else {
            st.setString(index, getString(value));
        }
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }
}
