package com.learning.java.webservice;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService(targetNamespace = "http://www.ocean.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class JavaAppWebService {

    @WebMethod
    public String getServerTime()
    {
        //返回服务器时间的方法
        return new Date(System.currentTimeMillis()).toString();

    }

    public static void main(String[] args)
    {
        //可以做到不借助web容器（如GlassFish或者Tomcat）发布Web Service应用
        //访问：
        //http://localhost:8088/JavaAppWebService
        //http://localhost:8088/JavaAppWebService?wsdl
        Endpoint.publish("http://localhost:8088/JavaAppWebService", new JavaAppWebService());
    }

}