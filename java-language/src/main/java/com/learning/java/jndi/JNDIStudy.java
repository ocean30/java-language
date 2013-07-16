package com.learning.java.jndi;

import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class JNDIStudy {
	public static void main(String[] args) {

		// Set up the environment for creating the initial context
		Hashtable<String,String> env = new Hashtable<String,String>(11);
		// 配置初始化参数
		// 指定服务工厂类
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.fscontext.RefFSContextFactory");
		// 指定服务的url。
		// "E:/dir"是一个存在的目录，把它当作一个命名空间。
		env.put(Context.PROVIDER_URL, "file:E:/dir");

		try {
			// Create the initial context
			Context ctx = new InitialContext(env);

			// Get listing of context
			NamingEnumeration<Binding> bindings = ctx.listBindings("");
			// Go through each item in list
			while (bindings.hasMore()) {
				Binding bd = bindings.next();
				System.out.println(bd.getName() + ": " + bd.getObject());
			}
			// 在context下建立两个子上下文，在文件系统中对应目录。
			ctx.createSubcontext("awt");
			ctx.createSubcontext("aaa");
			// 给命名改名，在文件系统中就是给目录改名。
			ctx.rename("aaa", "bbb");
			// 查找名字对应的对象,"E:/dir/svc.txt"对应的对象
			Object obj = ctx.lookup("svc.txt");
			System.out.println("svc.txt is bingiing with " + obj);
			// Close the context when we're done
			ctx.close();
		} catch (NamingException e) {
			System.out.println("List Bindings failed: " + e);
		}
	}
}