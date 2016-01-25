package com.spring1.context.config;

public interface Config {

     public <T> Class<T> getImpl(String ifc);
    
}

