package com.learning.java.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.learning.util.StdOutErrLog;

/**
 * test jdbc load
 *@author zhengzh
 *@create 2013-7-15
 **/
public class JDBCTest {
	private static final Logger logger = Logger.getLogger(JDBCTest.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//把out输出重定向到logger
		StdOutErrLog.tieSystemOutAndErrToLog();
		JDBCTest jdbc = new JDBCTest();
		//加载数据库配置文件
		jdbc.loadProperties();
		//获取数据库连接
		Connection conn = jdbc.getConnection();
		logger.info("conn: " + conn);
//		System.out.println("hello");
//		throw new RuntimeException("for test log ");
	}

	private String dbdriver;
	private String dburl;
	private String dbuser;
	private String dbpassword;
	private Connection conn;
	
	/**
	 * connect database
	 * @return Connection
	 */
	public Connection getConnection()
	{		
		try {
			//把out输出重定向到logger
			PrintWriter out = null;
//			out = new PrintWriter(System.out){
//				@Override
//				public void print(String s) {
//					logger.info(s);
//				}
//			};
			out = new PrintWriter(System.out);
			//设置DriverManager输出流
			DriverManager.setLogWriter(out);
			
			Class.forName(dbdriver);
			conn = DriverManager.getConnection(dburl,dbuser,dbpassword); 
			
			logger.info("kdfadmin connection is successful");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * Read server information from config file
	 */
	private void loadProperties()
	{
		FileInputStream fis = null;
		try {
			Properties pro=new Properties();
			String path = this.getClass().getResource("/").getPath() + "dbinfo.properties";
			fis = new FileInputStream(path);
//			fis = new FileInputStream(new File(path));
			pro.load(fis);
			this.dbdriver=pro.getProperty("dbdriver");
			this.dburl=pro.getProperty("dburl");
			this.dbuser=pro.getProperty("dbuser");
			this.dbpassword=pro.getProperty("dbpassword");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
		logger.info("read config file is successful and dburl = ["+this.dburl+"] user = ["+this.dbuser+"] password = ["+this.dbpassword+"]");		
	}

}