package com.learning.log4j;

import org.apache.log4j.Logger;

/**
 *@author zhengzh
 *@create 2013-7-16
 **/
public class ShowInfo {
	private static final Logger logger = Logger.getLogger(ShowInfo.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Logger root11 = Logger.getRootLogger();
		root11.info("root " + root11);
		logger.info("logger " + logger);
		logger.info(logger.getParent());
	}

}
