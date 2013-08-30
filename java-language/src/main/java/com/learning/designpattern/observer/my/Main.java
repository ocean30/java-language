package com.learning.designpattern.observer.my;

import com.learning.hibernate.tutorial.domain.Quote;

/**
 *@author zhengzh
 *@create 2013-8-27
 **/
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Quote q1 = new Quote(1.1,1.2,"AUDUSD");
		Subject<Quote> quoteSubject = new QuoteSubject() ;
		QuoteObserver qq1 = new QuoteObserver();
		QuoteObserver qq2 = new QuoteObserver();
		QuoteObserver qq3 = new QuoteObserver();
		quoteSubject.attach(qq1);
		quoteSubject.attach(qq2);
		quoteSubject.attach(qq3);
		quoteSubject.change(q1);
		quoteSubject.detach(qq1);
		quoteSubject.change(q1);
	}

}
