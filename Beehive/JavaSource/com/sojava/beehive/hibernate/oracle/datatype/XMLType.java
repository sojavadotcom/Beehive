package com.sojava.beehive.hibernate.oracle.datatype;

import java.io.Serializable;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.sql.OPAQUE;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class XMLType implements UserType, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2946638757487604076L;
	private static final Class<Document> returnedClass = Document.class;
	private static final int[] SQL_TYPES = new int[] {oracle.xdb.XMLType._SQL_TYPECODE};

    @Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
    	try {
    		return new SAXReader().read(new InputSource(new StringReader((String) cached)));
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception ex) {
    		throw new HibernateException("Could not assemble String to Document", ex);
    	}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value != null ? ((Document) value).clone() : value;
	}

	@Override
	public Serializable disassemble(Object obj) throws HibernateException {
		try {
			return ((Document) obj).asXML();
		} catch (Exception ex) {
			throw new HibernateException("Could not disassemble Document to Serializable", ex);
		}
	}

	@Override
	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		if (arg0 == null && arg1 == null) return true;
		else if (arg0 == null && arg1 != null) return false;
		else return arg0.equals(arg1);
	}

	@Override
	public int hashCode(Object _obj) throws HibernateException {
		return _obj.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImplementor, Object value) throws HibernateException ,SQLException {
		Document doc = null;
		OPAQUE op = null;
		OracleResultSet ors = null;
		try {
			if (rs instanceof OracleResultSet) {
				ors = (OracleResultSet) rs;
			} else {
				throw new UnsupportedOperationException("ResultSet needs to be of type OracleResultSet");
			}
			op = ors.getOPAQUE(names[0]);
			if (op != null) {
				doc = new SAXReader().read(new InputSource(new StringReader(oracle.xdb.XMLType.createXML(op).getClobVal().stringValue())));
			}
		} catch (HibernateException ex) {
			throw new HibernateException("Could not execute XMLType.nullSafeGet:", ex);
		} catch (Exception ex) {
			throw new SQLException("Could not execute XMLType.nullSafeGet:", ex);
		}

		return doc;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
		try {
			oracle.xdb.XMLType xmlType = null;
			if (value != null) {
				xmlType = oracle.xdb.XMLType.createXML(st.getConnection(), ((Document) value).asXML());
			} else {
				xmlType = null;
			}

			((OraclePreparedStatement) st).setObject(index, xmlType);
		} catch (HibernateException ex) {
			throw new HibernateException("Could not execute XMLType.nullSafeSet:", ex);
		} catch (Exception ex) {
			throw new SQLException("Could not execute XMLType.nullSafeSet:", ex);
		}
	}

	@Override
	public Object replace(Object _orig, Object _tar, Object _owner) throws HibernateException {
		return deepCopy(_orig);
	}

	@Override
	public Class<Document> returnedClass() {
		return returnedClass;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}
