package com.spring1.context.ioc;

import com.spring1.context.config.Config;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaConfigApplicationContext implements ApplicationContext {
    
    private Config config;
    private Map<String, Object> beans = new HashMap<String, Object>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    public Object getBean(String beanName) throws Exception {
        Class<?> type = config.getImpl(beanName);
        Object bean = beans.get(beanName);
        if(bean != null)
            return bean;
        
        
        BeanBuilder builder = new BeanBuilder(type);
        builder.construct();
        builder.afterConstruct();
        builder.createProxy();
        bean = builder.build();
        
        
        
        beans.put(beanName,bean);
            
        return bean;
    }
    
    class BeanBuilder {
        Class<?> type;
        Object bean;
        public BeanBuilder(Class<?> type) {
            this.type = type;
        }
        public void construct() throws Exception {
            Constructor<?> constructor = type.getConstructors()[0];
            Class<?>[] paramTypes = constructor.getParameterTypes();
            if(paramTypes.length == 0) 
                bean = type.newInstance();
            else {
                Object[] params = new Object[paramTypes.length];
                for(int i = 0; i < params.length; i++) {
                    String beanName = paramTypes[i].getSimpleName();
                    char[] arrayOfChars = beanName.toCharArray();
                    arrayOfChars[0] += 32;
                    params[i] = getBean(String.valueOf(arrayOfChars));
                }
                bean = constructor.newInstance(params);
            }
        }
        public void afterConstruct() throws Exception {
            Class<?> clazz = bean.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            Method initMethod = null;
            for (Method m : methods) {
                if(m.isAnnotationPresent(PostCreate.class)) {
                    m.invoke(bean);
                    continue;
                }
                if(initMethod == null && m.getName().equals("init"))
                    initMethod = m;
            }
            if(initMethod != null)
                initMethod.invoke(bean);
        } 
        public void createProxy() {
            
        }
        public Object build() {
            return bean;
        }
    }
    
}
