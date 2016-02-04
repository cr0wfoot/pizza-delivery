package com.spring1.context.ioc.config;

public interface Config {

     public <T> Class<T> getImpl(String ifc);
    
}

