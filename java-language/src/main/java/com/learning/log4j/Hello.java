package com.learning.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Hello {
	//define
	private static final Logger logger = Logger.getLogger(Hello.class);
	public static void main(String argv[]) {
//		BasicConfigurator.configure();
		//usage
		logger.debug("Hello world.");
		logger.info("What a beatiful day.");
		try {
			throw new Exception("System Error");
		}catch (Exception e) {
			logger.error("system error.", e);
		}
	}
}