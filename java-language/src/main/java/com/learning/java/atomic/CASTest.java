package com.learning.java.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static class Cas {
		private AtomicInteger counter = new AtomicInteger(3);

		public void addCounter() {
		    for (;;) {
		        int oldValue = counter.get();
		        int newValue = oldValue + 1;
		        //虚机保证CAS的原子性
		        if (counter.compareAndSet(oldValue, newValue))
		            return;
		    }
		}

	}

}
