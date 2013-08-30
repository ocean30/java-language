package com.learning.designpattern.observer.my;

/**
 * 观察者组件，
 *@author zhengzh
 *@create 2013-8-27
 **/
public interface Subject<T> {
	public void attach(Observer<T> observer);
	public void detach(Observer<T> observer);
	public void notifyObservers(T source);
	public void deleteObservers();
	public int size();
	public void change(T source);
}
