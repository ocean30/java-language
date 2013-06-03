package com.learning.java.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BrokenSocket {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "10.10.5.116";
		int port = 12010;
		//connect to server
		Socket socket = new Socket(host, port);
		//set socket time out 10s
		socket.setSoTimeout(40000);
		//give socket to client and print receive data 
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new BrokenSocket().new  Client(socket));
		System.out.println(Thread.currentThread().getName() + " exit.");
	}
	
	class Client implements Runnable{
		private Socket client ;

		public Client(Socket client) {
			super();
			this.client = client;
		}

		@Override
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				while(true) {
					System.out.println(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
