package com.learning.java.variableparameterized;


/**
 * 可变参数永远不可能是null,当没有任何参数的时候，默认长度为0
 * @author zhengzh
 *
 */
public class SeeParameterValue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		show();
	}
	
	public static void show(Class<?>... groups) {
		if(groups ==null) {
			System.out.println("groups is null.");
		}
		if(groups != null ) {
			System.out.println("groups length: " + groups.length);
		}
	}

}
