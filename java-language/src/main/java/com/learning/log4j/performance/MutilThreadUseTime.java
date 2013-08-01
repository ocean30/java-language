package com.learning.log4j.performance;

import org.apache.log4j.Logger;

/**
 * 
 * 测试100个线程每秒产生100条日志，同时向一个logger写入一个文件，所使用的时间和日志输出情况
 * 也就是每秒10000条日志的量
 *@author zhengzh
 *@create 2013-7-24
 **/
public class MutilThreadUseTime {
	private static final Logger logger = Logger.getLogger(MutilThreadUseTime.class);
	private static int numberOfThread = 100;
	private static long sleepTime = 10;
	private static int logTimes = 100;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		Thread[] threadPool = new Thread[ numberOfThread ];
		//init thread pool
		for(int i = 0 ; i < numberOfThread ; i++) {
			threadPool[i] = new Thread(new Task(i));
		}
		long start = System.nanoTime();
		//do log
		doLog(threadPool);
		for(int i = 0 ; i < numberOfThread ; i++) {
			threadPool[i].join();
		}
		long end = System.nanoTime() - start;
		System.out.println("useTime: " + end);
	}
	
	private static void doLog(Thread[] threadPool) {
		for(int i = 0 ; i < numberOfThread ; i++) {
			threadPool[i].start();
		}
	}

	static class Task implements Runnable {
		private String name ;
		public Task(int num) {
			super();
			this.name = Thread.currentThread().getName() +"-" + num;
		}
		@Override
		public void run() {
			for(int i = 0 ; i < logTimes ; i++) {
				logger.info(name + " " + i);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					logger.error("",e);
				}
			}
		}
	}

}
