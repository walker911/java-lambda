package com.walker.lambda.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * JDK动态代理
 * </p>
 *
 * @author mu qin
 * @date 2019/12/30
 */
public class SimpleJDKDynamicProxyDemo {

    static interface IService {
        void sayHello();
    }

    static class RealService implements IService {

        @Override
        public void sayHello() {
            System.out.println("Hello");
        }
    }

    static class SimpleInvocationHandler implements InvocationHandler {

        private Object realObj;

        public SimpleInvocationHandler(Object realObj) {
            this.realObj = realObj;
        }

        /**
         * @param proxy  代理对象本身
         * @param method 正在被调用的方法
         * @param args   方法的参数
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = method.invoke(realObj, args);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }

    public static void main(String[] args) {
        IService service = new RealService();
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(),
                new Class<?>[]{IService.class}, new SimpleInvocationHandler(service));
        proxyService.sayHello();
    }
}
