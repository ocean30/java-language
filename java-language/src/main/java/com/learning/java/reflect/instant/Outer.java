package com.learning.java.reflect.instant;

import java.lang.reflect.InvocationTargetException;

public class Outer{  
   public class Inner{  
   }  
   public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException{  
      System.out.println(Inner.class);  
      //查看class是否有构造函数  
      System.out.println(Inner.class.getConstructors().length);  
      //获取第一个构造函数  
      System.out.println(Inner.class.getConstructors()[0]);  
      //用构造函数初始化内部类  
      System.out.println(Inner.class.getConstructors()[0].newInstance(new Outer()));  
   }  
}