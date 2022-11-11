package br.org.fgp.core.usertype;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

public abstract class StringUserType<T> extends GenericUserType {

    private static final Logger LOGGER = Logger.getLogger(StringUserType.class);

    protected abstract T getValue(String codigo);

    protected abstract String getString(Object value);

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
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
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {
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
