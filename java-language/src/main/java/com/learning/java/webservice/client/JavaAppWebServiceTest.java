package com.learning.java.webservice.client;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.learning.java.webservice.IWebService;
import com.learning.java.webservice.JavaAppWebService;

public class JavaAppWebServiceTest {

	@Test
	public void testKDFService() {
		try{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/learning/java/webservice/client/spring-rpc-test.xml");
		IWebService ws = (IWebService) ctx.getBean("ws");
		System.out.println("-----------------------login----------------------");
		System.out.println(ws.getServerTime());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
