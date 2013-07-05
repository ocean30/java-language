package com.banclogix;

public class EumerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(State.A);
		System.out.println(State.A.name());
		System.out.println(State.B.ordinal());
		System.out.println(State.valueOf("B").getMessage());
	}
	


}
