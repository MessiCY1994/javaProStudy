package com.messi.springFramework.beans.factory;

/**
 * @ClassName AbstractBeanFactory
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public abstract class AbstractBeanFactory implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return null;
    }
}
