package com.learning.designpattern.observer.my;

import com.learning.hibernate.tutorial.domain.Quote;

/**
 *@author zhengzh
 *@create 2013-8-27
 **/
public class QuoteObserver implements Observer<Quote> {

	@Override
	public void update(Quote interest) {
		System.out.println(this +" : " + interest.toString());
	}

}
