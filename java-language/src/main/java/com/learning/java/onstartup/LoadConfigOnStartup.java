package com.learning.java.onstartup;

/**
 *@author zhengzh
 *@create 2013-7-23
 **/
public class LoadConfigOnStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//see run configurations argument
		System.out.println(System.getProperty("a"));
		System.out.println(System.getenv("a"));
	}

}
