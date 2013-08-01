package com.learning.java.ssl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class SSLClient {

	private static String CLIENT_KEY_STORE = "E:/git-r310-e38/r310-e38/java-language/doc/client_ks";

	public static void main(String[] args) throws Exception {
		// Set the key store to use for validating the server cert.
		System.setProperty("javax.net.ssl.trustStore", CLIENT_KEY_STORE);
		
		System.setProperty("javax.net.debug", "ssl,handshake");

		SSLClient client = new SSLClient();
		Socket s = client.clientWithoutCert();

		PrintWriter writer = new PrintWriter(s.getOutputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(s
				.getInputStream()));
		writer.println("hello");
		writer.flush();
		System.out.println(reader.readLine());
		s.close();
	}

	private Socket clientWithoutCert() throws Exception {
		SocketFactory sf = SSLSocketFactory.getDefault();
		System.out.println("----------------------------------------------------");
		Socket s = sf.createSocket("localhost", 8443);
		return s;
	}
}
