package com.learning.designpattern.observer.my;

/**
 *@author zhengzh
 * @param <T>
 * @param <M>
 *@create 2013-8-30
 **/
public interface SubjectAndObserver<T, M> extends Subject<T>, Observer<M> {

}
