package com.sojava.beehive.hibernate.dialect;

import oracle.jdbc.OracleTypes;
import oracle.xdb.XMLType;

import org.hibernate.dialect.Oracle10gDialect;

public class Oracle11gDialect extends Oracle10gDialect {
	public Oracle11gDialect() {
		super();
		registerHibernateType(OracleTypes.BFILE, "BFileType");
		registerColumnType(OracleTypes.BFILE, "BFileType");
		registerHibernateType(XMLType._SQL_TYPECODE, "XMLType");
		registerColumnType(XMLType._SQL_TYPECODE, "XMLType");
	}
}
