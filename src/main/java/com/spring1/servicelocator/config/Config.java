package com.spring1.servicelocator.config;

public interface Config {

     public <T> Class<T> getImpl(String ifc);
    
}

