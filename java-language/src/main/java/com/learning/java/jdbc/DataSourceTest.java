package com.learning.java.jdbc;

import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *@author zhengzh
 *@create 2013-7-15
 **/
public class DataSourceTest {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws SQLException, NamingException {
		Context ctx=new InitialContext();
		DataSource datasourceRef=(DataSource) ctx.lookup("java:MySqlDS"); //引用数据源
		datasourceRef.getConnection();
	}

}
