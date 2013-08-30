package com.learning.designpattern.observer.my;

/**
 *@author zhengzh
 *@create 2013-8-27
 **/
public interface Observer<M> {
	public String getName() ;
	public void update(M interest);
}
