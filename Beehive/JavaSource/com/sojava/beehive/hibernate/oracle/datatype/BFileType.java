package com.sojava.beehive.hibernate.oracle.datatype;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.sql.BFILE;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public class BFileType implements UserType, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2709978313430918845L;
	private static final Class<BFILE> RETURNED_CLASS = BFILE.class;
	private static final int[] SQL_TYPES = new int[] {OracleTypes.BFILE};

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
    	try {
    		return (BFILE) cached;
		} catch (HibernateException ex) {
			throw ex;
		} catch (Exception ex) {
    		throw new HibernateException("Could not assemble BFILE", ex);
    	}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		byte[] result = null;

		if (value != null) {
			result = ((byte[]) value).clone();
//			result = new byte[((byte[]) value).length-1];
//			System.arraycopy(value, 0, result, 0, result.length);
		}

		return result;
	}

	@Override
	public Serializable disassemble(Object obj) throws HibernateException {
		try {
			return ((BFILE) obj).getBytes();
		} catch (Exception ex) {
			throw new HibernateException("Could not disassemble BFILE to Serializable", ex);
		}
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return (x == y) || (x != null && y != null && Arrays.equals((byte[]) x, (byte[]) y)); 
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
		byte[] result = null;
		OracleResultSet ors = null;
		try {
			if (rs instanceof OracleResultSet) {
				ors = (OracleResultSet) rs;
			} else {
				throw new UnsupportedOperationException("ResultSet needs to be of type OracleResultSet");
			}
			BFILE file = ors.getBFILE(names[0]);
			if (file != null && file.fileExists()) {
				file.openFile();
				ByteArrayOutputStream bao = new ByteArrayOutputStream();
				InputStream in = file.binaryStreamValue();
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = in.read(buffer, 0, buffer.length)) > -1) {
					bao.write(buffer, 0, len);
					bao.flush();
				}
				bao.close();
				result = bao.toByteArray();
				file.closeFile();
			}
			return result;
		} catch (HibernateException ex) {
			throw new HibernateException("Could not execute BFileType.nullSafeGet:", ex);
		} catch (Exception ex) {
			throw new SQLException("Could not execute BFileType.nullSafeGet:", ex);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
		try {
			BFILE bf = null;
			if (value != null) {
				bf = new BFILE((OracleConnection) st.getConnection(), (byte[]) value);
			}

			((OraclePreparedStatement) st).setObject(index, bf);
		} catch (HibernateException ex) {
			throw new HibernateException("Could not execute BFileType.nullSafeSet:", ex);
		} catch (Exception ex) {
			throw new SQLException("Could not execute BFileType.nullSafeSet:", ex);
		}
	}

	@Override
	public Object replace(Object _orig, Object _tar, Object _owner) throws HibernateException {
		return deepCopy(_orig);
	}

	@Override
	public Class<BFILE> returnedClass() {
		return RETURNED_CLASS;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}
