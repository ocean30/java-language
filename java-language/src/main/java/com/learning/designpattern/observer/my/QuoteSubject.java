package com.learning.designpattern.observer.my;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.learning.hibernate.tutorial.domain.Quote;
import com.learning.util.Assert;



/**
 *@author zhengzh
 *@create 2013-8-27
 **/
public class QuoteSubject implements Subject<Quote> {
	private CopyOnWriteArrayList<Observer<Quote>> observers ;
	
	public QuoteSubject() {
		//默认使用同步的队列
		this(new CopyOnWriteArrayList<Observer<Quote>>());
	}

	public QuoteSubject(CopyOnWriteArrayList<Observer<Quote>> observers) {
		this.observers = observers;
	}

	/**
	 * add observer
	 */
	@Override
	public void attach(Observer<Quote> observer) {
		Assert.notNull(observer);
		observers.addIfAbsent(observer);
	}
	
	/**
	 * remove observer
	 */
	@Override
	public void detach(Observer<Quote> observer) {
		Assert.notNull(observer);
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(Quote quote) {
		Iterator<Observer<Quote>> obs = observers.iterator();
		while(obs.hasNext()) {
			obs.next().update(quote);
		}
	}

	@Override
	public void deleteObservers() {
		observers.clear();
	}

	@Override
	public int size() {
		return observers.size();
	}

	@Override
	public void change(Quote source) {
		Assert.notNull(source);
		notifyObservers(source);
	}

}
