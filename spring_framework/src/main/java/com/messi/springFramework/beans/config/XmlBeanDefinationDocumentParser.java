package com.messi.springFramework.beans.config;

import com.messi.springFramework.beans.factory.DefaultListableBeanFactory;
import com.messi.springFramework.beans.utils.ReflectUtils;
import org.dom4j.Element;

import java.util.List;

/**
 * 专门根据spring的语义来解析document对象
 * @ClassName XmlBeanDefinationDocumentReader
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class XmlBeanDefinationDocumentParser {
    private DefaultListableBeanFactory beanFactory;
    public XmlBeanDefinationDocumentParser(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinations(Element rootElement) {
        List<Element> elements = rootElement.elements();
        for (Element element:elements) {
            //获取标签名
            String name = element.getName();
            if (name.equals("bean")){
                parseDefaultElement(element);
            }else{
                parseCustomElement(element);
            }
        }
    }

    private void parseCustomElement(Element element) {
    }

    /**
     *
     * @param element
     *            bean标签
     */
    private void parseDefaultElement(Element beanElement) {
        try {
            if (beanElement == null)
                return;
            // 获取id属性
            String id = beanElement.attributeValue("id");

            // 获取name属性
            String name = beanElement.attributeValue("name");
            // 获取class属性
            String clazz = beanElement.attributeValue("class");
            Class<?> clazzType = Class.forName(clazz);
            // 获取init-method属性
            String initMethod = beanElement.attributeValue("init-method");

            String beanName = id == null ? name : id;
            beanName = beanName == null ? clazzType.getSimpleName() : beanName;
            // 创建BeanDefinition对象
            BeanDefination beanDefinition = new BeanDefination(clazz, beanName);
            beanDefinition.setInitMethod(initMethod);
            // 获取property子标签集合
            List<Element> propertyElements = beanElement.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }

            // 注册BeanDefinition信息
            registerBeanDefinition(beanName, beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void registerBeanDefinition(String beanName, BeanDefination beanDefinition) {
        this.beanFactory.registerBeanDefinations(beanName,beanDefinition);
    }

    /***
     * 解析property子标签
     *
     * @param beanDefinition
     *
     * @param propertyElement
     */
    private void parsePropertyElement(BeanDefination beanDefinition, Element propertyElement) {
        if (propertyElement == null)
            return;

        // 获取name属性
        String name = propertyElement.attributeValue("name");
        // 获取value属性
        String value = propertyElement.attributeValue("value");
        // 获取ref属性
        String ref = propertyElement.attributeValue("ref");

        // 如果value和ref都有值，则返回
        if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
            return;
        }

        /**
         * PropertyValue就封装着一个property标签的信息
         */
        PropertyValue pv = null;

        if (value != null && !value.equals("")) {
            // 因为spring配置文件中的value是String类型，而对象中的属性值是各种各样的，所以需要存储类型
            TypedStringValue typeStringValue = new TypedStringValue(value);

            Class<?> targetType = ReflectUtils.getTypeByFieldName(beanDefinition.getBeanClassName(), name);
            typeStringValue.setTargetType(targetType);

            pv = new PropertyValue(name, typeStringValue);
            beanDefinition.addPropertyValue(pv);
        } else if (ref != null && !ref.equals("")) {

            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            pv = new PropertyValue(name, reference);
            beanDefinition.addPropertyValue(pv);
        } else {
            return;
        }
    }


}
