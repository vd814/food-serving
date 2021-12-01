package com.swing.foodserving.handler;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class MysqlCustomDilect extends MySQL5Dialect{

    public MysqlCustomDilect() {
        super();
    }

}
