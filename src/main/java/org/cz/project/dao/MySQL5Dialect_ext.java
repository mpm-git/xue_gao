package org.cz.project.dao;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class MySQL5Dialect_ext extends org.hibernate.dialect.MySQL5Dialect {
	public MySQL5Dialect_ext() {
		super();
		registerFunction("regexp", new SQLFunctionTemplate(Hibernate.BOOLEAN,
				"?1 REGEXP ?2"));
		registerFunction("now", new SQLFunctionTemplate(Hibernate.BOOLEAN,
				"now()"));
		
		registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING,
				"convert(?1 using ?2)"));
	}
}