package com.spring1.context.ioc.proxy;

import com.spring1.context.annotations.BenchMark;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyForBenchMark {
    
    public Object createProxy(Object o) {
        Method[] methods = o.getClass().getMethods();
        for(Method m: methods) {
            if(m.isAnnotationPresent(BenchMark.class)) {
                o = createProxyObject(o);
            }
        }
        return o;
    }

    private Object createProxyObject(final Object o) {
        final Class<?> type = o.getClass();
        return Proxy.newProxyInstance(
                type.getClassLoader(), 
                type.getInterfaces(), 
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.isAnnotationPresent(BenchMark.class)) {
                            System.out.println();
                            System.out.println("Method: " + method.getName());
                            long start = System.nanoTime();
                            Object obj = method.invoke(o, args);
                            long time = System.nanoTime() - start;
                            System.out.println("Invokation time: " + time);
                            return obj;
                        } else {
                            return method.invoke(o, args);
                        }
                    }
                    
                });
    }
    
}
