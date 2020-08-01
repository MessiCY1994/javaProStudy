package com.messi.springFramework.beans.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BeanDefination
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class BeanDefination {
    private String beanName;
    private String beanClassName;
    private String initMethod;

    /**
     * bean中的属性信息
     */
    private List<PropertyValue> propertyValues = new ArrayList<>();

    public BeanDefination(String beanClassName, String beanName) {
        this.beanClassName = beanClassName;
        this.beanName = beanName;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }
}