package com.messi.springFramework.beans.factory;

import com.messi.springFramework.beans.config.*;
import com.messi.springFramework.beans.converter.IntegerTypeConverter;
import com.messi.springFramework.beans.converter.StringTypeConverter;
import com.messi.springFramework.beans.converter.TypeConverter;
import com.messi.springFramework.beans.utils.ReflectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DefaultListableBeanFactory
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class DefaultListableBeanFactory extends AbstractBeanFactory {
    /**
     * 读取xml之后获得的beandefination信息
     */
    private Map<String, BeanDefination> beanDefinations = new HashMap<>();

    public Map<String, BeanDefination> getBeanDefinations() {
        return beanDefinations;
    }

    public void registerBeanDefinations(String beanName,BeanDefination beanDefination) {
       this.beanDefinations.put(beanName,beanDefination);
    }

    /**
     * 传说中的spring容器
     */
    private Map<String,Object> singletonObjects = new HashMap<>();

    private List<Resoure> resoures = new ArrayList<>();

    private List<TypeConverter> typeConverters = new ArrayList<>();

    public DefaultListableBeanFactory(String location){
        //注册资源类
        registeResources();
        //
        registeTypeConverter();
        //读取配置文件的bean信息
        XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader();
        //因为不清楚lication字符串到底代表的是类路径下的xml，还是d盘下面的xml，还是url
        Resoure resoure = getResource(location);
        xmlBeanDefinationReader.loadBeanDefinations(this,resoure);

    }

    private void registeTypeConverter() {
        typeConverters.add(new StringTypeConverter());
        typeConverters.add(new IntegerTypeConverter());
    }

    private void registeResources() {
        resoures.add(new ClassPathResource());
    }

    private Resoure getResource(String location) {
        for (Resoure resoure:resoures) {
            if(resoure.isCanRead(location)){
                return resoure;
            }
        }
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        Object instance =  singletonObjects.get(beanName);
        if (instance != null){
            return instance;
        }
        //只处理单例
        //先获取Beandefination
        BeanDefination beanDefination = this.getBeanDefinations().get(beanName);
        String beanClassName = beanDefination.getBeanClassName();
        //通过构造参数创建bean的实例
        instance = createBeanInstance(beanClassName,null);
        //设置参数
        setProperty(instance,beanDefination);
        //初始化
        initBean(instance, beanDefination);
        return  instance;
    }

    private void initBean(Object instance, BeanDefination beanDefination) {
        String initMethod = beanDefination.getInitMethod();
        if (initMethod==null||initMethod.equals("")){
            return;
        }
        //执行初始化方法
        ReflectUtils.invokeMethod(instance,initMethod);
    }

    private void setProperty(Object instance, BeanDefination beanDefination) {
        List<PropertyValue> propertyValues = beanDefination.getPropertyValues();
        for (PropertyValue propertyValue:propertyValues) {
            String name = propertyValue.getName();
            //VALUE的类型可能是typedStringvalue或者是RuntimeBeanReference
            Object value = propertyValue.getValue();
            Object valueToUse = null;
            if(value instanceof TypedStringValue){
                TypedStringValue typedStringValue = (TypedStringValue)value;
                String  stringValue = typedStringValue.getValue();
                Class<?> targetType = typedStringValue.getTargetType();
                for (TypeConverter converter : typeConverters) {
                    if (converter.isType(targetType)) {
                        valueToUse = converter.convert(stringValue);
                    }
                }
            }else if(value instanceof RuntimeBeanReference){
                RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference)value;
                String ref = runtimeBeanReference.getRef();
                // 递归调用getBean方法(暂时不处理循环依赖)
                valueToUse = getBean(ref);
            }
            //条用反射进行赋值
            ReflectUtils.setProperty(instance,name,valueToUse);
        }
    }

    /**
     * 创建bean实例
     * @param beanClassName
     * @param args
     */
    private Object createBeanInstance(String beanClassName, Object args) {
        return ReflectUtils.createObject(beanClassName,args);
    }
}
