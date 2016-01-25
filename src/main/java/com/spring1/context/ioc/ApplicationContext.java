package com.spring1.context.ioc;

public interface ApplicationContext {

    public Object getBean(String beanName) throws Exception;    
    
}
