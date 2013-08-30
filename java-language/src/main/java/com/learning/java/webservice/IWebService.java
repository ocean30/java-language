package com.learning.java.webservice;

import javax.jws.WebService;

/**
 *@author zhengzh
 *@create 2013-8-30
 **/
@WebService
public interface IWebService {
	public String getServerTime();
}
