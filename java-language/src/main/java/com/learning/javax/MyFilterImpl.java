package com.learning.javax;

/**
 *@author zhengzh
 *@create 2013-7-30
 **/
public class MyFilterImpl implements MyFilter {
	private String name ;
	public MyFilterImpl(String name) {
		this.name = name ;
	}
	@Override
	public void doFilter(String name, MyFilterChain chain) {
		System.out.println("Filter" + name + this.name);
		chain.doFilter(name);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyFilter[] filters = new MyFilter[2];
		filters[0] = new MyFilterImpl("1");
		filters[1] = new MyFilterImpl("2");
		MyFilterChainImpl chain = new MyFilterChainImpl(filters);
		chain.doFilter("my");
		
	}

}
