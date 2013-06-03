package com.learning.java.thread;

public class MaxThreadNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int num = 10000 ;
		int i = 0 ;
		for(;i <num ; i++) {
			new Thread(){
				public void run() {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				};
			}.start();
		}
	}

}
