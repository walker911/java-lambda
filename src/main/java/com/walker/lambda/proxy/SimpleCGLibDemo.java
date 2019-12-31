package com.walker.lambda.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2019/12/30
 */
public class SimpleCGLibDemo {

    static class RealService {
        public void sayHello() {
            System.out.println("Hello");
        }

        public final void sayHi() {
            System.out.println("hi");
        }
    }

    static class SimpleInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("entering " + method.getName());
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println("leaving " + method.getName());
            return result;
        }
    }

    private static <T> T getProxy(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new SimpleInterceptor());
        return (T) enhancer.create();
    }

    public static void main(String[] args) {
        RealService proxyService = getProxy(RealService.class);
        proxyService.sayHello();
        // CGLib通过继承实现，也是动态创建一个类，但这个类的父类是被代理的类，代理类重写了父类所有public非final方法
        proxyService.sayHi();
    }
}
