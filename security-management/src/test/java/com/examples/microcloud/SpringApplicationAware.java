package com.examples.microcloud;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationStartupAware;
import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SpringApplicationAware implements ApplicationStartupAware , ApplicationContextAware {

    @Override
    public void setApplicationStartup(ApplicationStartup applicationStartup) {
        System.out.println(new Date()+", applicationStartup >>> " + applicationStartup.toString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(new Date()+", applicationContext >>> " + applicationContext.toString());
    }
}
