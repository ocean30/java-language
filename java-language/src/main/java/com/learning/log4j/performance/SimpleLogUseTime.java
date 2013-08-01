package com.learning.log4j.performance;

import org.apache.log4j.Logger;

import com.learning.log4j.ShowInfo;

/**
 *@author zhengzh
 *@create 2013-7-23
 **/
public class SimpleLogUseTime {
	private static final Logger logger = Logger.getLogger(SimpleLogUseTime.class);
	private static int loopTimes = 1000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		avg();
	}
	private static void avg() {
		long loopStartTime = System.nanoTime();
		for(int i=0; i< loopTimes; i++) {}
		long loopEndTime = System.nanoTime()-loopStartTime;
		
		long logStartTime = System.nanoTime();
		for(int i=0; i< loopTimes; i++) {
//			logger.info("Hello world.");
			System.out.println("Hello world.");
		}
		long logEndTime = System.nanoTime()-logStartTime-loopEndTime;
		System.out.println("time:" + (logEndTime/loopTimes));
		
	}

}
