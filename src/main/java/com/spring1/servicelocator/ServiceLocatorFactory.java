package com.spring1.servicelocator;

import com.spring1.servicelocator.config.Config;
import com.spring1.servicelocator.config.JavaConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceLocatorFactory {
    
    private volatile static ServiceLocatorFactory instance;
    private Config config;
    
    private ServiceLocatorFactory() {
        config = new JavaConfig();
    }
    
    public static ServiceLocatorFactory getInstance() {
        ServiceLocatorFactory localInstance = instance;
        if (localInstance == null)
            synchronized (ServiceLocatorFactory.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new ServiceLocatorFactory();
            }
        return localInstance;
    }
    
    public Object create(String ifc) {
        try {
            return config.getImpl(ifc).newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(ServiceLocatorFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServiceLocatorFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
