package com.example.shop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;

    @Autowired
    public synchronized void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(type);
    }

    public static Object getBean(String beanName) {
        if (applicationContext == null) {
            return null;
        }

        return applicationContext.getBean(beanName);
    }
}
