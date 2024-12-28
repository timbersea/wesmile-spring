package com.qian.wesmile;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SmileFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private Class<T> tClass;

    private ApplicationContext applicationContext;


    public SmileFactoryBean(Class<T> tClass) {
        this.tClass = tClass;
    }

    public SmileFactoryBean() {
    }

    @Override
    public T getObject() {
        SmileConfigurer bean = applicationContext.getBean(SmileConfigurer.class);

        return bean.getWeSmile().getInstance(tClass);
    }

    @Override
    public Class<?> getObjectType() {
        return tClass;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
