package com.learning.java.webservice;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(endpointInterface="com.learning.java.webservice.IWebService",
targetNamespace = "http://www.ocean.com")
//@SOAPBinding(style = SOAPBinding.Style.RPC)
public class JavaAppWebService implements IWebService{

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
    	//java 发布
        Endpoint.publish("http://localhost:8088/JavaAppWebService", new JavaAppWebService());
    	//spring 发布
//    	SimpleJaxWsServiceExporter sjwse = new SimpleJaxWsServiceExporter();
//    	sjwse.setBaseAddress("http://localhost:8088/");
//    	sjwse.publishEndpoints();
    }

}