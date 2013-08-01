package com.learning.javax;

/**
 *@author zhengzh
 *@create 2013-7-30
 **/
public class MyFilterChainImpl implements MyFilterChain {
	private MyFilter[] filters ;
	private int current = -1 ;

	public MyFilterChainImpl(MyFilter[] filters) {
		this.filters = filters;
	}
	@Override
	public void doFilter(String name) {
		current++;
		if(current < filters.length)
		filters[current].doFilter(name, this);
	}

}
