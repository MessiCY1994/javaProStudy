package com.messi.springFramework.beans.config;

import com.messi.springFramework.beans.factory.DefaultListableBeanFactory;
import org.dom4j.Element;

/**
 * @ClassName XmlBeanDefinationDocumentReader
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class XmlBeanDefinationDocumentReader {
    private DefaultListableBeanFactory beanFactory;
    public XmlBeanDefinationDocumentReader(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinations(Element rootElement) {
    }
}
