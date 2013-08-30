package com.learning.util.math;

/**
 *@author zhengzh
 *@create 2013-8-23
 **/
public class Digist {
	
	/**
	 * 外汇常用整数来说明小数位数，然后通过该整数对点（即最后一位）进行加减
	 * 
	 * @param digistLength
	 * @return
	 */
	public static int getDigist(int digistLength) {
		return new Integer((int) Math.pow(10,digistLength));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double n1 = 1.001;
		int n2 = 1 ;
		System.out.println(n1+n2);
		System.out.println(getDigist(4));
	}

}
