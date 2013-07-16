package com.learning.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Hello {

	private static final Logger logger = Logger.getLogger(Hello.class);

	public static void main(String argv[]) {
		BasicConfigurator.configure();
		logger.debug("Hello world.");
		logger.info("What a beatiful day.");
	}
}