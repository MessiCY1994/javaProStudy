package com.messi.springFramework.beans.config;

import com.messi.springFramework.beans.factory.DefaultListableBeanFactory;
import com.messi.springFramework.beans.utils.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @ClassName XmlBeanDefinationReader
 * @Description: TODO
 * @Author messi
 * @Date 2020/8/1
 * @Version V1.0
 **/
public class XmlBeanDefinationReader {

    public void loadBeanDefinations(DefaultListableBeanFactory defaultListableBeanFactory, Resoure resoure) {
        //读取配置文件袋bean信息
        Document document = DocumentReader.createDocument(resoure.getInputStream());
        //解析document
        XmlBeanDefinationDocumentParser xmlBeanDefinationDocumentParser = new XmlBeanDefinationDocumentParser(defaultListableBeanFactory);
        xmlBeanDefinationDocumentParser.loadBeanDefinations(document.getRootElement());
    }
}
