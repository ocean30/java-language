package com.learning.java.annotation.sample;

import java.lang.annotation.Retention;

/**
 * 定义一个annotation
 * @author zhengzh
 *
 */
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface Broker {

	String name();

	String address();

}
