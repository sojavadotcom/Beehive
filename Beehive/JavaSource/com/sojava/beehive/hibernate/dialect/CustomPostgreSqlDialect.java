package com.sojava.beehive.hibernate.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgresPlusDialect;

public class CustomPostgreSqlDialect extends PostgresPlusDialect {

	public CustomPostgreSqlDialect() {
		super();
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}
}
