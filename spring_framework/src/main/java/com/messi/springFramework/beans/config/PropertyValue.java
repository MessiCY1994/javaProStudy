package com.messi.springFramework.beans.config;

/**
 * @ClassName PropertyValue
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class PropertyValue {
    private String name;

    private Object value;

    public PropertyValue(String name, Object value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
