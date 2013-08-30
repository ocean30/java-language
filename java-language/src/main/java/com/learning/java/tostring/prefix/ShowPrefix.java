package com.learning.java.tostring.prefix;

/**
 * toString方法前缀含义，' [ '表示数组，' L '表示类
 * 详见@see java.lang.Class  的 getName()方法说明
 * @author zhengzh
 *
 */
public class ShowPrefix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(String.class.getName());
		System.out.println(int.class.getName());
		System.out.println(byte.class.getName());
		System.out.println(long.class.getName());
		System.out.println(void.class.getName());
		System.out.println((new Object[3]).getClass().getName());
		System.out.println((new int[3][4][5][6][7][8][9]).getClass().getName());

	}

}
