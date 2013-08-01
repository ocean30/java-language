package com.learning.javax;

/**
 *@author zhengzh
 *@create 2013-7-30
 **/
public interface MyFilter {
	public void doFilter(String name, MyFilterChain chain);
}
