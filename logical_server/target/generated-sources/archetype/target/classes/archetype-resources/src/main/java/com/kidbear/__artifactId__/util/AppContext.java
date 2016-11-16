#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.util;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

    private static AppContext instance;

    private AbstractApplicationContext appContext;

    public synchronized static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    private AppContext() {
        this.appContext = new ClassPathXmlApplicationContext(
                "/applicationContext.xml");
    }

    public AbstractApplicationContext getAppContext() {
        return appContext;
    }
}
