package com.learning.java.annotation.sample;

import java.lang.annotation.Annotation;

/**
 * 运行期得到这个annotation
 * @author zhengzh
 *
 */
public class Main {

	public static void main(String[] args) {

		Agent agent = new Agent();

		try {

//			Annotation[] a = agent.getClass().getMethod("getTelPhone").getAnnotations();
			Annotation[] a = agent.getClass().getAnnotations();

			for (int i = 0; i < a.length; i++) {

				if (a[i] instanceof Broker) {

					Broker broker = (Broker) a[i];

					System.out.println(broker.name());
					System.out.println(broker.address());
					System.out.println(broker.annotationType());

				}

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}