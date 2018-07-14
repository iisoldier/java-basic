package com.isoldier.basic;

import sun.misc.ProxyGenerator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * jdk
 * @author jinmeng on 16/06/2018.
 * @version 1.0
 */

public class DynamicProxy {

	public static void main(String[] args) {
		// 1.创建委托对象
		RealSubject realSubject = new RealSubject();
		// 2.创建调用处理器对象
		ProxyHandler handler = new ProxyHandler(realSubject);
		// 3.动态生成代理对象
		Subject proxySubject = (Subject) Proxy.newProxyInstance(
				RealSubject.class.getClassLoader(),
				RealSubject.class.getInterfaces(), handler);
		// 4.通过代理对象调用方法

		proxySubject.request();

	}

	/**
	 * 接口类
	 *
	 * @author isoldier
	 *
	 */
	interface Subject {
		void request();
	}

	/**
	 * 委托类
	 */
	static class RealSubject implements Subject {
		@Override
		public void request() {
			System.out.println("====RealSubject Request====");
		}
	}

	/**
	 * 代理类的调用处理器
	 */
	static class ProxyHandler implements InvocationHandler {
		private Subject subject;

		public ProxyHandler(Subject subject) {
			this.subject = subject;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			// 定义预处理的工作，当然你也可以根据 method的不同进行不同的预处理工作
			System.out.println("====before====");

			Object result = method.invoke(subject, args);
			System.out.println("====after====");
			return result;
		}
	}
}


